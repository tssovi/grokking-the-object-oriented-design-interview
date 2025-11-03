# ✈️ Airline Management System (Java)

A **Java implementation** of the *Grokking the Object Oriented Design Interview* problem:
**Airline Management System**, fully object-oriented and modularized.

---

## 🧩 Core OOP Concepts

This project demonstrates:

* **Encapsulation** → all attributes are private.
* **Abstraction** → key entities like `Account`, `Person`, and `Flight`.
* **Inheritance** → `Seat → FlightSeat`, `Person → Customer`.
* **Composition** → `Airport` has `Address`; `Flight` has `Aircraft`.
* **Modularity** → Organized packages and separation of concerns.

---

## 🏗️ Class Overview

| Component                                    | Description                                                |
| -------------------------------------------- | ---------------------------------------------------------- |
| `Airport`                                    | Represents airports and their associated flights.          |
| `Aircraft`                                   | Contains aircraft details and seating.                     |
| `Seat` / `FlightSeat`                        | Defines seat type and class, including fare.               |
| `FlightSchedule`                             | Handles flights, schedules, reservations, and itineraries. |
| `Account`, `Person`, `Customer`, `Passenger` | Handles users, login info, and travel identities.          |
| `Constants`                                  | Central enums and the `Address` class.                     |
| `Main`                                       | Demo class that runs the system with sample data.          |

---

## 💡 Example Run

```bash
javac com/airline/*.java
java com.airline.Main
```

**Expected Output:**

```
Customer Jane Doe booked flight com.airline.FlightSchedule$Flight@5b2133b1 from com.airline.Airport@2b193f2d to com.airline.Airport@355da254
Flight instance departs at com.airline.FlightSchedule$FlightInstance@6acbcfc0 with status SCHEDULED
Reservation created successfully with ID: RSV001
```

*(Note: Object memory addresses will differ when you run it.)*

---

## 🧠 Example Code (from Main.java)

```java
Constants.Address jfkAddr = new Constants.Address("JFK Rd", "New York", "NY", "10001", "USA");
Airport jfk = new Airport("John F. Kennedy International", jfkAddr, "JFK");

Aircraft boeing737 = new Aircraft("Boeing 737", "MAX-8", 2018);
FlightSchedule.Flight flight = new FlightSchedule.Flight("AA101", jfk, lax, 360);
```

You can extend this design with:

* `Payment` and `Billing` classes
* `FlightSearch` functionality
* `Admin` and `Crew` subclasses under `Person`

---

## 🧰 Requirements

* Java 17 or later
* Basic understanding of OOP and Java syntax

---

## 📦 Folder Structure

```
com/
 └── airline/
     ├── Account.java
     ├── Aircraft.java
     ├── Airport.java
     ├── Constants.java
     ├── FlightSchedule.java
     ├── Main.java
     ├── Seat.java
```

---

## 🧑‍💻 Author

**Converted and commented by:** ChatGPT
**Based on:** *Grokking the Object Oriented Design Interview* (Python examples)

---
