# MineX – DreamNet Character Agent Hackathon Submission

![MineX Banner](images/MineX_logo.png)

> **Unlocking true ownership for 350&nbsp;million Minecraft players.**

MineX is a **Minecraft server project** that bridges Web2 gameplay with Web3 ownership and AI-driven storytelling. For the **DreamNet Character Agent Hackathon**, we integrated DreamNet’s webhook and API to add original, fun, and dynamic AI-powered interactions to an special story mode and throughout the map. MineX is not affiliated with DreamNet outside of this hackathon (yet).

---

## ✨ What is MineX?

MineX is a next-generation Minecraft server designed to:
- Unlock true digital asset ownership for players by integrating on-chain wallets and asset management.
- Provide a unique story-driven quest ("Digital Garden Rescue") with interactive NPCs and puzzles.
- Seamlessly blend traditional Minecraft gameplay with blockchain features and AI-generated narrative.
- Use DreamNet’s AI only for this hackathon to enhance Daisy’s character dialogue and quest flavor.
- Provide a **ready-made plugin + webhook template** so other Minecraft server owners can integrate DreamNet agents into their own worlds in minutes.

Players explore the map, interact with Daisy in story mode, and encounter AI-powered events and messages as part of the quest and in various locations.

---

## 🎮 Quick Start

1. **Join the DreamNet public demo server** (no whitelist):
   ```
   IP: 170.205.30.59:25594
   Version: Minecraft 1.21.6
   ```
2. Explore the map and follow the story prompts to meet Daisy and progress through the Digital Garden Rescue quest.
3. Experience AI-powered dialogue and original quest content throughout the adventure.

### 🎬 Demo Videos

<a href="https://youtu.be/M-3dmY4mVWM?si=Mu89EatQ8-IWserL" target="_blank" rel="noopener noreferrer"><img src="https://img.youtube.com/vi/M-3dmY4mVWM/hqdefault.jpg" alt="Gameplay Trailer"></a>
*60-second trailer that captures the high-level vision of MineX.*

<a href="https://youtu.be/IAEKAfLcz7A?si=vy8qXRv403NbA1g4" target="_blank" rel="noopener noreferrer"><img src="https://img.youtube.com/vi/IAEKAfLcz7A/hqdefault.jpg" alt="Full Gameplay Walkthrough"></a>
*Long-form, unedited session covering the core quest. (Later in the map you’ll find branching choice-question encounters—also answered by AI—but we kept them out of this cut to avoid spoilers.)*

This is the link to our <a href="https://x.com/MineXsol/status/1941931966808367572" target="_blank" rel="noopener noreferrer">Tweet on X</a> announcing our participation — you can see some pictures there!

Our broader roadmap (see below) brings **on-chain AI agents** to every player via smart wallets. DreamNet’s technology is a perfect fit for this, as outlined in the <a href="https://minex-solana.gitbook.io/minex-world-chain/basics/5.-ai-agents" target="_blank" rel="noopener noreferrer">MineX AI Agents spec</a>.

Visit **<a href="https://MineX.gg" target="_blank" rel="noopener noreferrer">MineX.gg</a>** for the latest news, server IPs, and community events.

---

## 🛠️ How It Works

### DreamNet Integration (for Hackathon)
- Our server hosts both an **API server** and a **webhook server** to communicate with DreamNet’s platform.
- Daisy’s in-game dialogue and certain quest messages are powered by DreamNet’s AI, using webhooks and API calls.
- Every interaction is designed to feel natural and immersive, leveraging DreamNet for creative, context-aware responses.

### Architecture

```
┌────────────┐         ┌────────────────────────┐
│  Player    │ ──────▶│ MineX Minecraft Server │
└────────────┘         │  • Story & Quests      │
                       │  • API Server         │
                       │  • Webhook Server     │
                       └─────────┬─────────────┘
                                 │ REST/Webhook
                                 ▼
                    ┌──────────────────────────┐
                    │ DreamNet Agents API      │
                    └──────────────────────────┘
```

---

## 📂 Repository Contents

```
MineX/
├── src/                 # Minecraft **plugin** source (Java + YAML)
├── webhook/             # Netlify Function source (TypeScript)
├── build.gradle         # Gradle build (Spigot/Paper API)
├── README.md            # You are here 🚀
└── .gitignore           # Ignore build artifacts & IDE files
```

This repo ships **both** the plugin *and* the Netlify webhook—everything you need to reproduce the DreamNet integration locally. (We don’t include the full game world.) Clone, build, drop the JAR into any 1.21.x Spigot/Paper server and go!

---

## 🌱 Roadmap

- [ ] Attach Swig smart-wallets → token rewards on quest completion.  
- [ ] Persist quest state on-chain.  
- [ ] Multiplayer branching quests with AI memory.  
- [ ] Marketing push with influencers post-TGE.

---

## 🤝 Credits

| Role                  | Name                                    |
|-----------------------|-----------------------------------------|
| Founder & Lead Dev    | **Yeray Selva** (<a href="https://t.me/YeraySelva" target="_blank" rel="noopener noreferrer">Telegram</a>) |
| Minecraft Developer   | **Roberto Porfidia**                   |
| Builder               | **BreakerFinger**                       |
| Video Editor          | **Bacefi**                               |

Special thanks to **DreamNet** & **SendAI** for the agent platform, and to the whole hackathon crew for the inspiration.

---

## License

`Apache-2.0` – free to fork, hack, and grow 🌻
