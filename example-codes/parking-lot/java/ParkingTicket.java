package parkinglot;

import java.util.Date;

/**
 * Represents a parking ticket issued to a vehicle.
 */
public class ParkingTicket {
    private String ticketNumber;
    private Date issuedAt;
    private Date payedAt;
    private double payedAmount;
    private ParkingSpot parkingSpot;

    public ParkingTicket() {
        this.issuedAt = new Date();
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public void setParkingSpot(ParkingSpot spot) {
        this.parkingSpot = spot;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }
}
