from datetime import datetime
from abc import ABC
from .constants import *

class Search(ABC):
    def search(self, query):
        None


class Question(Search):
    def __init__(self, title, description, bounty, asking_member):
        self.__title = title
        self.__description = description
        self.__view_count = 0
        self.__vote_count = 0
        self.__creation_time = datetime.now()
        self.__update_time = datetime.now()
        self.__status = QuestionStatus.OPEN
        self.__closing_remark = QuestionClosingRemark.DUPLICATE

        self.__bounty = bounty
        self.__asking_member = asking_member
        self.__photos = []
        self.__comments = []
        self.__answers = []

    def close(self):
        None

    def undelete(self):
        None

    def add_comment(self, comment):
        None

    def add_bounty(self, bounty):
        None

    def search(self, query):
        # return all questions containing the string query in their title or description.
        None


class Comment:
    def __init__(self, text, member):
        self.__text = text
        self.__creation_time = datetime.now()
        self.__flag_count = 0
        self.__vote_count = 0
        self.__asking_member = member

    def increment_vote_count(self):
        None


class Answer:
    def __init__(self, text, member):
        self.__answer_text = text
        self.__accepted = False
        self.__vote_count = 0
        self.__flag_count = 0
        self.__creation_time = datetime.now()
        self.__creating_member = member
        self.__photos = []

    def increment_vote_count(self):
        None

