from datetime import datetime


class Badge:
    def __init__(self, name, description):
        self.__name = name
        self.__description = description


class Tag:
    def __init__(self, name, description):
        self.__name = name
        self.__description = description
        self.__daily_asked_frequency = 0
        self.__weekly_asked_frequency = 0


class Notification:
    def __init__(self, id, content):
        self.__notification_id = id
        self.__created_on = datetime.datetime.now()
        self.__content = content

    def send_notification(self):
        None

