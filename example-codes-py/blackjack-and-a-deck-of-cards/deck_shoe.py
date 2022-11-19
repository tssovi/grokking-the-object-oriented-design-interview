import random
from datetime import datetime
from .blackjack_card import *
from .constants import *


class Deck:
    def __init__(self):
        self.__cards = []
        self.__creation_date = datetime.date.today()
        for value in range(1, 14):
            for suit in SUIT:
                self.__cards.add(BlackjackCard(suit, value))

    def get_cards(self):
        self.__cards


class Shoe:
    def __init__(self, number_of_decks):
        self.__cards = []
        self.__number_of_decks = number_of_decks
        self.create_shoe()
        self.shuffle()

    def create_shoe(self):
        for decks in range(0, self.__number_of_decks):
            self.__cards.add(Deck().get_cards())

    def shuffle(self):
        card_count = self.__cards.size()
        for i in range(0, card_count):
            j = random.randrange(0, card_count - i - 1, 1)
            self.__cards[i], self.__cards[j] = self.__cards[j], self.__cards[i]

    # Get the next card from the shoe
    def deal_card(self):
        if self.__cards.size() == 0:
            self.create_shoe()
        return self.__cards.remove(0)

