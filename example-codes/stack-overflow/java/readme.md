# StackOverflow-Style Q&A Model (Java 8 Version)

This project is a **plain Java** port of a simplified Q&A platform backend, originally written in Python.

## 🧱 Project Structure

| File                | Description                                           |
| ------------------- | ----------------------------------------------------- |
| `Constants.java`    | Enum definitions for question and account statuses    |
| `AccountTypes.java` | Defines Account, Member, Admin, Moderator classes     |
| `Badge.java`        | Defines Badge, Tag, Notification classes              |
| `Photo.java`        | Defines Photo and Bounty classes                      |
| `Question.java`     | Defines Question, Comment, Answer, and Search classes |
| `Main.java`         | Example entry point demonstrating object interaction  |

## ⚙️ Build Instructions

Compile all Java files:

```bash
javac *.java
```

Run the demo:

```bash
java Main
```

## 🧩 Notes

* Written for **Java 8 compatibility**
* No frameworks or dependencies required
* Each class is production-documented with Javadoc and inline comments
* Perfect for educational demos or OOP modeling practice
