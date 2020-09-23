<h1 align="center">Design an Airline Management System</h1>
<h3 align="center">Let's make an Airline Management System</h3>

**We'll cover the following:**

* [System Requirements](#system-requirements)
* [Use Case Diagram](#use-case-diagram)
* [Class Diagram](#class-diagram)
* [Activity Diagrams](#activity-diagrams)
* [Code](#code)

An Airline Management System is a managerial software which targets to control all operations of an airline. Airlines provide transport services for their passengers. They carry or hire aircraft for this purpose. All operations of an airline company are controlled by their airline management system.

This system involves the scheduling of flights, air ticket reservations, flight cancellations, customer support, and staff management. Daily flights updates can also be retrieved by using the system.

<p align="center">
    <img src="/media-files/airline-management-system.png" alt="Airline Management System">
    <br />
    Airline Management System
</p>

### System Requirements

We will focus on the following set of requirements while designing the Airline Management System:

1. Customers should be able to search for flights for a given date and source/destination airport.
2. Customers should be able to reserve a ticket for any scheduled flight. Customers can also build a multi-flight itinerary.
3. Users of the system can check flight schedules, their departure time, available seats, arrival time, and other flight details.
4. Customers can make reservations for multiple passengers under one itinerary.
5. Only the admin of the system can add new aircrafts, flights, and flight schedules. Admin can cancel any pre-scheduled flight (all stakeholders will be notified).
6. Customers can cancel their reservation and itinerary.
7. The system should be able to handle the assignment of pilots and crew members to flights.
8. The system should be able to handle payments for reservations.
9. The system should be able to send notifications to customers whenever a reservation is made/modified or there is an update for their flights.

### Use Case Diagram

We have five main Actors in our system:

* **Admin:** Responsible for adding new flights and their schedules, canceling any flight, maintaining staff-related work, etc.
* **Front desk officer:** Will be able to reserve/cancel tickets.
* **Customer:** Can view flight schedule, reserve and cancel tickets.
* **Pilot/Crew:** Can view their assigned flights and their schedules.
* **System:** Mainly responsible for sending notifications regarding itinerary changes, flight status updates, etc.

Here are the top use cases of the Airline Management System:

* **Search Flights:** To search the flight schedule to find flights for a suitable date and time.
* **Create/Modify/View reservation:** To reserve a ticket, cancel it, or view details about the flight or ticket.
* **Assign seats to passengers:** To assign seats to passengers for a flight instance with their reservation.
* **Make payment for a reservation:** To pay for the reservation.
* **Update flight schedule:** To make changes in the flight schedule, and to add or remove any flight.
* **Assign pilots and crew:** To assign pilots and crews to flights.

Here is the use case diagram of our Airline Management System:

<p align="center">
    <img src="/media-files/ams-use-case-diagram.svg" alt="Airline Management System Use Case Diagram">
    <br />
    Use Case Diagram for Airline Management System
</p>

### Class Diagram

Here are the main classes of our Airline Management System:

* **Airline:** The main part of the organization for which this software has been designed. It has attributes like ‘name’ and an airline code to distinguish the airline from other airlines.
* **Airport:** Each airline operates out of different airports. Each airport has a name, address, and a unique code.
* **Aircraft:** Airlines own or hire aircraft to carry out their flights. Each aircraft has attributes like name, model, manufacturing year, etc.
* **Flight:** The main entity of the system. Each flight will have a flight number, departure and arrival airport, assigned aircraft, etc.
* **FlightInstance:** Each flight can have multiple occurrences; each occurrence will be considered a flight instance in our system. For example, if a British Airways flight from London to Tokyo (flight number: BA212) occurs twice a week, each of these occurrences will be considered a separate flight instance in our system.
* **WeeklySchedule and CustomSchedule:** Flights can have multiple schedules and each schedule will create a flight instance.
* **FlightReservation:** A reservation is made against a flight instance and has attributes like a unique reservation number, list of passengers and their assigned seats, reservation status, etc.
* **Itinerary:** An itinerary can have multiple flights.
* **FlightSeat:** This class will represent all seats of an aircraft assigned to a specific flight instance. All reservations of this flight instance will assign seats to passengers through this class.
* **Payment:** Will be responsible for collecting payments from customers.
* **Notification:** This class will be responsible for sending notifications for flight reservations, flight status update, etc.

<p align="center">
    <img src="/media-files/ams-class-diagram.png" alt="Airline Management System Class Diagram">
    <br />
    Class Diagram for Airline Management System
</p>

<p align="center">
    <img src="/media-files/ams-uml.svg" alt="Airline Management System UML">
    <br />
    UML for Airline Management System
</p>

### Activity Diagrams

**Reserve a ticket:** Any customer can perform this activity. Here are the steps to reserve a ticket:

<p align="center">
    <img src="/media-files/ams-reserve-ticket-activity-diagram.svg" alt="Airline Management System Reserve Ticket Activity Diagram">
    <br />
    Activity Diagram for Airline Management System Reserve Ticket
</p>

* **Cancel a reservation:** Any customer can perform this activity. Here are the set of steps to cancel a reservation:

<p align="center">
    <img src="/media-files/ams-cancel-reservation-activity-diagram.svg" alt="Airline Management System Cancel Reservation Activity Diagram">
    <br />
    Activity Diagram for Airline Management System Cancel Reservation
</p>

### Code

Here is the code for major classes.

**Enums and Constants:** Here are the required enums, data types, and constants:

```python
from enum import Enum


class FlightStatus(Enum):
    ACTIVE, SCHEDULED, DELAYED, DEPARTED, LANDED, IN_AIR, ARRIVED, CANCELLED, DIVERTED, UNKNOWN = 1, 2, 3, 4, 5, 6, 7, 8, 9, 10


class PaymentStatus(Enum):
    UNPAID, PENDING, COMPLETED, FILLED, DECLINED, CANCELLED, ABANDONED, SETTLING, SETTLED, REFUNDED = 1, 2, 3, 4, 5, 6, 7, 8, 9, 10


class ReservationStatus(Enum):
    REQUESTED, PENDING, CONFIRMED, CHECKED_IN, CANCELLED, ABANDONED = 1, 2, 3, 4, 5, 6


class SeatClass(Enum):
    ECONOMY, ECONOMY_PLUS, PREFERRED_ECONOMY, BUSINESS, FIRST_CLASS = 1, 2, 3, 4, 5


class SeatType(Enum):
    REGULAR, ACCESSIBLE, EMERGENCY_EXIT, EXTRA_LEG_ROOM = 1, 2, 3, 4, 5


class AccountStatus(Enum):
    ACTIVE, CLOSED, CANCELED, BLACKLISTED, BLOCKED = 1, 2, 3, 4, 5, 6


class Address:
    def __init__(self, street, city, state, zip_code, country):
        self.__street_address = street
        self.__city = city
        self.__state = state
        self.__zip_code = zip_code
        self.__country = country


```

**Account, Person, Customer and Passenger:** These classes represent the different people that interact with our system:

```python
from abc import ABC
from .constants import *


# For simplicity, we are not defining getter and setter functions. The reader can
# assume that all class attributes are private and accessed through their respective
# public getter methods and modified only through their public methods function.

class Account:
    def __init__(self, id, password, status=AccountStatus.Active):
        self.__id = id
        self.__password = password
        self.__status = status

    def reset_password(self):
        None


class Person(ABC):
    def __init__(self, name, address, email, phone, account):
        self.__name = name
        self.__address = address
        self.__email = email
        self.__phone = phone
        self.__account = account


class Customer(Person):
    def __init__(self, frequent_flyer_number):
        self.__frequent_flyer_number

    def get_itineraries(self):
        None


class Passenger:
    def __init__(self, name, passport_number, date_of_birth):
        self.__name = name
        self.__passport_number = passport_number
        self.__date_of_birth = date_of_birth

    def get_passport_number(self):
        return self.__passport_number


```

**Airport, Aircraft, Seat and FlightSeat:** These classes represent the top-level classes of the system:

```python
class Airport:
    def __init__(self, name, address, code):
        self.__name = name
        self.__address = address
        self.__code = code

    def get_flights(self):
        None


class Aircraft:
    def __init__(self, name, model, manufacturing_year):
        self.__name = name
        self.__model = model
        self.__manufacturing_year = manufacturing_year
        self.__seats = []

    def get_flights(self):
        None


class Seat:
    def __init__(self, seat_number, type, seat_class):
        self.__seat_number = seat_number
        self.__type = type
        self.__seat_class = seat_class


class FlightSeat(Seat):
    def __init__(self, fare):
        self.__fare = fare

    def get_fare(self):
        return self.__fare


```

**Flight Schedule classes, Flight, FlightInstance, FlightReservation, Itinerary:** Here are the classes related to flights and reservations:

```python
class WeeklySchedule:
    def __init__(self, day_of_week, departure_time):
        self.__day_of_week = day_of_week
        self.__departure_time = departure_time


class CustomSchedule:
    def __init__(self, custom_date, departure_time):
        self.__custom_date = custom_date
        self.__departure_time = departure_time


class Flight:
    def __init__(self, flight_number, departure, arrival, duration_in_minutes):
        self.__flight_number = flight_number
        self.__departure = departure
        self.__arrival = arrival
        self.__duration_in_minutes = duration_in_minutes

        self.__weekly_schedules = []
        self.__custom_schedules = []
        self.__flight_instances = []


class FlightInstance:
    def __init__(self, departure_time, gate, status, aircraft):
        self.__departure_time = departure_time
        self.__gate = gate
        self.__status = status
        self.__aircraft = aircraft

    def cancel(self):
        None

    def update_status(self, status):
        None


class FlightReservation:
    def __init__(self, reservation_number, flight, aircraft, creation_date, status):
        self.__reservation_number = reservation_number
        self.__flight = flight
        self.__seat_map = {}
        self.__creation_date = creation_date
        self.__status = status

    def fetch_reservation_details(self, reservation_number):
        None

    def get_passengers(self):
        None


class Itinerary:
    def __init__(self, customer_id, starting_airport, final_airport, creation_date):
        self.__customer_id = customer_id
        self.__starting_airport = starting_airport
        self.__final_airport = final_airport
        self.__creation_date = creation_date
        self.__reservations = []

    def get_reservations(self):
        None

    def make_reservation(self):
        None

    def make_payment(self):
        None


```

