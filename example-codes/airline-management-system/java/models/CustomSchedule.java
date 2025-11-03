package models;

import java.time.LocalDate;
import java.time.LocalTime;

public class CustomSchedule {
    private LocalDate customDate;
    private LocalTime departureTime;

    public CustomSchedule(LocalDate customDate, LocalTime departureTime) {
        this.customDate = customDate;
        this.departureTime = departureTime;
    }

    // Getters
    public LocalDate getCustomDate() {
        return customDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    // Setters
    public void setCustomDate(LocalDate customDate) {
        this.customDate = customDate;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }
}
