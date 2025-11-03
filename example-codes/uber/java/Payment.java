import java.util.Date;
import java.util.UUID;

public class Payment {
  private String id;
  private double amount;
  private PaymentStatus status;
  private String method; // Credit card, PayPal, etc.
  private Date timestamp;

  public Payment(double amount, String method) {
    this.id = UUID.randomUUID().toString();
    this.amount = amount;
    this.method = method;
    this.status = PaymentStatus.PENDING;
    this.timestamp = new Date();
  }

  public boolean processPayment() {
    // Simulate payment processing
    this.status = PaymentStatus.COMPLETED;
    return true;
  }

  public void refund() {
    this.status = PaymentStatus.REFUNDED;
  }

  // Getters
  public String getId() { return id; }
  public double getAmount() { return amount; }
  public PaymentStatus getStatus() { return status; }
  public String getMethod() { return method; }
}
