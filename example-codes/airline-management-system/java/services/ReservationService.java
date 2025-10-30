package services;

import models.*;
import enums.ReservationStatus;
import java.time.LocalDateTime;
import java.util.UUID;

public class ReservationService {
    
    public FlightReservation createReservation(Customer customer, FlightInstance flightInstance) {
        String reservationNumber = generateReservationNumber();
        FlightReservation reservation = new FlightReservation(
            reservationNumber,
            flightInstance,
            LocalDateTime.now(),
            customer
        );
        return reservation;
    }
    
    public boolean assignSeatsToReservation(FlightReservation reservation, 
                                           Passenger passenger, FlightSeat seat) {
        return reservation.assignSeat(passenger, seat);
    }
    
    public boolean confirmReservation(FlightReservation reservation, Payment payment) {
        if (payment.processPayment()) {
            reservation.setStatus(ReservationStatus.CONFIRMED);
            
            // Send confirmation notification
            Customer customer = reservation.getCustomer();
            EmailNotification notification = new EmailNotification(
                UUID.randomUUID().toString(),
                "Your reservation " + reservation.getReservationNumber() + " has been confirmed.",
                customer.getEmail()
            );
            notification.send(customer);
            
            return true;
        }
        return false;
    }
    
    public boolean cancelReservation(FlightReservation reservation) {
        boolean cancelled = reservation.cancel();
        
        if (cancelled) {
            // Send cancellation notification
            Customer customer = reservation.getCustomer();
            EmailNotification notification = new EmailNotification(
                UUID.randomUUID().toString(),
                "Your reservation " + reservation.getReservationNumber() + " has been cancelled.",
                customer.getEmail()
            );
            notification.send(customer);
        }
        
        return cancelled;
    }
    
    private String generateReservationNumber() {
        return "RES" + System.currentTimeMillis();
    }
}
