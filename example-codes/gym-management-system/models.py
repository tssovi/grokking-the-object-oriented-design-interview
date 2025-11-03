"""
Models Module - Gym Management System

This module contains the core domain models for the gym management system.
It includes classes for gym organization, equipment, classes, memberships,
bookings, payments, and notifications.
"""

from abc import ABC, abstractmethod
from datetime import datetime, timedelta
from .constants import *


class Gym:
    """
    Represents the main gym organization.
    
    A gym can have multiple branches across different locations.
    This is the top-level entity in the system hierarchy.
    """
    
    def __init__(self, name, address):
        """
        Initialize a Gym object.
        
        Args:
            name (str): Name of the gym organization
            address (Address): Main address of the gym headquarters
        """
        self.__name = name
        self.__address = address
        self.__branches = []  # List of all gym branches
        
    def add_branch(self, branch):
        """
        Add a new branch to the gym organization.
        
        Args:
            branch (Branch): Branch object to be added
        """
        self.__branches.append(branch)
        
    def get_branches(self):
        """
        Get all branches of the gym.
        
        Returns:
            list: List of Branch objects
        """
        return self.__branches


class Branch:
    """
    Represents a physical gym branch/location.
    
    Each branch has its own equipment, trainers, and class schedules.
    Members can use facilities at any branch of the gym.
    """
    
    def __init__(self, branch_id, name, address):
        """
        Initialize a Branch object.
        
        Args:
            branch_id (str): Unique identifier for the branch
            name (str): Name of the branch (e.g., "Downtown Branch")
            address (Address): Physical address of the branch
        """
        self.__branch_id = branch_id
        self.__name = name
        self.__address = address
        self.__equipment = []  # List of equipment at this branch
        self.__trainers = []   # List of trainers working at this branch
        self.__classes = []    # List of classes offered at this branch
        
    def add_equipment(self, equipment):
        """
        Add equipment to this branch.
        
        Args:
            equipment (Equipment): Equipment object to be added
        """
        self.__equipment.append(equipment)
        
    def add_trainer(self, trainer):
        """
        Add a trainer to this branch.
        
        Args:
            trainer (Trainer): Trainer object to be added
        """
        self.__trainers.append(trainer)
        
    def schedule_class(self, gym_class):
        """
        Schedule a fitness class at this branch.
        
        Args:
            gym_class (GymClass): GymClass object to be scheduled
        """
        self.__classes.append(gym_class)


class Equipment:
    """
    Represents gym equipment (treadmill, weights, machines, etc.).
    
    Tracks equipment status, maintenance schedule, and availability.
    """
    
    def __init__(self, equipment_id, name, equipment_type, purchase_date):
        """
        Initialize an Equipment object.
        
        Args:
            equipment_id (str): Unique identifier for the equipment
            name (str): Name/model of the equipment
            equipment_type (str): Type/category of equipment
            purchase_date (datetime): Date when equipment was purchased
        """
        self.__equipment_id = equipment_id
        self.__name = name
        self.__type = equipment_type
        self.__purchase_date = purchase_date
        self.__status = EquipmentStatus.AVAILABLE
        self.__last_maintenance_date = None
        
    def update_status(self, status):
        """
        Update the status of the equipment.
        
        Args:
            status (EquipmentStatus): New status for the equipment
        """
        self.__status = status
        
    def schedule_maintenance(self, date):
        """
        Schedule maintenance for the equipment.
        
        Args:
            date (datetime): Date when maintenance is scheduled
        """
        self.__last_maintenance_date = date
        self.__status = EquipmentStatus.UNDER_MAINTENANCE


