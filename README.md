
# CS4001 Gadget Shop

A Java Swing desktop application built for the CS4001 coursework assignment. The application manages a shop inventory of **Mobile phones** and **MP3 players** stored in an `ArrayList`.

---

## How to Compile and Run

Make sure you have Java installed, then open a command prompt in this folder and run:

```bash
javac Gadget.java Mobile.java MP3.java GadgetShop.java
java GadgetShop

```

The GUI window will open.

---

## Files

| File | Description |
| --- | --- |
| `Gadget.java` | Base class — model, price, weight, size |
| `Mobile.java` | Subclass of Gadget — calling credit, `makeCall()`, `addCredit()` |
| `MP3.java` | Subclass of Gadget — available memory, `downloadMusic()`, `deleteMusic()` |
| `GadgetShop.java` | Main GUI class — `ArrayList`, 10 text fields, 6 buttons |

---

## Class Hierarchy

```text
Gadget (base class)
  ├── Mobile (extends Gadget)
  └── MP3 (extends Gadget)

GadgetShop (extends JFrame, implements ActionListener)
  └── ArrayList<Gadget> — stores both Mobile and MP3 objects

```

---

## Features

* Add Mobile phones and MP3 players to an `ArrayList`
* Display details of all gadgets
* Make a call on a Mobile (deducts calling credit)
* Add calling credit to a Mobile
* Download music to an MP3 player (deducts available memory)
* Delete music from an MP3 player (restores available memory)
* Input validation with `try/catch` blocks and `JOptionPane` error dialogs
* Colour-coded buttons grouped by function

---

## GUI Overview

| Button | Colour | Action |
| --- | --- | --- |
| **Add Mobile** | Blue | Creates a new Mobile and adds it to the ArrayList |
| **Add MP3** | Blue | Creates a new MP3 and adds it to the ArrayList |
| **Display All** | Green | Prints full details of every gadget |
| **Make A Call** | Orange | Calls `makeCall()` on the selected Mobile |
| **Add Credit** | Orange | Calls `addCredit()` on the selected Mobile |
| **Download Music** | Purple | Calls `downloadMusic()` on the selected MP3 |
| **Delete Music** | Purple | Calls `deleteMusic()` on the selected MP3 |
| **Clear** | Grey | Clears all text fields and the output panel |
```
