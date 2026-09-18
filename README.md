# EasyMagic BEER Compatibility

A small compatibility mod that makes [EasyMagic](https://www.curseforge.com/minecraft/mc-mods/easy-magic) and [BEER](https://www.curseforge.com/minecraft/mc-mods/bookshelf-extended-enchanting-reach) play nicely together.

## The basic idea

> **BEER decides where to look. EasyMagic decides what to do with what it finds.**

BEER expands the area around an enchanting table in which bookshelves can be detected.

EasyMagic replaces the vanilla enchanting-table menu and calculation, adding features such as support for chiseled bookshelves.

When used together, EasyMagic's custom enchanting calculation normally continues to use Minecraft's vanilla bookshelf search range, meaning BEER's expanded range is not taken into account.

This mod bridges that gap.

It does not replace either mod, and neither mod's JAR files need to be modified.

---

## What does it actually change?

The compatibility mod connects BEER's expanded bookshelf range to EasyMagic's enchanting-power calculation.

EasyMagic continues to handle:

* Normal bookshelf enchantment power
* Chiseled bookshelf contents
* Valid bookshelf checks
* Enchanting-power calculation
* The enchanting-table GUI
* Enchantment generation

BEER continues to handle:

* The expanded enchanting-table range
* Per-table range modifiers
* Its existing range configuration

This mod simply makes EasyMagic use the bookshelf positions provided by BEER when calculating enchanting power.

---

## Requirements

For the current Forge version, you need:

* Minecraft **1.20.1**
* **Forge**
* **EasyMagic 8.0.1**
* **BEER 1.4.2**
* **EasyMagic BEER Compatibility**

EasyMagic and BEER are required for this mod to have anything to connect.

### Compatibility with other versions

The current release has only been tested with the versions listed above.

If you want to try it with another Minecraft, Forge, EasyMagic, or BEER version, feel free to test it and let me know whether it works.

---

## Installation

1. Install Minecraft **1.20.1 Forge**.
2. Install the required versions of **EasyMagic** and **BEER**.
3. Download `easymagicbeer-1.0.0.jar`.
4. Place the JAR directly into your Minecraft `mods` folder.
5. **Do not extract the JAR.**

Your `mods` folder should contain something similar to:

```text
mods/
├── beer-1.4.2.jar
├── EasyMagic-v8.0.1-1.20.1-Forge.jar
└── easymagicbeer-1.0.0.jar
```

Launch Minecraft and all three mods should load together.

### Important

Some messaging applications may treat JAR files like ZIP archives or otherwise interfere with the file.

If Windows shows the downloaded file as a Java archive/JAR, leave it as-is.

You want:

```text
easymagicbeer-1.0.0.jar
```

as a single file in your `mods` folder.

If you open the archive and extract its contents into the `mods` folder, Minecraft will not load it as a mod.

---

## How to use it

Once all three mods are active, you can use BEER's existing recipes to modify your enchanting table:

* **Block of Redstone** → modify the X range
* **Glowstone** → modify the Y range
* **Block of Lapis Lazuli** → modify the Z range
* **Quartz in the offhand** → use BEER's corresponding decrease recipes

Crouch while right-clicking the enchanting table to apply the BEER modification.

EasyMagic will then use BEER's modified enchanting-table range when calculating enchanting power.

Other than that, use the enchanting table normally through EasyMagic.

---

## Known limitations

### Jade

Jade's enchanting-table information does not currently appear to account for BEER's expanded range when displaying enchanting power.

For example, a bookshelf outside Minecraft's vanilla range may contribute to the actual EasyMagic enchanting interface while Jade still reports no additional enchanting power.

The actual enchantment menu correctly accounts for the expanded range.

**This is currently considered a Jade compatibility issue and is outside the scope of this mod.**

### OptiFine

OptiFine may be incompatible with this mod in some configurations.

Testing has shown that the compatibility mod works correctly with OptiFine disabled. There are also configurations/modpacks where OptiFine is present without causing the same problem, so the exact interaction has not yet been fully investigated.

If the mod does not appear to work, try disabling OptiFine first.

---

## Why does this exist?

I wanted to build a large library around an enchanting table and have the entire library actually contribute to enchanting, rather than having Minecraft's relatively small vanilla bookshelf range determine which shelves mattered.

BEER already provides the ability to expand the enchanting table's range.

EasyMagic already provides an improved enchanting experience, including support for chiseled bookshelves.

The problem was that the two mods approached the enchanting calculation independently. BEER could expand the table's range, but EasyMagic's custom calculation would still look only at Minecraft's vanilla bookshelf positions.

There was also an interaction conflict when modifying the enchanting table with BEER's range-modifier items while EasyMagic was installed.

Rather than modifying either mod directly, this project provides a separate compatibility layer between them.

The goal is simple:

**Let BEER handle the range, let EasyMagic handle enchanting, and make the two agree on where the enchanting table should look.**

---

## Technical details

This project uses Mixins to alter EasyMagic's runtime behavior without modifying its JAR file.

The compatibility layer makes two changes to EasyMagic:

1. It allows BEER's crouching range-modifier interaction to take precedence over EasyMagic's enchanting-table interaction.
2. It redirects EasyMagic's enchanting-power bookshelf lookup to BEER's expanded bookshelf offsets.

EasyMagic's existing enchanting calculation is otherwise preserved.

Conceptually:

```text
                    ┌─────────────────┐
                    │      BEER       │
                    │                 │
                    │ Expanded range  │
                    │ Range modifiers │
                    └────────┬────────┘
                             │
                     bookshelf positions
                             │
                             ▼
                  ┌─────────────────────┐
                  │ EasyMagic BEER      │
                  │ Compatibility      │
                  └──────────┬──────────┘
                             │
                      expanded positions
                             │
                             ▼
                    ┌─────────────────┐
                    │    EasyMagic    │
                    │                 │
                    │ Enchanting      │
                    │ calculation     │
                    │ + chiseled      │
                    │   bookshelves   │
                    └─────────────────┘
```

Neither EasyMagic nor BEER is bundled with this project, and their original JAR files remain untouched.

---

## License

See the repository's license information for the licensing terms of this project.

EasyMagic and BEER remain separate projects and retain their respective licenses and ownership.

## Credits

* **EasyMagic** by Fuzs
* **BEER (Bookshelf Enchanting Expansion Range)** by BreakInBlocks
* Compatibility mod by **LexRei**

This project is not affiliated with or endorsed by the authors of EasyMagic or BEER.
