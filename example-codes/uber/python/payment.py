"""
Payment module for Uber ride-sharing system.

This module provides the Payment class for handling payment transactions
including processing and refunds.
"""

import uuid
from datetime import datetime
from constants import PaymentStatus


class Payment:
    """
    Represents a payment transaction in the Uber system.
    
    This class handles payment processing, tracking payment status,
    and managing refunds for ride transactions.
    
    Attributes:
        id (str): Unique identifier for the payment transaction
        amount (float): The payment amount in the local currency
        status (PaymentStatus): Current status of the payment
        method (str): Payment method used (e.g., "Credit Card", "PayPal", "Cash")
        timestamp (datetime): When the payment was created
    """
    
    def __init__(self, amount: float, method: str):
        """
        Initialize a Payment transaction.
        
        Args:
            amount (float): The payment amount
            method (str): The payment method (e.g., "Credit Card", "PayPal")
            
        Example:
            >>> payment = Payment(25.50, "Credit Card")
        """
        self.id = str(uuid.uuid4())  # Generate unique payment ID
        self.amount = amount
        self.method = method
        self.status = PaymentStatus.PENDING  # Payment starts as pending
        self.timestamp = datetime.now()  # Record creation time
    
    def process_payment(self) -> bool:
        """
        Process the payment transaction.
        
        In a real system, this would integrate with a payment gateway
        to actually process the payment. This is a simplified simulation.
        
        Returns:
            bool: True if payment was processed successfully, False otherwise
            
        Example:
            >>> payment = Payment(25.50, "Credit Card")
            >>> success = payment.process_payment()
            >>> print(payment.status)  # PaymentStatus.COMPLETED
        """
        # Simulate payment processing
        # In production, this would call a payment gateway API
        self.status = PaymentStatus.COMPLETED
        return True
    
    def refund(self) -> None:
        """
        Refund the payment.
        
        This method changes the payment status to REFUNDED.
        In a real system, this would trigger an actual refund through
        the payment gateway.
        
        Example:
            >>> payment = Payment(25.50, "Credit Card")
            >>> payment.process_payment()
            >>> payment.refund()
            >>> print(payment.status)  # PaymentStatus.REFUNDED
        """
        self.status = PaymentStatus.REFUNDED
