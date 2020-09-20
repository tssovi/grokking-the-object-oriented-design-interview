from abc import ABC


class Bank:
  def __init__(self, name, bank_code):
    self.__name = name
    self.__bank_code = bank_code

  def get_bank_code(self):
    return self.__bank_code

  def add_atm(self, atm):
    None


class ATM:
  def __init__(self, id, location):
    self.__atm_id = id
    self.__location = location

    self.__cash_dispenser = CashDispenser()
    self.__keypad = Keypad()
    self.__screen = Screen()
    self.__printer = Printer()
    self.__check_deposit = CheckDeposit()
    self.__cash_deposit = CashDeposit

  def authenticate_user(self):
    None

  def make_transaction(self, customer, transaction):
    None


class CashDispenser:
  def __init__(self):
    self.__total_five_dollar_bills = 0
    self.__total_twenty_dollar_bills = 0

  def dispense_cash(self, amount):
    None

  def can_dispense_cash(self):
    None


class Keypad:
  def get_input(self):
    None


class Screen:
  def show_message(self, message):
    None

  def get_input(self):
    None


class Printer:
  def print_receipt(self, transaction):
    None


class CheckDeposit:
    def __init__(self):
        None


class CashDeposit:
    def __init__(self):
        None


class DepositSlot(ABC):
  def __init__(self):
    self.__total_amount = 0.0

  def get_total_amount(self):
    return self.__total_amount


class CheckDepositSlot(DepositSlot):
  def get_check_amount(self):
    None


class CashDepositSlot(DepositSlot):
  def receive_dollar_bill(self):
    None

