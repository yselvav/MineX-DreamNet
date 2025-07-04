# MineX – DreamNet Character Agent Hackathon Submission

![MineX Banner](images/minex-banner.png)

> **Unlocking true ownership for 350&nbsp;million Minecraft players.**

MineX is a **Minecraft server project** that bridges Web2 gameplay with Web3 ownership and AI-driven storytelling. For the **DreamNet Character Agent Hackathon**, we integrated DreamNet’s webhook and API to add original, fun, and dynamic AI-powered interactions to our story mode and throughout the map. MineX is not affiliated with DreamNet outside of this hackathon.

---

## ✨ What is MineX?

MineX is a next-generation Minecraft server designed to:
- Unlock true digital asset ownership for players by integrating on-chain wallets and asset management.
- Provide a unique story-driven quest ("Digital Garden Rescue") with interactive NPCs and puzzles.
- Seamlessly blend traditional Minecraft gameplay with blockchain features and AI-generated narrative.
- Use DreamNet’s AI only for this hackathon to enhance Daisy’s character dialogue and quest flavor.

Players explore the map, interact with Daisy in story mode, and encounter AI-powered events and messages as part of the quest and in various locations.

---

## 🎮 Quick Start

1. **Join the public demo server** (no whitelist):
   ```
   IP: 170.205.30.59:25594
   Version: Minecraft 1.21.6
   ```
2. Explore the map and follow the story prompts to meet Daisy and progress through the Digital Garden Rescue quest.
3. Experience AI-powered dialogue and original quest content throughout the adventure.

> _Gameplay trailer coming soon!_ `[![Watch the Trailer](images/trailer-thumb.png)](LINK-TO-TRAILER)`

---

## 🛠️ How It Works

### DreamNet Integration (for Hackathon)
- Our server hosts both an **API server** and a **webhook server** to communicate with DreamNet’s platform.
- Daisy’s in-game dialogue and certain quest events are powered by DreamNet’s AI, using webhooks and API calls.
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
├── src/                 # Plugin/server source (Java + YAML)
├── build.gradle         # Gradle build (Spigot API)
├── README.md            # You are here 🚀
└── .gitignore           # Ignore build artifacts & IDE files
```

We only publish the **plugin/server source**, not the full game world. Clone, build, drop the JAR into any 1.21.x Spigot/Paper server and go!

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
| Founder & Lead Dev    | **Yeray Selva** ([Telegram](https://t.me/YeraySelva)) |
| Minecraft Developer   | **Roberto Portfidia**                   |
| Builder               | **BreakerFinger**                       |
| Builder               | **cvameraman**                          |

Special thanks to **DreamNet** & **SendAI** for the agent platform, and to the whole hackathon crew for the inspiration.

---

## License

`Apache-2.0` – free to fork, hack, and grow 🌻
