package models;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class WeeklySchedule {
    private DayOfWeek dayOfWeek;
    private LocalTime departureTime;

    public WeeklySchedule(DayOfWeek dayOfWeek, LocalTime departureTime) {
        this.dayOfWeek = dayOfWeek;
        this.departureTime = departureTime;
    }

    // Getters
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    // Setters
    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }
}
