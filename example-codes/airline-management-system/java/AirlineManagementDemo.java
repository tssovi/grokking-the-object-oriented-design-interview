import models.*;
import enums.*;
import services.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;

public class AirlineManagementDemo {
    
    public static void main(String[] args) {
        System.out.println("=== Airline Management System Demo ===\n");
        
        // 1. Create Airports
        System.out.println("1. Creating Airports...");
        Address jfkAddress = new Address("JFK Airport", "New York", "NY", "11430", "USA");
        Airport jfk = new Airport("John F Kennedy International", jfkAddress, "JFK");
        
        Address lhrAddress = new Address("Longford", "London", "England", "TW6", "UK");
        Airport lhr = new Airport("London Heathrow", lhrAddress, "LHR");
        
        System.out.println("   Created: " + jfk.getName() + " (" + jfk.getCode() + ")");
        System.out.println("   Created: " + lhr.getName() + " (" + lhr.getCode() + ")\n");
        
        // 2. Create Aircraft
        System.out.println("2. Creating Aircraft...");
        Aircraft boeing747 = new Aircraft("Boeing 747", "747-400", 2010);
        
        // Add seats to aircraft
        boeing747.addSeat(new Seat("1A", SeatType.REGULAR, SeatClass.FIRST_CLASS));
        boeing747.addSeat(new Seat("1B", SeatType.REGULAR, SeatClass.FIRST_CLASS));
        boeing747.addSeat(new Seat("2A", SeatType.REGULAR, SeatClass.BUSINESS));
        boeing747.addSeat(new Seat("2B", SeatType.REGULAR, SeatClass.BUSINESS));
        boeing747.addSeat(new Seat("10A", SeatType.REGULAR, SeatClass.ECONOMY));
        boeing747.addSeat(new Seat("10B", SeatType.REGULAR, SeatClass.ECONOMY));
        boeing747.addSeat(new Seat("10C", SeatType.EMERGENCY_EXIT, SeatClass.ECONOMY_PLUS));
        
        System.out.println("   Created: " + boeing747.getName() + " with " + 
                          boeing747.getSeats().size() + " seats\n");
        
        // 3. Create Flight
        System.out.println("3. Creating Flight...");
        Flight flight = new Flight("BA212", jfk, lhr, 420); // 7 hours
        jfk.addFlight(flight);
        
        // Add weekly schedule
        WeeklySchedule schedule = new WeeklySchedule(DayOfWeek.MONDAY, LocalTime.of(10, 0));
        flight.addWeeklySchedule(schedule);
        
        System.out.println("   Created: Flight " + flight.getFlightNumber() + 
                          " from " + jfk.getCode() + " to " + lhr.getCode() + "\n");
        
        // 4. Create Flight Instance
        System.out.println("4. Creating Flight Instance...");
        FlightInstance flightInstance = new FlightInstance(
            flight,
            LocalDateTime.of(2024, 12, 25, 10, 0),
            "A1",
            FlightStatus.SCHEDULED,
            boeing747
        );
        flight.addFlightInstance(flightInstance);
        
        System.out.println("   Created: Flight instance for " + 
                          flightInstance.getDepartureTime() + " at gate " + 
                          flightInstance.getGate() + "\n");
        
        // 5. Create Pilot and Crew
        System.out.println("5. Assigning Pilot and Crew...");
        Account pilotAccount = new Account("pilot1", "pass123");
        Address pilotAddress = new Address("123 Pilot St", "New York", "NY", "10001", "USA");
        Pilot pilot = new Pilot("Captain Smith", pilotAddress, "pilot@airline.com", 
                               "555-1111", pilotAccount, "PIL123456");
        
        Account crewAccount = new Account("crew1", "pass123");
        Address crewAddress = new Address("456 Crew Ave", "New York", "NY", "10002", "USA");
        Crew crew = new Crew("Jane Doe", crewAddress, "crew@airline.com", 
                            "555-2222", crewAccount);
        
        flightInstance.assignPilot(pilot);
        flightInstance.assignCrew(crew);
        
        System.out.println("   Assigned: " + pilot.getName() + " as pilot");
        System.out.println("   Assigned: " + crew.getName() + " as crew\n");
        
        // 6. Create Customer
        System.out.println("6. Creating Customer...");
        Account customerAccount = new Account("customer1", "password123");
        Address customerAddress = new Address("789 Customer Blvd", "Boston", "MA", "02101", "USA");
        Customer customer = new Customer("John Doe", customerAddress, "john@email.com", 
                                        "555-3333", customerAccount, "FF123456");
        
        System.out.println("   Created: Customer " + customer.getName() + 
                          " (Frequent Flyer: " + customer.getFrequentFlyerNumber() + ")\n");
        
        // 7. Search for Flights
        System.out.println("7. Searching for Flights...");
        FlightSearch searchService = new FlightSearch();
        List<FlightInstance> searchResults = searchService.searchFlights(
            jfk, lhr, LocalDate.of(2024, 12, 25)
        );
        
        System.out.println("   Found " + searchResults.size() + " flight(s)");
        if (!searchResults.isEmpty()) {
            FlightInstance found = searchResults.get(0);
            System.out.println("   Flight: " + found.getFlight().getFlightNumber() + 
                             " departing at " + found.getDepartureTime() + "\n");
        }
        
        // 8. Create Reservation
        System.out.println("8. Creating Reservation...");
        ReservationService reservationService = new ReservationService();
        FlightReservation reservation = reservationService.createReservation(customer, flightInstance);
        
        System.out.println("   Created: Reservation " + reservation.getReservationNumber() + "\n");
        
        // 9. Add Passengers and Assign Seats
        System.out.println("9. Adding Passengers and Assigning Seats...");
        Passenger passenger1 = new Passenger("John Doe", "P123456", new Date());
        Passenger passenger2 = new Passenger("Jane Doe", "P789012", new Date());
        
        List<FlightSeat> availableSeats = flightInstance.getAvailableSeats();
        if (availableSeats.size() >= 2) {
            reservationService.assignSeatsToReservation(reservation, passenger1, availableSeats.get(0));
            reservationService.assignSeatsToReservation(reservation, passenger2, availableSeats.get(1));
            
            System.out.println("   Assigned: " + passenger1.getName() + " to seat " + 
                             availableSeats.get(0).getSeatNumber());
            System.out.println("   Assigned: " + passenger2.getName() + " to seat " + 
                             availableSeats.get(1).getSeatNumber() + "\n");
        }
        
        // 10. Calculate Total Fare
        System.out.println("10. Calculating Total Fare...");
        double totalFare = reservation.calculateTotalFare();
        System.out.println("   Total Fare: $" + totalFare + "\n");
        
        // 11. Process Payment
        System.out.println("11. Processing Payment...");
        Payment payment = new CreditCardPayment(
            totalFare,
            "TXN" + System.currentTimeMillis(),
            "4111111111111111",
            "John Doe",
            "12/25",
            "123"
        );
        
        boolean paymentSuccess = reservationService.confirmReservation(reservation, payment);
        System.out.println("   Payment Status: " + (paymentSuccess ? "SUCCESS" : "FAILED"));
        System.out.println("   Reservation Status: " + reservation.getStatus() + "\n");
        
        // 12. Create Itinerary
        System.out.println("12. Creating Itinerary...");
        Itinerary itinerary = new Itinerary(
            customer.getAccount().getId(),
            jfk,
            lhr,
            LocalDateTime.now()
        );
        itinerary.makeReservation(reservation);
        customer.addItinerary(itinerary);
        
        System.out.println("   Created: Itinerary from " + itinerary.getStartingAirport().getCode() + 
                          " to " + itinerary.getFinalAirport().getCode());
        System.out.println("   Total Reservations: " + itinerary.getReservations().size() + "\n");
        
        // 13. Display Summary
        System.out.println("=== Booking Summary ===");
        System.out.println("Customer: " + customer.getName());
        System.out.println("Flight: " + flight.getFlightNumber());
        System.out.println("Route: " + jfk.getCode() + " → " + lhr.getCode());
        System.out.println("Departure: " + flightInstance.getDepartureTime());
        System.out.println("Gate: " + flightInstance.getGate());
        System.out.println("Passengers: " + reservation.getPassengers().size());
        System.out.println("Total Fare: $" + totalFare);
        System.out.println("Status: " + reservation.getStatus());
        System.out.println("\n=== Demo Completed Successfully ===");
    }
}
