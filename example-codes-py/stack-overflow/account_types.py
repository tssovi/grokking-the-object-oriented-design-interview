from .constants import *


# For simplicity, we are not defining getter and setter functions. The reader can
# assume that all class attributes are private and accessed through their respective
# public getter methods and modified only through their public methods function.


class Account:
    def __init__(self, id, password, name, address, email, phone, status=AccountStatus.Active):
        self.__id = id
        self.__password = password
        self.__name = name
        self.__address = address
        self.__email = email
        self.__phone = phone
        self.__status = status
        self.__reputation = 0

    def reset_password(self):
        None


class Member:
    def __init__(self, account):
        self.__account = account
        self.__badges = []

    def get_reputation(self):
        return self.__account.get_reputation()

    def get_email(self):
        return self.__account.get_email()

    def create_question(self, question):
        None

    def create_tag(self, tag):
        None


class Admin(Member):
    def block_member(self, member):
        None

    def unblock_member(self, member):
        None


class Moderator(Member):
    def close_question(self, question):
        None

    def undelete_question(self, question):
        None

