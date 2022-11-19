from abc import ABC


class Search(ABC):
    def search_member(self, name):
        None

    def search_group(self, name):
        None

    def search_page(self, name):
        None

    def search_post(self, word):
        None


class SearchIndex(Search):
    def __init__(self):
        self.__member_names = {}
        self.__group_names = {}
        self.__page_titles = {}
        self.__posts = {}

    def add_member(self, member):
        if member.get_name() in self.__member_names:
            self.__member_names.get(member.get_name()).add(member)
        else:
            self.__member_names[member.get_name()] = member

    def add_group(self, group):
        None

    def add_page(self, page):
        None

    def add_post(self, post):
        None

    def search_member(self, name):
        return self.__member_names.get(name)

    def search_group(self, name):
        return self.__group_names.get(name)

    def search_page(self, name):
        return self.__page_titles.get(name)

    def search_post(self, word):
        return self.__posts.get(word)
