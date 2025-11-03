"""
Account Types Module - Gym Management System

This module contains all user account types and their specific behaviors.
It defines the account hierarchy with different roles: Member, Trainer,
GymManager, and Receptionist, each with their own permissions and capabilities.
"""

from abc import ABC
from datetime import datetime
from .constants import *
from .models import *


class Account(ABC):
    """
    Abstract base class for all user accounts in the system.
    
    Provides common functionality for authentication, status management,
    and basic account operations. All specific account types inherit from this class.
    """
    def __init__(self, account_id, username, password, person, status=AccountStatus.ACTIVE):
        """
        Initialize an Account object.
        
        Args:
            account_id (str): Unique identifier for the account
            username (str): Username for login
            password (str): Password for authentication
            person (Person): Person object with personal details
            status (AccountStatus): Initial account status (default: ACTIVE)
        """
        self.__account_id = account_id
        self.__username = username
        self.__password = password
        self.__status = status
        self.__person = person
        self.__creation_date = datetime.now()
        
    def reset_password(self, new_password):
        """
        Reset the account password.
        
        Args:
            new_password (str): New password to set
        """
        self.__password = new_password
        
    def get_account_id(self):
        """
        Get the unique account identifier.
        
        Returns:
            str: Account ID
        """
        return self.__account_id
        
    def get_status(self):
        """
        Get the current account status.
        
        Returns:
            AccountStatus: Current status of the account
        """
        return self.__status
        
    def update_status(self, status):
        """
        Update the account status.
        
        Args:
            status (AccountStatus): New status to set
        """
        self.__status = status


class Member(Account):
    """
    Represents a gym member account.
    
    Members can book classes, manage their membership, make payments,
    and access gym facilities based on their membership type.
    """
    
    def __init__(self, account_id, username, password, person, status=AccountStatus.ACTIVE):
        """
        Initialize a Member account.
        
        Args:
            account_id (str): Unique identifier for the member
            username (str): Username for login
            password (str): Password for authentication
            person (Person): Person object with personal details
            status (AccountStatus): Initial account status (default: ACTIVE)
        """
        super().__init__(account_id, username, password, person, status)
        self.__membership = None  # Current membership subscription
        self.__bookings = []  # List of class bookings
        self.__payment_history = []  # History of all payments made
        
    def get_membership(self):
        """
        Get the member's current membership.
        
        Returns:
            Membership: Current membership object or None
        """
        return self.__membership
        
    def set_membership(self, membership):
        """
        Set or update the member's membership.
        
        Args:
            membership (Membership): New membership to assign
        """
        self.__membership = membership
        
    def book_class(self, class_schedule):
        """
        Book a fitness class.
        
        Validates membership status and booking limits before creating a booking.
        
        Args:
            class_schedule (ClassSchedule): The class schedule to book
            
        Returns:
            bool: True if booking successful, False otherwise
        """
        # Check if member has an active membership
        if self.__membership is None or not self.__membership.is_active():
            print("Active membership required to book classes")
            return False
        
        # Check if member has reached maximum booking limit
        if len(self.__bookings) >= Constants.MAX_BOOKINGS_PER_MEMBER:
            print("Maximum booking limit reached")
            return False
        
        # Create a new booking
        booking = ClassBooking(
            booking_id=f"BK{len(self.__bookings) + 1}",
            member=self,
            class_schedule=class_schedule,
            booking_date=datetime.now()
        )
        
        # Try to add booking to the class schedule
        if class_schedule.add_booking(booking):
            self.__bookings.append(booking)
            return True
        return False
        
    def cancel_booking(self, booking):
        """
        Cancel a class booking.
        
        Args:
            booking (ClassBooking): The booking to cancel
            
        Returns:
            bool: True if cancellation successful, False if too late or failed
        """
        if booking.cancel():
            self.__bookings.remove(booking)
            return True
        return False
        
    def make_payment(self, amount, payment_method):
        """
        Make a payment for membership or services.
        
        Args:
            amount (float): Payment amount
            payment_method (str): Payment method (credit_card, cash, etc.)
            
        Returns:
            bool: True if payment successful, False otherwise
        """
        payment = Payment(
            payment_id=f"PAY{len(self.__payment_history) + 1}",
            amount=amount,
            payment_date=datetime.now(),
            payment_method=payment_method
        )
        if payment.process_payment():
            self.__payment_history.append(payment)
            return True
        return False
        
    def get_bookings(self):
        """
        Get all bookings made by this member.
        
        Returns:
            list: List of ClassBooking objects
        """
        return self.__bookings


