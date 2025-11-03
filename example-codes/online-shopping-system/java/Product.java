/**
 * Product-related classes for Online Shopping System
 * 
 * This file contains classes related to products, categories, and reviews.
 */

/**
 * ProductCategory represents a category that products can belong to
 * Examples: Electronics, Clothing, Books, etc.
 */
class ProductCategory {
    private String name;
    private String description;

    /**
     * Constructor to create a new product category
     * 
     * @param name The name of the category
     * @param description A description of the category
     */
    public ProductCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters
    public String getName() { return name; }
    public String getDescription() { return description; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
}

/**
 * ProductReview represents a customer review for a product
 * Contains rating, review text, and reviewer information
 */
class ProductReview {
    private int rating;
    private String review;
    private Object reviewer;  // Reference to the reviewer (Customer/Member)

    /**
     * Constructor to create a new product review
     * 
     * @param rating The rating given (typically 1-5 stars)
     * @param review The review text/comment
     * @param reviewer The customer who wrote the review
     */
    public ProductReview(int rating, String review, Object reviewer) {
        this.rating = rating;
        this.review = review;
        this.reviewer = reviewer;
    }

    // Getters
    public int getRating() { return rating; }
    public String getReview() { return review; }
    public Object getReviewer() { return reviewer; }

    // Setters
    public void setRating(int rating) { this.rating = rating; }
    public void setReview(String review) { this.review = review; }
    public void setReviewer(Object reviewer) { this.reviewer = reviewer; }
}

/**
 * Product represents an item available for purchase in the online shopping system
 * Contains all product information including price, category, and availability
 */
class Product {
    private String productId;
    private String name;
    private String description;
    private double price;
    private ProductCategory category;
    private int availableItemCount;
    private Object seller;  // Reference to the seller account

    /**
     * Constructor to create a new product
     * 
     * @param id Unique identifier for the product
     * @param name Name of the product
     * @param description Detailed description of the product
     * @param price Price of the product
     * @param category Category the product belongs to
     * @param sellerAccount The account of the seller offering this product
     */
    public Product(String id, String name, String description, double price, 
                   ProductCategory category, Object sellerAccount) {
        this.productId = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.availableItemCount = 0;
        this.seller = sellerAccount;
    }

    /**
     * Get the number of available items in stock
     * 
     * @return The count of available items
     */
    public int getAvailableCount() {
        return availableItemCount;
    }

    /**
     * Update the price of the product
     * 
     * @param newPrice The new price to set
     */
    public void updatePrice(double newPrice) {
        this.price = newPrice;
    }

    // Getters
    public String getProductId() { return productId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public ProductCategory getCategory() { return category; }
    public Object getSeller() { return seller; }

    // Setters
    public void setProductId(String productId) { this.productId = productId; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setCategory(ProductCategory category) { this.category = category; }
    public void setAvailableItemCount(int count) { this.availableItemCount = count; }
    public void setSeller(Object seller) { this.seller = seller; }
}
