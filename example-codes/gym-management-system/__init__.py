"""
Gym Management System - Object Oriented Design

This package contains the implementation of a gym management system
following object-oriented design principles.

Modules:
    - constants: Enums, constants, and base classes
    - models: Core domain models (Gym, Branch, Equipment, Classes, etc.)
    - account_types: User account types (Member, Trainer, Manager, Receptionist)
    - services: Business logic and search services
"""

from .constants import (
    MembershipType,
    MembershipStatus,
    EquipmentStatus,
    ClassStatus,
    BookingStatus,
    PaymentStatus,
    AccountStatus,
    Address,
    Person,
    Constants
)

from .models import (
    Gym,
    Branch,
    Equipment,
    GymClass,
    ClassSchedule,
    Membership,
    ClassBooking,
    Payment,
    Notification
)

from .account_types import (
    Account,
    Member,
    Trainer,
    GymManager,
    Receptionist
)

from .services import (
    Search,
    ClassSearch,
    MemberSearch,
    GymManagementSystem
)

__all__ = [
    # Constants
    'MembershipType', 'MembershipStatus', 'EquipmentStatus', 'ClassStatus',
    'BookingStatus', 'PaymentStatus', 'AccountStatus', 'Address', 'Person', 'Constants',
    
    # Models
    'Gym', 'Branch', 'Equipment', 'GymClass', 'ClassSchedule',
    'Membership', 'ClassBooking', 'Payment', 'Notification',
    
    # Account Types
    'Account', 'Member', 'Trainer', 'GymManager', 'Receptionist',
    
    # Services
    'Search', 'ClassSearch', 'MemberSearch', 'GymManagementSystem'
]
