class Team:
    def __init__(self, name, coach):
        self.__name = name
        self.__players = []
        self.__news = []
        self.__coach = coach

    def add_tournament_squad(self, tournament_squad):
        None

    def add_player(self, player):
        None

    def add_news(self, news):
        None


class TournamentSquad:
    def __init__(self):
        self.__players = []
        self.__tournament_stats = []

    def add_player(self, player):
        None


class Playing11:
    def __init__(self):
        self.__players = []
        self.__twelfth_man = None

    def add_player(self, player):
        None
