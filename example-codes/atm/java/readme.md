# 🏦 Bank Management System (Java)

This is a **Java implementation** of the *Grokking the Object Oriented Design Interview* example:
**Bank and ATM System**, demonstrating core OOP design concepts in a compact, interview-friendly structure.

---

## 🧩 Overview

| File               | Description                                         |
| ------------------ | --------------------------------------------------- |
| `Constants.java`   | Enums and Address class                             |
| `Bank.java`        | Bank and ATM logic + hardware components            |
| `Customer.java`    | Customer, Account, and Card classes                 |
| `Transaction.java` | Transaction hierarchy (Withdraw, Deposit, etc.)     |
| `Main.java`        | Demo runner that simulates a withdrawal transaction |

---

## 💡 Key OOP Concepts

* **Encapsulation** — All attributes are private and accessible via methods.
* **Inheritance** — `Account` → `SavingAccount` and `CheckingAccount`.
* **Composition** — `ATM` has hardware components; `Customer` has `Card` and `Account`.
* **Abstraction** — `Transaction` is the base class for various transaction types.

---

## 🧱 System Flow

1. Create a `Bank` and `ATM`.
2. Create a `Customer` with an `Account` and `Card`.
3. Perform a transaction (e.g., `Withdraw`).
4. Output simulates how an ATM would process the request.

---

## 🧰 Example Run

```bash
javac com/bank/*.java
java com.bank.Main
```

**Expected Output:**

```
Withdrawing $100.0
Transaction processed for customer: John Doe
```

---

## 🏗️ Example Code (Main.java)

```java
Constants.Address addr = new Constants.Address("123 Wall St", "New York", "NY", "10001", "USA");
Account acc = new SavingAccount("ACC001", 5000);
Card card = new Card("CARD001", "John Doe", "12/30", "1234");

Customer cust = new Customer("John Doe", addr, "john@example.com", "555-9999",
        Constants.CustomerStatus.ACTIVE, card, acc);

Bank bank = new Bank("Grokking Bank", "GB001");
ATM atm = new ATM("ATM01", "Downtown");

Transaction withdraw = new Withdraw("TXN001", new Date(), Constants.TransactionStatus.SUCCESS, 100);
cust.makeTransaction(withdraw);
atm.makeTransaction(cust, withdraw);
```

---

## ✅ Notes

* All methods marked `TODO` are placeholders for business logic.
* This version mirrors the Python structure but follows Java OOP best practices.
* Ideal for system design or OOD interviews.

---