class Trainer(Account):
    """
    Represents a gym trainer/instructor account.
    
    Trainers conduct fitness classes, have specializations,
    and manage their class schedules.
    """
    
    def __init__(self, account_id, username, password, person, specialization, status=AccountStatus.ACTIVE):
        """
        Initialize a Trainer account.
        
        Args:
            account_id (str): Unique identifier for the trainer
            username (str): Username for login
            password (str): Password for authentication
            person (Person): Person object with personal details
            specialization (str): Trainer's area of expertise (Yoga, Spinning, etc.)
            status (AccountStatus): Initial account status (default: ACTIVE)
        """
        super().__init__(account_id, username, password, person, status)
        self.__specialization = specialization  # Area of expertise
        self.__assigned_classes = []  # Classes assigned to this trainer
        self.__schedule = []  # Trainer's time schedule
        
    def assign_class(self, gym_class):
        """
        Assign a class to this trainer.
        
        Args:
            gym_class (GymClass): Class to be assigned
        """
        self.__assigned_classes.append(gym_class)
        
    def get_assigned_classes(self):
        """
        Get all classes assigned to this trainer.
        
        Returns:
            list: List of GymClass objects
        """
        return self.__assigned_classes
        
    def update_schedule(self, schedule):
        """
        Update the trainer's schedule.
        
        Args:
            schedule (list): List of time slots with start and end times
        """
        self.__schedule = schedule
        
    def is_available(self, start_time, end_time):
        """
        Check if trainer is available during a specific time slot.
        
        Args:
            start_time (datetime): Start time of the slot
            end_time (datetime): End time of the slot
            
        Returns:
            bool: True if available, False if already scheduled
        """
        # Check if the time slot conflicts with existing schedule
        for slot in self.__schedule:
            if slot['start_time'] <= start_time < slot['end_time'] or \
               slot['start_time'] < end_time <= slot['end_time']:
                return False
        return True


class GymManager(Account):
    """
    Represents a gym manager account.
    
    Managers have administrative privileges to manage members, trainers,
    equipment, class schedules, and generate reports for their branch.
    """
    
    def __init__(self, account_id, username, password, person, branch, status=AccountStatus.ACTIVE):
        """
        Initialize a GymManager account.
        
        Args:
            account_id (str): Unique identifier for the manager
            username (str): Username for login
            password (str): Password for authentication
            person (Person): Person object with personal details
            branch (Branch): Branch that this manager oversees
            status (AccountStatus): Initial account status (default: ACTIVE)
        """
        super().__init__(account_id, username, password, person, status)
        self.__branch = branch  # Branch managed by this manager
        
    def add_member(self, member):
        """
        Add a new member to the system.
        
        Args:
            member (Member): Member to be added
        """
        # Add member to the system database
        pass
        
    def remove_member(self, member):
        """
        Remove a member from the system.
        
        Args:
            member (Member): Member to be removed
        """
        # Remove member from the system database
        pass
        
    def add_trainer(self, trainer):
        """
        Add a trainer to the branch.
        
        Args:
            trainer (Trainer): Trainer to be added
        """
        self.__branch.add_trainer(trainer)
        
    def schedule_class(self, gym_class):
        """
        Schedule a class at the branch.
        
        Args:
            gym_class (GymClass): Class to be scheduled
        """
        self.__branch.schedule_class(gym_class)
        
    def add_equipment(self, equipment):
        """
        Add equipment to the branch.
        
        Args:
            equipment (Equipment): Equipment to be added
        """
        self.__branch.add_equipment(equipment)
        
    def generate_report(self, report_type, start_date, end_date):
        """
        Generate various management reports.
        
        Args:
            report_type (str): Type of report (membership, revenue, attendance)
            start_date (datetime): Start date for the report period
            end_date (datetime): End date for the report period
            
        Returns:
            Report data structure (implementation specific)
        """
        # Generate various reports (membership, revenue, attendance, etc.)
        pass
        
    def suspend_member(self, member):
        """
        Suspend a member's account and membership.
        
        Args:
            member (Member): Member to be suspended
        """
        member.update_status(AccountStatus.SUSPENDED)
        if member.get_membership():
            member.get_membership().suspend()


class Receptionist(Account):
    """
    Represents a receptionist account.
    
    Receptionists handle member check-ins, registrations,
    and payment processing at the front desk.
    """
    
    def __init__(self, account_id, username, password, person, status=AccountStatus.ACTIVE):
        """
        Initialize a Receptionist account.
        
        Args:
            account_id (str): Unique identifier for the receptionist
            username (str): Username for login
            password (str): Password for authentication
            person (Person): Person object with personal details
            status (AccountStatus): Initial account status (default: ACTIVE)
        """
        super().__init__(account_id, username, password, person, status)
        
    def check_in_member(self, member):
        """
        Check in a member at the gym entrance.
        
        Validates membership status before allowing entry.
        
        Args:
            member (Member): Member attempting to check in
            
        Returns:
            bool: True if check-in successful, False if membership invalid
        """
        if member.get_membership() and member.get_membership().is_active():
            print(f"Member {member.get_account_id()} checked in successfully")
            return True
        print("Invalid or expired membership")
        return False
        
    def register_member(self, member_details):
        """
        Register a new member.
        
        Args:
            member_details (dict): Dictionary containing member information
            
        Returns:
            Member: Newly created member object
        """
        # Register new member with provided details
        pass
        
    def process_payment(self, member, amount, payment_method):
        """
        Process a payment on behalf of a member.
        
        Args:
            member (Member): Member making the payment
            amount (float): Payment amount
            payment_method (str): Payment method
            
        Returns:
            bool: True if payment successful, False otherwise
        """
        return member.make_payment(amount, payment_method)
