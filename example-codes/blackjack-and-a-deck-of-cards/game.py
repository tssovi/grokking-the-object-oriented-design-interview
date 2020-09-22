from .hand import  *
from .player import *
from .deck_shoe import *


def get_bet_from_UI():
    pass


def get_user_action():
    pass


class Game:
    def __init__(self, player, dealer):
        self.__player = player
        self.__dealer = dealer
        self.__MAX_NUM_OF_DECKS = 3
        self.__shoe = Shoe(self.__MAX_NUM_OF_DECKS)

    def play_action(self, action, hand):
        switcher = {
            "hit": self.hit(hand),
            "split": self.split(hand),
            "stand pat": None,  # do nothing
            "stand": self.stand()
        }
        switcher.get(action, 'Invalid move')

    def hit(self, hand):
        self.__hand.add_card(self.__shoe.deal_card())

    def stand(self):
        dealer_score = self.__dealer.get_total_score()
        player_score = self.__player.get_total_score()
        hands = self.__player.get_hands()
        for hand in hands:
            best_score = hand.resolve_score()
            if player_score == 21:
                # blackjack, pay 3: 2 of the bet
                None
            elif player_score > dealer_score:
                # pay player equal to the bet
                None
            elif player_score < dealer_score:
                # collect the bet from the player
                None
            else:  # tie
                # bet goes back to player
                None

    def split(self, hand):
        cards = hand.get_cards()
        self.__player.add_hand(Hand(cards[0], self.__shoe.deal_card()))
        self.__player.add_hand(Hand(cards[1], self.__shoe.deal_card()))
        self.__player.remove_hand(hand)

    def start(self):
        self.__player.place_bet(get_bet_from_UI())

        player_hand = Hand(self.__shoe.deal_card(), self.__shoe.deal_card())
        self.__player.add_to_hand(player_hand)

        dealer_hand = Hand(self.__shoe.deal_card(), self.__shoe.deal_card())
        self.__dealer.add_to_hand(dealer_hand)

        while True:
            hands = self.__player.get_hands()
            for hand in hands:
                action = get_user_action(hand)
                self.play_action(action, hand)
                if action.equals("stand"):
                    break


def main():
    player = Player()
    dealer = Dealer()
    game = Game(player, dealer)
    game.start()

