class Kitchen:
    def __init__(self, name):
        self.__name = name
        self.__chefs = []

    def assign_chef(self, chef):
        None


class Branch:
    def __init__(self, name, location, kitchen):
        self.__name = name
        self.__location = location
        self.__kitchen = kitchen

    def add_table_chart(self):
        None


class Restaurant:
    def __init__(self, name):
        self.__name = name
        self.__branches = []

    def add_branch(self, branch):
        None


class TableChart:
    def __init__(self, id):
        self.__table_chart_id = id
        self.__table_chart_image = []

    def print(self):
        None
