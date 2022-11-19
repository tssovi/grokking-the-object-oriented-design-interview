from .card import *


class BlackjackCard(Card):
    def __init__(self, suit, face_value):
        super().__init__(suit, face_value)
        self.__game_value = face_value
        if self.__game_value > 10:
            self.__game_value = 10

    def get_game_value(self):
        return self.__game_value
