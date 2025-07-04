# MineX – DreamNet Character Agent Hackathon Submission

![MineX Banner](images/minex-banner.png)

> **Unlocking true ownership for 350&nbsp;million Minecraft players.**

MineX is a **Minecraft plugin** that seamlessly bridges Web2 gameplay with Web3 ownership and AI-driven storytelling.  
Built for the **DreamNet Character Agent Hackathon**, it showcases how DreamNet’s webhook + agent architecture can inject dynamic NPC dialogue and quest logic into the world’s most-played game.

---

## ✨ Key Features

| Category | What We Built |
| -------- | ------------- |
| **AI Immersion** | Daisy, a whimsical AI guide, responds to player commands (`/prompt1 … /promptorange`) via DreamNet’s Agents API. Each prompt asks Daisy to generate rhyming verses, gothic omens, or colour-themed personality insights. |
| **On-chain Ready** | The plugin architecture is wallet-ready – future work will attach a Swig smart-wallet to each player for in-game token rewards & trades. |
| **Zero Friction** | Players join with any vanilla 1.21.6 client (PC, Console, Mobile) – no mods, no wallets required. |
| **Quest Framework** | “Digital Garden Rescue” questline with location triggers, puzzles, and AI-generated narration. |

---

## 🎮 Quick Start

1. **Join the public demo server** (no whitelist):
   ```
   IP: 170.205.30.59:25594
   Version: Minecraft 1.21.6
   ```
2. Meet **Daisy** at spawn and follow the on-screen instructions.
3. Trigger AI dialogue anytime:
   * `/prompt1` … `/prompt5` – rhymes, omens & limericks
   * `/promptblue` – positive deep-dive on the colour blue
   * `/promptyellow` – humorous roast on yellow
   * `/promptorange` – witty roast on orange

> _Gameplay trailer coming soon!_ `[![Watch the Trailer](images/trailer-thumb.png)](LINK-TO-TRAILER)`

---

## 🛠️ How It Works

### DreamNet Integration

* The plugin sends JSON to the DreamNet **Agents API** (`/user/message`).  
* Each command crafts a bespoke prompt that includes the player’s name, guiding Daisy’s response.
* After each call, we optionally wipe the agent’s short-term memory so every player enjoys a fresh interaction.

### Architecture

```
┌────────────┐      /promptX         ┌────────────────────────┐
│  Player    │ ───────────────────▶ │ MineX Plugin (Spigot)  │
└────────────┘                      │  • Commands & Quests    │
                                    │  • DreamNet webhooks    │
                                    └─────────┬──────────────┘
                                              │ REST
                                              ▼
                                 ┌──────────────────────────┐
                                 │ DreamNet Agents API      │
                                 └──────────────────────────┘
```

---

## 📂 Repository Contents

```
MineX/
├── src/                 # Plugin source (Java + YAML)
├── build.gradle         # Gradle build (Spigot API)
├── README.md            # You are here 🚀
└── .gitignore           # Ignore build artifacts & IDE files
```

We only publish the **plugin source**, not the full game world. Clone, build, drop the JAR into any 1.21.x Spigot/Paper server and go!

---

## 🌱 Roadmap

- [ ] Attach Swig smart-wallets → token rewards on quest completion.  
- [ ] Persist quest state on-chain.  
- [ ] Multiplayer branching quests with AI memory.  
- [ ] Marketing push with influencers post-TGE.

---

## 🤝 Credits

| Role | Name |
|------|------|
| Founder & Lead Dev | **Yeray Selva** ([Telegram](https://t.me/YeraySelva)) |
| Co-Founder & Game Dev | **Kevin Celiberti** |

Special thanks to **DreamNet** & **SendAI** for the agent platform, and to the whole hackathon crew for the inspiration.

---

## License

`Apache-2.0` – free to fork, hack, and grow 🌻
