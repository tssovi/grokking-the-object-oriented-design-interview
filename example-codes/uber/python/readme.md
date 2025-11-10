# 🚗 Uber Ride-Sharing System (Python)

Pythonic version of the **Uber system** from *Grokking the Object-Oriented Design Interview*.

---

## 🧩 Files

* `constants.py` — Enums and configuration
* `user.py` — Rider and Driver models
* `vehicle.py` — Vehicle entity
* `trip.py` — Trip + TripManager logic
* `main.py` — Demo runner

---

## ⚙️ Run

```bash
python main.py
```

**Output:**

```
[TripManager] Driver Alice registered.
[Rider] Bob requesting a ride from Downtown to Airport.
[Trip] Trip 1 assigned to driver Alice.
[Driver] Alice accepted trip 1.
[Trip] Trip 1 completed. Total fare: $25.00
[Driver] Alice completed trip 1.
```
