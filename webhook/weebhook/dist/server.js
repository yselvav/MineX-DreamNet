"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = __importDefault(require("express"));
const axios_1 = __importDefault(require("axios"));
const crypto_1 = __importDefault(require("crypto"));
const dotenv_1 = __importDefault(require("dotenv"));
const ngrok_1 = __importDefault(require("ngrok"));
dotenv_1.default.config();
const app = (0, express_1.default)();
app.use(express_1.default.json());
const PORT = process.env.SERVER_PORT ? Number(process.env.SERVER_PORT) : 4000;
/**
 * Webhook endpoint that handles incoming requests, verifies signatures, and returns appropriate responses.
 */
app.post("/", (req, res) => {
    const sigHeader = req.headers["x-signature"];
    const data = req.body;
    const webhookSecret = process.env.WEBHOOK_SECRET;
    if (!webhookSecret) {
        return res.status(500).send("Webhook secret is not set");
    }
    const isValid = verifySignature(data, sigHeader, webhookSecret);
    if (!isValid)
        return res.status(403).send("Invalid signature");
    return res.status(200).json({
        ...data,
        text: data.eventType == "request"
            ? "Hello, how are you?"
            : "I am doing well, thank you for asking.",
        saveModified: data.eventType == "request" ? true : false,
    });
});
/**
 * POST /deysi
 * Body: { text: string, user?: string }
 */
app.post("/deysi", async (req, res) => {
    const { text, user } = req.body;
    if (!text) {
        return res.status(400).json({ error: "Missing 'text' in body" });
    }
    const agentId = "af5504a3-406e-0064-8ebb-22b7c1fca166";
    const apiUrl = `https://agents-api.doodles.app/${agentId}/user/message`;
    // Usa tu APP ID fijo y el SECRET de tu .env
    const appId = process.env.DOODLES_APP_ID || "690bde47-2c3a-420f-a277-eedd8b0de762";
    const appSecret = process.env.WEBHOOK_SECRET || "";
    try {
        const apiRes = await axios_1.default.post(apiUrl, {
            text,
            user: user || "user",
        }, {
            headers: {
                "x-mini-app-id": appId,
                "x-mini-app-secret": appSecret,
                "Content-Type": "application/json",
            },
            timeout: 10000,
        });
        return res.json(apiRes.data);
    }
    catch (err) {
        if (err.response) {
            return res.status(err.response.status).json({
                error: "Request to Deysi failed",
                detail: err.response.data,
            });
        }
        return res.status(500).json({ error: "Error sending message to Deysi", detail: err.message });
    }
});
/**
 * Verifies the signature of the incoming webhook request using HMAC SHA-256.
 */
function verifySignature(rawBody, signature, secret) {
    try {
        const cleanSignature = signature?.toString().trim();
        if (!cleanSignature)
            return false;
        const hmac = crypto_1.default.createHmac("sha256", secret);
        const rawBodyStr = typeof rawBody === "string" ? rawBody : JSON.stringify(rawBody);
        hmac.update(rawBodyStr);
        const expected = hmac.digest("base64");
        return crypto_1.default.timingSafeEqual(Buffer.from(cleanSignature), Buffer.from(expected));
    }
    catch (error) {
        console.error("Signature verification error:", error);
        return false;
    }
}
/**
 * Starts the Express server, then launches ngrok tunnel and logs the public URL.
 */
app
    .listen(PORT, async () => {
    console.log(`🚀 Server is running on http://localhost:${PORT}`);
    console.log(`📝 Environment: ${process.env.NODE_ENV || "development"}`);
    if (process.env.NGROK_ENABLED !== "false") {
        try {
            const url = await ngrok_1.default.connect({
                addr: PORT,
                authtoken: process.env.NGROK_AUTHTOKEN,
            });
            console.log(`🌐 Public URL (ngrok): ${url}`);
        }
        catch (err) {
            console.error("❌ Error starting ngrok:", err);
        }
    }
})
    .on("error", (error) => {
    if (error.code === "EADDRINUSE") {
        console.error(`❌ Port ${PORT} is already in use`);
    }
    else {
        console.error("❌ Server failed to start:", error.message);
    }
    process.exit(1);
});
