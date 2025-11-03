public abstract class User {
  private String id;
  private String name;
  private String email;
  private String phone;
  private double rating;
  private int totalRatings;

  public User(String id, String name, String email, String phone) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.rating = 5.0;
    this.totalRatings = 0;
  }

  public void updateRating(double newRating) {
    this.rating = ((this.rating * this.totalRatings) + newRating) / (this.totalRatings + 1);
    this.totalRatings++;
  }

  // Getters
  public String getId() { return id; }
  public String getName() { return name; }
  public String getEmail() { return email; }
  public String getPhone() { return phone; }
  public double getRating() { return rating; }
}
