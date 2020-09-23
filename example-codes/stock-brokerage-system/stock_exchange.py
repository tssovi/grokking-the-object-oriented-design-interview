from .order import Order


class StockExchange:
    # singleton, used for restricting to create only one instance
    instance = None

    class __OnlyOne:
        def __init__(self):
            None

    def __init__(self):
        if not StockExchange.instance:
            StockExchange.instance = StockExchange.__OnlyOne()

    def place_order(self, order):
        return_status = self.get_instance().submit_order(Order)
        return return_status
