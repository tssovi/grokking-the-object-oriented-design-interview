# 💹 Stock Brokerage System (Java)

Java version of the **Stock Brokerage System** from *Grokking the Object-Oriented Design Interview.*

---

## 🧩 Files

* `Constants.java` – Enums and config values
* `Order.java` – Abstract and limit order logic
* `StockExchange.java` – Singleton exchange handler
* `Member.java` – Account and trade operations
* `Main.java` – Demo runner

---

## ⚙️ Run

```bash
javac com/stockbrokerage/*.java
java com.stockbrokerage.Main
```

**Output:**

```
===== BUY ORDER =====
Saving order AAPL_BUY to database...
Submitting order to exchange: AAPL_BUY
Executing BUY LIMIT ORDER for 10 shares @ $175.5
Buy order result: SUCCESS

===== SELL ORDER =====
Cannot sell AAPL — no holdings found.
Sell order result: NO_STOCK_POSITION
```
