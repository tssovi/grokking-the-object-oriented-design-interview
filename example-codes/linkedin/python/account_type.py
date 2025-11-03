from abc import ABC
from datetime import datetime
from .constants import AccountStatus
from .profile import Profile


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


class Member(Person):
    def __init__(self):
        self.__date_of_membership = datetime.date.today()
        self.__headline = ""
        self.__photo = []
        self.__member_suggestions = []
        self.__member_follows = []
        self.__member_connections = []
        self.__company_follows = []
        self.__group_follows = []
        self.__profile = Profile()

    def send_message(self, message):
        None

    def create_post(self, post):
        None

    def send_connection_invitation(self, connection_invitation):
        None


class Admin(Person):
    def block_user(self, customer):
        None

    def unblock_user(self, customer):
        None
