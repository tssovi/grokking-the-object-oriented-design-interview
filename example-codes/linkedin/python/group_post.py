class Group:
    def __init__(self, name, description):
        self.__name = name
        self.__description = description
        self.__total_members = 0
        self.__members = []

    def add_member(self, member):
        None

    def update_description(self, description):
        None


class Post:
    def __init__(self, text, owner):
        self.__text = text
        self.__total_likes = 0
        self.__total_shares = 0
        self.__owner = owner


class Message:
    def __init__(self, sent_to, message_body, media):
        self.__sent_to = sent_to
        self.__message_body = message_body
        self.__media = media
