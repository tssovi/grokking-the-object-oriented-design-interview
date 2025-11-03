import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Search-related classes for Online Shopping System
 * 
 * This file contains classes for searching and cataloging products.
 */

/**
 * Search is an abstract base class that defines the search interface
 * Provides methods for searching products by different criteria
 */
abstract class Search {
    
    /**
     * Search for products by name
     * 
     * @param name The product name to search for
     * @return List of products matching the name
     */
    public abstract List<Product> searchProductsByName(String name);

    /**
     * Search for products by category
     * 
     * @param category The category to search in
     * @return List of products in the category
     */
    public abstract List<Product> searchProductsByCategory(String category);
}

/**
 * Catalog implements the Search interface and manages the product catalog
 * Maintains indices for efficient product searching by name and category
 */
class Catalog extends Search {
    // Map of product names to list of products (multiple products can have similar names)
    private Map<String, List<Product>> productNames;
    
    // Map of category names to list of products in that category
    private Map<String, List<Product>> productCategories;

    /**
     * Constructor to create a new empty catalog
     */
    public Catalog() {
        this.productNames = new HashMap<>();
        this.productCategories = new HashMap<>();
    }

    /**
     * Add a product to the catalog
     * Updates both name and category indices
     * 
     * @param product The product to add
     */
    public void addProduct(Product product) {
        // Add to name index
        String name = product.getName().toLowerCase();
        productNames.computeIfAbsent(name, k -> new ArrayList<>()).add(product);

        // Add to category index
        String categoryName = product.getCategory().getName().toLowerCase();
        productCategories.computeIfAbsent(categoryName, k -> new ArrayList<>()).add(product);
    }

    /**
     * Remove a product from the catalog
     * 
     * @param product The product to remove
     * @return true if product was removed successfully
     */
    public boolean removeProduct(Product product) {
        boolean removed = false;
        
        // Remove from name index
        String name = product.getName().toLowerCase();
        List<Product> nameList = productNames.get(name);
        if (nameList != null) {
            removed = nameList.remove(product);
            if (nameList.isEmpty()) {
                productNames.remove(name);
            }
        }

        // Remove from category index
        String categoryName = product.getCategory().getName().toLowerCase();
        List<Product> categoryList = productCategories.get(categoryName);
        if (categoryList != null) {
            categoryList.remove(product);
            if (categoryList.isEmpty()) {
                productCategories.remove(categoryName);
            }
        }

        return removed;
    }

    /**
     * Search for products by name
     * Performs case-insensitive search
     * 
     * @param name The product name to search for
     * @return List of products matching the name, or empty list if none found
     */
    @Override
    public List<Product> searchProductsByName(String name) {
        List<Product> results = productNames.get(name.toLowerCase());
        return (results != null) ? new ArrayList<>(results) : new ArrayList<>();
    }

    /**
     * Search for products by category
     * Performs case-insensitive search
     * 
     * @param category The category name to search for
     * @return List of products in the category, or empty list if none found
     */
    @Override
    public List<Product> searchProductsByCategory(String category) {
        List<Product> results = productCategories.get(category.toLowerCase());
        return (results != null) ? new ArrayList<>(results) : new ArrayList<>();
    }

    /**
     * Search for products by partial name match
     * Useful for autocomplete or fuzzy search
     * 
     * @param partialName The partial name to search for
     * @return List of products with names containing the partial name
     */
    public List<Product> searchProductsByPartialName(String partialName) {
        List<Product> results = new ArrayList<>();
        String searchTerm = partialName.toLowerCase();
        
        for (Map.Entry<String, List<Product>> entry : productNames.entrySet()) {
            if (entry.getKey().contains(searchTerm)) {
                results.addAll(entry.getValue());
            }
        }
        
        return results;
    }

    /**
     * Get all products in the catalog
     * 
     * @return List of all products
     */
    public List<Product> getAllProducts() {
        List<Product> allProducts = new ArrayList<>();
        for (List<Product> products : productNames.values()) {
            allProducts.addAll(products);
        }
        return allProducts;
    }

    // Getters
    public Map<String, List<Product>> getProductNames() { return productNames; }
    public Map<String, List<Product>> getProductCategories() { return productCategories; }
}
