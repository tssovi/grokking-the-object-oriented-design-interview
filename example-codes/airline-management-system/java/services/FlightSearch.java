package services;

import models.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightSearch {
    
    public List<FlightInstance> searchFlights(Airport source, Airport destination, LocalDate date) {
        List<FlightInstance> result = new ArrayList<>();
        
        // Get all flights from source airport
        List<Flight> flights = source.getFlights();
        
        for (Flight flight : flights) {
            // Check if flight goes to destination
            if (flight.getArrival().getCode().equals(destination.getCode())) {
                // Get flight instances for the given date
                List<FlightInstance> instances = flight.getFlightInstances().stream()
                    .filter(instance -> instance.getDepartureTime().toLocalDate().equals(date))
                    .collect(Collectors.toList());
                result.addAll(instances);
            }
        }
        
        return result;
    }
    
    public List<FlightInstance> searchFlightsByNumber(String flightNumber) {
        // Implementation to search flights by flight number
        return new ArrayList<>();
    }
    
    public List<FlightSeat> getAvailableSeats(FlightInstance flightInstance) {
        return flightInstance.getAvailableSeats();
    }
}