class GymClass:
    """
    Represents a fitness class type (Yoga, Spinning, Zumba, etc.).
    
    A class definition that can be scheduled multiple times with different
    time slots. Contains class details, capacity, and assigned trainer.
    """
    
    def __init__(self, class_id, name, description, duration, capacity, trainer):
        """
        Initialize a GymClass object.
        
        Args:
            class_id (str): Unique identifier for the class
            name (str): Name of the class (e.g., "Morning Yoga")
            description (str): Description of the class
            duration (int): Duration of the class in minutes
            capacity (int): Maximum number of participants
            trainer (Trainer): Trainer assigned to this class
        """
        self.__class_id = class_id
        self.__name = name
        self.__description = description
        self.__duration = duration
        self.__capacity = capacity
        self.__trainer = trainer
        self.__schedule = []  # List of scheduled instances of this class
        self.__status = ClassStatus.SCHEDULED
        
    def add_schedule(self, schedule):
        """
        Add a scheduled instance of this class.
        
        Args:
            schedule (ClassSchedule): Schedule object for this class
        """
        self.__schedule.append(schedule)
        
    def update_status(self, status):
        """
        Update the status of the class.
        
        Args:
            status (ClassStatus): New status for the class
        """
        self.__status = status
        
    def get_capacity(self):
        """
        Get the maximum capacity of the class.
        
        Returns:
            int: Maximum number of participants allowed
        """
        return self.__capacity
        
    def get_class_id(self):
        """
        Get the unique identifier of the class.
        
        Returns:
            str: Class ID
        """
        return self.__class_id


class ClassSchedule:
    """
    Represents a scheduled instance of a fitness class.
    
    Links a GymClass to a specific time slot and room, and manages
    bookings for that particular session.
    """
    
    def __init__(self, schedule_id, gym_class, start_time, end_time, room):
        """
        Initialize a ClassSchedule object.
        
        Args:
            schedule_id (str): Unique identifier for this schedule
            gym_class (GymClass): The class being scheduled
            start_time (datetime): Start time of the class
            end_time (datetime): End time of the class
            room (str): Room/location where class will be held
        """
        self.__schedule_id = schedule_id
        self.__gym_class = gym_class
        self.__start_time = start_time
        self.__end_time = end_time
        self.__room = room
        self.__bookings = []  # List of bookings for this scheduled class
        
    def add_booking(self, booking):
        """
        Add a booking to this scheduled class.
        
        Args:
            booking (ClassBooking): Booking to be added
            
        Returns:
            bool: True if booking was added, False if class is full
        """
        # Check if class has reached capacity
        if len(self.__bookings) >= self.__gym_class.get_capacity():
            return False
        self.__bookings.append(booking)
        return True
        
    def remove_booking(self, booking):
        """
        Remove a booking from this scheduled class.
        
        Args:
            booking (ClassBooking): Booking to be removed
            
        Returns:
            bool: True if booking was removed, False if not found
        """
        if booking in self.__bookings:
            self.__bookings.remove(booking)
            return True
        return False
        
    def get_available_slots(self):
        """
        Get the number of available slots in this class.
        
        Returns:
            int: Number of available slots remaining
        """
        return self.__gym_class.get_capacity() - len(self.__bookings)


class Membership:
    """
    Represents a member's gym membership subscription.
    
    Tracks membership type, validity period, and status. Handles
    renewal, suspension, and cancellation operations.
    """
    
    def __init__(self, membership_id, member, membership_type, start_date, duration_months):
        """
        Initialize a Membership object.
        
        Args:
            membership_id (str): Unique identifier for the membership
            member (Member): Member who owns this membership
            membership_type (MembershipType): Type of membership (BASIC/PREMIUM/VIP)
            start_date (datetime): Start date of the membership
            duration_months (int): Duration of membership in months
        """
        self.__membership_id = membership_id
        self.__member = member
        self.__type = membership_type
        self.__start_date = start_date
        # Calculate end date (approximating 30 days per month)
        self.__end_date = start_date + timedelta(days=duration_months * 30)
        self.__status = MembershipStatus.ACTIVE
        
    def renew(self, duration_months):
        """
        Renew the membership for additional months.
        
        Args:
            duration_months (int): Number of months to extend the membership
        """
        # If membership has expired, start from current date
        if self.__status == MembershipStatus.EXPIRED:
            self.__start_date = datetime.now()
        # Extend the end date
        self.__end_date = self.__end_date + timedelta(days=duration_months * 30)
        self.__status = MembershipStatus.ACTIVE
        
    def suspend(self):
        """
        Suspend the membership temporarily.
        """
        self.__status = MembershipStatus.SUSPENDED
        
    def cancel(self):
        """
        Cancel the membership permanently.
        """
        self.__status = MembershipStatus.CANCELED
        
    def is_active(self):
        """
        Check if the membership is currently active.
        
        Returns:
            bool: True if membership is active, False otherwise
        """
        # Check if membership has expired based on end date
        if datetime.now() > self.__end_date:
            self.__status = MembershipStatus.EXPIRED
            return False
        return self.__status == MembershipStatus.ACTIVE
        
    def get_membership_id(self):
        """
        Get the unique identifier of the membership.
        
        Returns:
            str: Membership ID
        """
        return self.__membership_id


