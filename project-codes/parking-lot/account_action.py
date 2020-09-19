from .constants import *


class Account:
    def __init__(self, user_name, password, person, status=AccountStatus.Active):
        self.__user_name = user_name
        self.__password = password
        self.__person = person
        self.__status = status

    def reset_password(self):
        None


class Admin(Account):
    def __init__(self, user_name, password, person, status=AccountStatus.Active):
        super().__init__(user_name, password, person, status)

    def add_parking_floor(self, floor):
        None

    def add_parking_spot(self, floor_name, spot):
        None

    def add_parking_display_board(self, floor_name, display_board):
        None

    def add_customer_info_panel(self, floor_name, info_panel):
        None

    def add_entrance_panel(self, entrance_panel):
        None

    def add_exit_panel(self, exit_panel):
        None


class ParkingAttendant(Account):
    def __init__(self, user_name, password, person, status=AccountStatus.Active):
        super().__init__(user_name, password, person, status)

    def process_ticket(self, ticket_number):
        None

