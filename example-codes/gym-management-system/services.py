"""
Services Module - Gym Management System

This module contains business logic services and search functionality.
It includes the main GymManagementSystem orchestrator and various
search services for classes and members.
"""

from abc import ABC, abstractmethod
from datetime import datetime
from .constants import *
from .models import *


class Search(ABC):
    """
    Abstract base class for search functionality.
    
    Defines the interface for searching different entities in the system.
    """
    @abstractmethod
    def search_by_name(self, name):
        """
        Search entities by name.
        
        Args:
            name (str): Name to search for
            
        Returns:
            list: List of matching entities
        """
        pass
        
    @abstractmethod
    def search_by_type(self, search_type):
        """
        Search entities by type.
        
        Args:
            search_type: Type to search for
            
        Returns:
            list: List of matching entities
        """
        pass


class ClassSearch(Search):
    """
    Search service for fitness classes.
    
    Provides various search methods to find classes by name, type,
    trainer, or time slot.
    """
    
    def __init__(self):
        """
        Initialize ClassSearch with empty search indexes.
        """
        self.__classes_by_name = {}  # Index of classes by name
        self.__classes_by_type = {}  # Index of classes by type
        self.__classes_by_trainer = {}  # Index of classes by trainer
        
    def search_by_name(self, name):
        """
        Search classes by name.
        
        Args:
            name (str): Class name to search for
            
        Returns:
            list: List of GymClass objects matching the name
        """
        return self.__classes_by_name.get(name, [])
        
    def search_by_type(self, class_type):
        """
        Search classes by type.
        
        Args:
            class_type (str): Type of class (e.g., "Cardio", "Strength")
            
        Returns:
            list: List of GymClass objects of the specified type
        """
        return self.__classes_by_type.get(class_type, [])
        
    def search_by_trainer(self, trainer):
        """
        Search classes by trainer.
        
        Args:
            trainer (Trainer): Trainer object to search for
            
        Returns:
            list: List of GymClass objects taught by the trainer
        """
        return self.__classes_by_trainer.get(trainer, [])
        
    def search_by_time(self, start_time, end_time):
        """
        Search for classes available in a specific time range.
        
        Args:
            start_time (datetime): Start of the time range
            end_time (datetime): End of the time range
            
        Returns:
            list: List of ClassSchedule objects in the time range
        """
        # Return classes available in the given time range
        pass


class MemberSearch(Search):
    """
    Search service for gym members.
    
    Provides various search methods to find members by name, ID,
    or membership type.
    """
    
    def __init__(self):
        """
        Initialize MemberSearch with empty search indexes.
        """
        self.__members_by_name = {}  # Index of members by name
        self.__members_by_id = {}  # Index of members by ID
        self.__members_by_membership_type = {}  # Index by membership type
        
    def search_by_name(self, name):
        """
        Search members by name.
        
        Args:
            name (str): Member name to search for
            
        Returns:
            list: List of Member objects matching the name
        """
        return self.__members_by_name.get(name, [])
        
    def search_by_type(self, membership_type):
        """
        Search members by membership type.
        
        Args:
            membership_type (MembershipType): Type of membership
            
        Returns:
            list: List of Member objects with the specified membership type
        """
        return self.__members_by_membership_type.get(membership_type, [])
        
    def search_by_id(self, member_id):
        """
        Search for a member by ID.
        
        Args:
            member_id (str): Unique member identifier
            
        Returns:
            Member: Member object if found, None otherwise
        """
        return self.__members_by_id.get(member_id)


