package models;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {
    private String frequentFlyerNumber;
    private List<Itinerary> itineraries;

    public Customer(String name, Address address, String email, String phone, 
                   Account account, String frequentFlyerNumber) {
        super(name, address, email, phone, account);
        this.frequentFlyerNumber = frequentFlyerNumber;
        this.itineraries = new ArrayList<>();
    }

    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    public String getFrequentFlyerNumber() {
        return frequentFlyerNumber;
    }

    public void addItinerary(Itinerary itinerary) {
        this.itineraries.add(itinerary);
    }

    public void setFrequentFlyerNumber(String frequentFlyerNumber) {
        this.frequentFlyerNumber = frequentFlyerNumber;
    }
}
