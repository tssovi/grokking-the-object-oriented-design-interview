# Gym Management System

## Overview
This is an object-oriented design implementation of a Gym Management System. The system handles gym operations including member management, class scheduling, trainer assignments, equipment tracking, and payment processing.

## System Requirements

The system supports the following features:

1. **Member Management**
   - Register new members with different membership types (Basic, Premium, VIP)
   - Track membership status and expiration dates
   - Suspend or cancel memberships
   - Check-in members at the gym

2. **Class Management**
   - Schedule fitness classes with trainers
   - Book classes with capacity limits
   - Cancel bookings (with late cancellation fees)
   - Track class attendance

3. **Trainer Management**
   - Assign trainers to classes
   - Track trainer schedules and availability
   - Manage trainer specializations

4. **Equipment Management**
   - Track equipment inventory
   - Monitor equipment status (available, in use, under maintenance)
   - Schedule equipment maintenance

5. **Payment Processing**
   - Process membership payments
   - Handle refunds
   - Track payment history

6. **Notifications**
   - Send membership expiry notifications
   - Notify members of class cancellations
   - Alert members when bookings are confirmed

## Main Classes

### Core Models (`models.py`)
- **Gym**: Main gym organization with multiple branches
- **Branch**: Individual gym location with equipment, trainers, and classes
- **Equipment**: Gym equipment with status tracking
- **GymClass**: Fitness class definition
- **ClassSchedule**: Scheduled instance of a class
- **Membership**: Member subscription with type and validity
- **ClassBooking**: Member's class reservation
- **Payment**: Payment transaction record
- **Notification**: System notifications

### Account Types (`account_types.py`)
- **Account**: Base class for all user accounts
- **Member**: Gym member with booking and payment capabilities
- **Trainer**: Fitness instructor with class assignments
- **GymManager**: Manager with administrative privileges
- **Receptionist**: Front desk staff for check-ins and registrations

### Services (`services.py`)
- **ClassSearch**: Search for classes by name, type, trainer, or time
- **MemberSearch**: Search for members by name, ID, or membership type
- **GymManagementSystem**: Main system orchestrator for all operations

### Constants (`constants.py`)
- Enums for membership types, statuses, equipment status, etc.
- System constants (max capacity, booking limits, fees)
- Base classes (Person, Address)

## Design Patterns Used

1. **Abstract Factory Pattern**: Account types inherit from abstract Account class
2. **Strategy Pattern**: Different membership types with varying privileges
3. **Observer Pattern**: Notification system for membership expiry and class updates
4. **Repository Pattern**: Search services for classes and members

## Usage Example

```python
from gym_management_system import *

# Create gym and branch
gym = Gym("FitLife Gym", Address("123 Main St", "City", "State", "12345", "Country"))
branch = Branch("BR001", "Downtown Branch", Address("123 Main St", "City", "State", "12345", "Country"))
gym.add_branch(branch)

# Create system
system = GymManagementSystem(gym)

# Register a member
member = Member("M001", "john_doe", "password123", Person("John Doe", address, "john@email.com", "1234567890"))
system.register_member(member)

# Create membership
system.process_membership_payment(member, MembershipType.PREMIUM, 12)

# Create a class
trainer = Trainer("T001", "jane_trainer", "pass456", Person("Jane Smith", address, "jane@email.com", "0987654321"), "Yoga")
yoga_class = GymClass("C001", "Morning Yoga", "Relaxing yoga session", 60, 20, trainer)

# Schedule the class
schedule = system.schedule_class(yoga_class, datetime(2025, 11, 4, 9, 0), datetime(2025, 11, 4, 10, 0), "Room A")

# Book the class
system.book_class(member, schedule)
```

## Notes

- This code focuses on the design aspects and is not fully executable
- Database interactions, external payment gateways, and notification services are abstracted with `pass` or `None`
- All class attributes are private (prefixed with `__`) following encapsulation principles
- The implementation uses Python 3 with type hints where appropriate
