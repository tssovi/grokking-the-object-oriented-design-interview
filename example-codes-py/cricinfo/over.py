from datetime import datetime
from abc import ABC
from .constants import MatchResult


class Over:
    def __init__(self, number):
        self.__number = number
        self.__balls = []

    def add_ball(self, ball):
        None


class Ball:
    def __init__(self, balled_by, played_by, ball_type, wicket, runs, commentary):
        self.__balled_by = balled_by
        self.__played_by = played_by
        self.__type = ball_type

        self.__wicket = wicket
        self.__runs = runs
        self.__commentary = commentary


class Wicket:
    def __init__(self, wicket_type, player_out, caught_by, runout_by, stumped_by):
        self.__wicket_type = wicket_type
        self.__player_out = player_out
        self.__caught_by = caught_by
        self.__runout_by = runout_by
        self.__stumped_by = stumped_by


class Commentary:
    def __init__(self, text, commentator):
        self.__text = text
        self.__created_at = datetime.date.today()
        self.__created_by = commentator


class Inning:
    def __init__(self, number, start_time):
        self.__number = number
        self.__start_time = start_time
        self.__overs = []

    def add_over(self, over):
        None


# from abc import ABC, abstractmethod
class Match(ABC):
    def __init__(self, number, start_time, referee):
        self.__number = number
        self.__start_time = start_time
        self.__result = MatchResult.LIVE

        self.__teams = []
        self.__innings = []
        self.__umpires = []
        self.__referee = referee
        self.__commentators = []
        self.__match_stats = []

    def assign_stadium(self, stadium):
        None

    def assign_referee(self, referee):
        None


class ODI(Match):
    # ...
    pass


class Test(Match):
    # ...
    pass
