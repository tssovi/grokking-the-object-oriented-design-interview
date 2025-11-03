class MenuItem:
    def __init__(self, id, title, description, price):
        self.__menu_item_id = id
        self.__title = title
        self.__description = description
        self.__price = price

    def update_price(self, price):
        None


class MenuSection:
    def __init__(self, id, title, description):
        self.__menu_section_id = id
        self.__title = title
        self.__description = description
        self.__menu_items = []

    def add_menu_item(self, menu_item):
        None


class Menu:
    def __init__(self, id, title, description):
        self.__menu_id = id
        self.__title = title
        self.__description = description
        self.__menu_sections = []

    def add_menu_section(self, menu_section):
        None

    def print(self):
        None