class GymManagementSystem:
    """
    Main orchestrator for the gym management system.
    
    This class coordinates all system operations including member registration,
    class scheduling, booking management, payment processing, and notifications.
    It serves as the central point for all business logic.
    """
    
    def __init__(self, gym):
        """
        Initialize the GymManagementSystem.
        
        Args:
            gym (Gym): The gym organization this system manages
        """
        self.__gym = gym
        self.__members = []  # List of all registered members
        self.__trainers = []  # List of all registered trainers
        self.__managers = []  # List of all managers
        self.__class_search = ClassSearch()  # Search service for classes
        self.__member_search = MemberSearch()  # Search service for members
        
    def register_member(self, member):
        """
        Register a new member in the system.
        
        Args:
            member (Member): Member to be registered
        """
        self.__members.append(member)
        
    def register_trainer(self, trainer):
        """
        Register a new trainer in the system.
        
        Args:
            trainer (Trainer): Trainer to be registered
        """
        self.__trainers.append(trainer)
        
    def create_membership(self, member, membership_type, duration_months):
        """
        Create a new membership for a member.
        
        Args:
            member (Member): Member to create membership for
            membership_type (MembershipType): Type of membership
            duration_months (int): Duration in months
            
        Returns:
            Membership: Newly created membership object
        """
        membership = Membership(
            membership_id=f"MEM{len(self.__members)}",
            member=member,
            membership_type=membership_type,
            start_date=datetime.now(),
            duration_months=duration_months
        )
        member.set_membership(membership)
        return membership
        
    def schedule_class(self, gym_class, start_time, end_time, room):
        """
        Schedule a class at a specific time and location.
        
        Args:
            gym_class (GymClass): The class to schedule
            start_time (datetime): Start time of the class
            end_time (datetime): End time of the class
            room (str): Room where class will be held
            
        Returns:
            ClassSchedule: Newly created schedule object
        """
        schedule = ClassSchedule(
            schedule_id=f"SCH{datetime.now().timestamp()}",
            gym_class=gym_class,
            start_time=start_time,
            end_time=end_time,
            room=room
        )
        gym_class.add_schedule(schedule)
        return schedule
        
    def book_class(self, member, class_schedule):
        """
        Book a class for a member.
        
        Args:
            member (Member): Member booking the class
            class_schedule (ClassSchedule): Schedule to book
            
        Returns:
            bool: True if booking successful, False otherwise
        """
        return member.book_class(class_schedule)
        
    def cancel_booking(self, member, booking):
        """
        Cancel a member's class booking.
        
        Args:
            member (Member): Member canceling the booking
            booking (ClassBooking): Booking to cancel
            
        Returns:
            bool: True if cancellation successful, False otherwise
        """
        return member.cancel_booking(booking)
        
    def process_membership_payment(self, member, membership_type, duration_months):
        """
        Process payment and create membership for a member.
        
        Calculates the fee, processes payment, and creates the membership
        if payment is successful.
        
        Args:
            member (Member): Member purchasing the membership
            membership_type (MembershipType): Type of membership to purchase
            duration_months (int): Duration in months
            
        Returns:
            bool: True if payment and membership creation successful
        """
        # Calculate amount based on membership type and duration
        amount = self.calculate_membership_fee(membership_type, duration_months)
        
        # Process payment and create membership if successful
        if member.make_payment(amount, "credit_card"):
            self.create_membership(member, membership_type, duration_months)
            return True
        return False
        
    def calculate_membership_fee(self, membership_type, duration_months):
        """
        Calculate the membership fee based on type and duration.
        
        Args:
            membership_type (MembershipType): Type of membership
            duration_months (int): Duration in months
            
        Returns:
            float: Total membership fee
        """
        # Base monthly rates for each membership type
        base_rates = {
            MembershipType.BASIC: 30.0,
            MembershipType.PREMIUM: 50.0,
            MembershipType.VIP: 100.0
        }
        return base_rates.get(membership_type, 30.0) * duration_months
        
    def send_notification(self, recipient, message, notification_type):
        """
        Send a notification to a user.
        
        Args:
            recipient (Account): User to receive the notification
            message (str): Notification message
            notification_type (str): Type of notification (email, SMS, etc.)
        """
        notification = Notification(
            notification_id=f"NOT{datetime.now().timestamp()}",
            recipient=recipient,
            message=message,
            notification_type=notification_type
        )
        notification.send()
        
    def check_expired_memberships(self):
        """
        Check for expired memberships and send notifications.
        
        Scans all members, identifies those with expired memberships,
        and sends renewal notifications.
        
        Returns:
            list: List of members with expired memberships
        """
        expired_members = []
        for member in self.__members:
            membership = member.get_membership()
            if membership and not membership.is_active():
                expired_members.append(member)
                # Send expiry notification
                self.send_notification(
                    member,
                    "Your membership has expired. Please renew to continue using gym facilities.",
                    "membership_expiry"
                )
        return expired_members
        
    def get_class_attendance(self, class_schedule):
        """
        Get attendance statistics for a scheduled class.
        
        Args:
            class_schedule (ClassSchedule): The scheduled class
            
        Returns:
            dict: Attendance statistics (total bookings, attended, no-shows)
        """
        # Return attendance statistics for a class
        pass
        
    def generate_revenue_report(self, start_date, end_date):
        """
        Generate a revenue report for a specific period.
        
        Args:
            start_date (datetime): Start date of the report period
            end_date (datetime): End date of the report period
            
        Returns:
            dict: Revenue report with breakdown by source
        """
        # Generate revenue report for the given period
        pass
