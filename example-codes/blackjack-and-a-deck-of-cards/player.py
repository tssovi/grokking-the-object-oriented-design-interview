from abc import ABC


class BasePlayer(ABC):
    def __init__(self, id, password, balance, status, person):
        self.__id = id
        self.__password = password
        self.__balance = balance
        self.__status = status
        self.__person = person
        self.__hands = []

    def reset_password(self):
        None

    def get_hands(self):
        return self.__hands

    def add_hand(self, hand):
        return self.__hands.add(hand)

    def remove_hand(self, hand):
        self.__hands.remove(hand)


class Player(BasePlayer):
    def __init__(self, id, password, balance, status, person):
        super.__init__(id, password, balance, status, person)
        self.__bet = 0
        self.__total_cash = 0


class Dealer(BasePlayer):
    def __init__(self, id, password, balance, status, person):
        super.__init__(id, password, balance, status, person)
