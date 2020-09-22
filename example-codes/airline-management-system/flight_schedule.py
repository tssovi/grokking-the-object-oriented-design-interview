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
