from enum import Enum


class QuestionStatus(Enum):
    OPEN, CLOSED, ON_HOLD, DELETED = 1, 2, 3, 4


class QuestionClosingRemark(Enum):
    DUPLICATE, OFF_TOPIC, TOO_BROAD, NOT_CONSTRUCTIVE, NOT_A_REAL_QUESTION, PRIMARILY_OPINION_BASED = 1, 2, 3, 4, 5, 6


class AccountStatus(Enum):
    ACTIVE, CLOSED, CANCELED, BLACKLISTED, BLOCKED = 1, 2, 3, 4, 5

