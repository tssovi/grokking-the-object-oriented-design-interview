from datetime import datetime

class Photo:
    def __init__(self, id, path, member):
        self.__photo_id = id
        self.__photo_path = path
        self.__creation_date = datetime.now()
        self.__creating_member = member

    def delete(self):
        None


class Bounty:
    def __init__(self, reputation, expiry):
        self.__reputation = reputation
        self.__expiry = expiry

    def modify_reputation(self, reputation):
        None