class ClassBooking:
    """
    Represents a member's booking for a scheduled class.
    
    Manages booking status and handles cancellation with late fee logic.
    """
    
    def __init__(self, booking_id, member, class_schedule, booking_date):
        """
        Initialize a ClassBooking object.
        
        Args:
            booking_id (str): Unique identifier for the booking
            member (Member): Member who made the booking
            class_schedule (ClassSchedule): The scheduled class being booked
            booking_date (datetime): Date when booking was made
        """
        self.__booking_id = booking_id
        self.__member = member
        self.__class_schedule = class_schedule
        self.__booking_date = booking_date
        self.__status = BookingStatus.CONFIRMED
        
    def cancel(self):
        """
        Cancel the booking.
        
        Checks if cancellation is within the allowed time limit.
        If cancelled too late, a fee may be charged.
        
        Returns:
            bool: True if cancellation successful, False if too late
        """
        # Calculate hours until class starts
        hours_until_class = (self.__class_schedule.get_start_time() - datetime.now()).total_seconds() / 3600
        
        # Check if cancellation is within allowed time limit
        if hours_until_class < Constants.CANCELLATION_HOURS_LIMIT:
            # Too late to cancel without fee - charge late cancellation fee
            return False
        
        self.__status = BookingStatus.CANCELED
        return True
        
    def mark_completed(self):
        """
        Mark the booking as completed (member attended the class).
        """
        self.__status = BookingStatus.COMPLETED
        
    def mark_no_show(self):
        """
        Mark the booking as no-show (member did not attend).
        """
        self.__status = BookingStatus.NO_SHOW


class Payment:
    """
    Represents a payment transaction.
    
    Handles payment processing and refunds for memberships,
    class bookings, and other gym services.
    """
    
    def __init__(self, payment_id, amount, payment_date, payment_method):
        """
        Initialize a Payment object.
        
        Args:
            payment_id (str): Unique identifier for the payment
            amount (float): Payment amount
            payment_date (datetime): Date when payment was made
            payment_method (str): Payment method (credit_card, cash, etc.)
        """
        self.__payment_id = payment_id
        self.__amount = amount
        self.__payment_date = payment_date
        self.__payment_method = payment_method
        self.__status = PaymentStatus.PENDING
        
    def process_payment(self):
        """
        Process the payment transaction.
        
        In a real system, this would integrate with a payment gateway.
        
        Returns:
            bool: True if payment successful, False otherwise
        """
        # Payment processing logic would go here
        # (e.g., call to payment gateway API)
        self.__status = PaymentStatus.COMPLETED
        return True
        
    def refund(self):
        """
        Process a refund for this payment.
        
        Returns:
            bool: True if refund successful, False otherwise
        """
        self.__status = PaymentStatus.REFUNDED
        return True


class Notification:
    """
    Represents a notification sent to users.
    
    Handles various types of notifications such as membership expiry,
    class reminders, booking confirmations, etc.
    """
    
    def __init__(self, notification_id, recipient, message, notification_type):
        """
        Initialize a Notification object.
        
        Args:
            notification_id (str): Unique identifier for the notification
            recipient (Account): User who will receive the notification
            message (str): Notification message content
            notification_type (str): Type of notification (email, SMS, push, etc.)
        """
        self.__notification_id = notification_id
        self.__recipient = recipient
        self.__message = message
        self.__type = notification_type
        self.__sent_date = None
        
    def send(self):
        """
        Send the notification to the recipient.
        
        In a real system, this would integrate with email/SMS services.
        """
        self.__sent_date = datetime.now()
        # Send notification logic would go here
        # (e.g., call to email service, SMS gateway, push notification service)
        pass
