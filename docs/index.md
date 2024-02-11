---
layout: home

hero:
  name: "DivineMC"
  tagline: "DivineMC is the fork of Purpur compatible with Spigot plugins, offering the best performance for your server."
  image:
    src: logo.png
    alt: DivineMC
  actions:
    - theme: brand
      text: Download
      link: https://github.com/DivineMC/DivineMC/releases/latest
    - theme: alt
      text: Documentation
      link: /docs/

features:
  - icon: 🍴
    title: Based on Purpur
    details: Purpur is a fork of Paper with new fun and exciting gameplay features, and performance boost.
  - icon: 📰
    title: Popular mods implemented
    details: Patches from mods such as Lithium, VMP, C2ME and others.
  - icon: 🐾
    title: Async Pathfinding
    details: Makes pathfinding-related work happen asynchronously.
  - icon: 🏠
    title: Linear region format 
    details: Linear region format saves about 50% of disk space in Overworld and Nether and 95% in The End.
  - icon: 💬
    title: Configurable chat reports 
    details: Disallow players from reporting others messages to Mojang.
  - icon: 😌
    title: Optimized Default Configuration
    details: The default configuration files is optimized.
  - icon: 🐛
    title: Bug fixes
    details: Fixed Minecraft bugs that reported on Mojira.
  - icon: 🔃
    title: Always up-to date
    details: DivineMC checks for updates from Purpur every day.
---

<style>
:root {
  --vp-home-hero-name-color: transparent;
  --vp-home-hero-name-background: -webkit-linear-gradient(120deg, #ffa38a 30%, #310085);

  --vp-home-hero-image-background-image: linear-gradient(-45deg, #ffa38a 50%, #310085 50%);
  --vp-home-hero-image-filter: blur(40px);
}

@media (min-width: 640px) {
  :root {
    --vp-home-hero-image-filter: blur(56px);
  }
}

@media (min-width: 960px) {
  :root {
    --vp-home-hero-image-filter: blur(72px);
  }
}
</style>
