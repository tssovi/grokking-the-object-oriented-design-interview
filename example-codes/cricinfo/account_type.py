# For simplicity, we are not defining getter and setter functions. The reader can
# assume that all class attributes are private and accessed through their respective
# public getter methods and modified only through their public methods function.

class Player:
    def __init__(self, person):
        self.__person = person
        self.__contracts = []

    def add_contract(self, contract):
        None


class Admin:
    def __init__(self, person):
        self.__person = person

    def add_match(self, match):
        None

    def add_team(self, team):
        None

    def add_tournament(self, tournament):
        None


class Umpire:
    def __init__(self, person):
        self.__person = person

    def assign_match(self, match):
        None


class Referee:
    def __init__(self, person):
        self.__person = person

    def assign_match(self, match):
        None


class Commentator:
    def __init__(self, person):
        self.__person = person

    def assign_match(self, match):
        None
