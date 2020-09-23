class Group:
    def __init__(self, id, name, description, total_members):
        self.__group_id = id
        self.__name = name
        self.__description = description
        self.__total_members = total_members
        self.__members = []

    def add_member(self, member):
        None

    def update_description(self, description):
        None


class Post:
    def __init__(self, id, text, total_likes, total_shares, owner):
        self.__post_id = id
        self.__text = text
        self.__total_likes = total_likes
        self.__total_shares = total_shares
        self.__owner = owner


class Message:
    def __init__(self, id, sent_to, body, media):
        self.__message_id = id
        self.__sent_to = sent_to
        self.__message_body = body
        self.__media = media

    def add_member(self, member):
        None


class Comment:
    def __init__(self, id, text, total_likes, owner):
        self.__comment_id = id
        self.__text = text
        self.__total_likes = total_likes
        self.__owner = owner
