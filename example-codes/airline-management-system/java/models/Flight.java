package models;

import java.util.ArrayList;
import java.util.List;

public class Flight {
    private String flightNumber;
    private Airport departure;
    private Airport arrival;
    private int durationInMinutes;
    private List<WeeklySchedule> weeklySchedules;
    private List<CustomSchedule> customSchedules;
    private List<FlightInstance> flightInstances;

    public Flight(String flightNumber, Airport departure, Airport arrival, int durationInMinutes) {
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.arrival = arrival;
        this.durationInMinutes = durationInMinutes;
        this.weeklySchedules = new ArrayList<>();
        this.customSchedules = new ArrayList<>();
        this.flightInstances = new ArrayList<>();
    }

    public void addWeeklySchedule(WeeklySchedule schedule) {
        this.weeklySchedules.add(schedule);
    }

    public void addCustomSchedule(CustomSchedule schedule) {
        this.customSchedules.add(schedule);
    }

    public void addFlightInstance(FlightInstance instance) {
        this.flightInstances.add(instance);
    }

    // Getters
    public String getFlightNumber() {
        return flightNumber;
    }

    public Airport getDeparture() {
        return departure;
    }

    public Airport getArrival() {
        return arrival;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public List<WeeklySchedule> getWeeklySchedules() {
        return weeklySchedules;
    }

    public List<CustomSchedule> getCustomSchedules() {
        return customSchedules;
    }

    public List<FlightInstance> getFlightInstances() {
        return flightInstances;
    }

    // Setters
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setDeparture(Airport departure) {
        this.departure = departure;
    }

    public void setArrival(Airport arrival) {
        this.arrival = arrival;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }
}
