<p align="center">
	<img width="512" height="512" alt="Chronodb" src="https://github.com/user-attachments/assets/f4d5e037-4d19-457a-8b9b-83c1cb835d4b" />
</p>

# ChronoDB

ChronoDB is a writing-based digital calendar that allows users to manage events using natural-language commands similar to SQL.
Instead of navigating through complex graphical menus, users can add, edit, and remove events simply by typing commands.

The goal of ChronoDB is to make scheduling faster and more intuitive for people who prefer text-based workflows.

---

## Screenshot

<p align="center">
	<img width="1920" height="1200" alt="cdbExample" src="https://github.com/user-attachments/assets/ba255144-2370-451b-9726-f4253cdd0ee5" />
</p>

---

## Features

* Create events using simple text commands
* Edit or remove events with command-based instructions
* Support for repeating events
* Fast keyboard-driven workflow
* Designed for users who prefer writing over clicking through UI menus

---

## Example Commands

ChronoDB uses simple structured commands to interact with the calendar.

Add an event:

```
ADD Meeting WITH team AT 2026-04-12 14:00
```

Add a repeating event:

```
ADD REPEAT Daily standup EVERY DAY UNTIL 2026-05-01
```

Remove a repeating event:

```
REMOVE REPEAT Daily standup UNTIL 2026-05-01
```

---

## Motivation

Many existing digital calendars rely heavily on graphical interfaces and nested menus.
ChronoDB explores an alternative approach where users can manage their schedule through writing, similar to how developers interact with databases using queries.

This approach can make calendar management faster, more flexible, and easier to automate.

---

## Tech Stack

* Java
* JavaFX
* Command-based parsing system
* Custom event storage and logic

---

## Project Status

ChronoDB was a school project and is no longer being developed

---

## License

This project is currently for educational and experimental purposes.
