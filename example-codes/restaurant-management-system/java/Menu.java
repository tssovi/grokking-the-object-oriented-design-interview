import java.util.ArrayList;
import java.util.List;

/**
 * Menu.java
 * 
 * This file contains classes related to the restaurant menu system,
 * including menu items, menu sections, and the complete menu structure.
 */

/**
 * Represents a single item on the restaurant menu.
 * 
 * Each menu item has a unique ID, title, description, and price.
 * Menu items are the basic building blocks of the restaurant's menu.
 */
class MenuItem {
    // Private fields for menu item information
    private String menuItemId;
    private String title;
    private String description;
    private double price;

    /**
     * Constructor to create a new MenuItem.
     * 
     * @param menuItemId Unique identifier for the menu item
     * @param title Name of the dish or item
     * @param description Detailed description of the item (ingredients, preparation, etc.)
     * @param price Price of the item in the restaurant's currency
     */
    public MenuItem(String menuItemId, String title, String description, double price) {
        this.menuItemId = menuItemId;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    /**
     * Updates the price of this menu item.
     * 
     * This method allows for price adjustments due to market changes,
     * special promotions, or seasonal variations.
     * 
     * @param newPrice The new price to set for this menu item
     * @return boolean indicating whether the price update was successful
     */
    public boolean updatePrice(double newPrice) {
        // TODO: Implement price update logic
        // This would typically involve:
        // 1. Validate new price (must be positive)
        // 2. Log price change for audit purposes
        // 3. Update price in database
        // 4. Notify relevant systems of price change
        if (newPrice > 0) {
            this.price = newPrice;
            return true;
        }
        return false;
    }

    // Getter methods
    
    public String getMenuItemId() {
        return menuItemId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}

/**
 * Represents a section of the menu (e.g., Appetizers, Main Courses, Desserts).
 * 
 * Menu sections help organize menu items into logical categories,
 * making it easier for customers to navigate the menu.
 */
class MenuSection {
    // Private fields for menu section information
    private String menuSectionId;
    private String title;
    private String description;
    private List<MenuItem> menuItems;

    /**
     * Constructor to create a new MenuSection.
     * 
     * @param menuSectionId Unique identifier for the menu section
     * @param title Name of the section (e.g., "Appetizers", "Beverages")
     * @param description Brief description of the section
     */
    public MenuSection(String menuSectionId, String title, String description) {
        this.menuSectionId = menuSectionId;
        this.title = title;
        this.description = description;
        this.menuItems = new ArrayList<>();
    }

    /**
     * Adds a menu item to this section.
     * 
     * @param menuItem The MenuItem object to add to this section
     * @return boolean indicating whether the item was successfully added
     */
    public boolean addMenuItem(MenuItem menuItem) {
        // TODO: Implement menu item addition logic
        // This would typically involve:
        // 1. Validate menu item
        // 2. Check for duplicates
        // 3. Add to section's item list
        // 4. Update database
        if (menuItem != null && !menuItems.contains(menuItem)) {
            menuItems.add(menuItem);
            return true;
        }
        return false;
    }

    // Getter methods
    
    public String getMenuSectionId() {
        return menuSectionId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<MenuItem> getMenuItems() {
        return new ArrayList<>(menuItems); // Return a copy to prevent external modification
    }
}

/**
 * Represents the complete menu of the restaurant.
 * 
 * A menu consists of multiple menu sections, each containing various menu items.
 * This class provides the top-level structure for organizing and displaying
 * all available food and beverage options.
 */
class Menu {
    // Private fields for menu information
    private String menuId;
    private String title;
    private String description;
    private List<MenuSection> menuSections;

    /**
     * Constructor to create a new Menu.
     * 
     * @param menuId Unique identifier for the menu
     * @param title Name of the menu (e.g., "Dinner Menu", "Lunch Special")
     * @param description Brief description of the menu
     */
    public Menu(String menuId, String title, String description) {
        this.menuId = menuId;
        this.title = title;
        this.description = description;
        this.menuSections = new ArrayList<>();
    }

    /**
     * Adds a menu section to this menu.
     * 
     * @param menuSection The MenuSection object to add to this menu
     * @return boolean indicating whether the section was successfully added
     */
    public boolean addMenuSection(MenuSection menuSection) {
        // TODO: Implement menu section addition logic
        // This would typically involve:
        // 1. Validate menu section
        // 2. Check for duplicates
        // 3. Add to menu's section list
        // 4. Update database
        if (menuSection != null && !menuSections.contains(menuSection)) {
            menuSections.add(menuSection);
            return true;
        }
        return false;
    }

    /**
     * Prints the entire menu in a formatted way.
     * 
     * This method displays all sections and their items with prices,
     * providing a complete view of the restaurant's offerings.
     */
    public void print() {
        // TODO: Implement menu printing logic
        // This would typically involve:
        // 1. Format menu header with title and description
        // 2. Iterate through each section
        // 3. Display section name and items with prices
        // 4. Apply appropriate formatting for readability
        System.out.println("=== " + title + " ===");
        System.out.println(description);
        System.out.println();
        
        for (MenuSection section : menuSections) {
            System.out.println("--- " + section.getTitle() + " ---");
            System.out.println(section.getDescription());
            for (MenuItem item : section.getMenuItems()) {
                System.out.printf("%s - $%.2f%n", item.getTitle(), item.getPrice());
                System.out.println("  " + item.getDescription());
            }
            System.out.println();
        }
    }

    // Getter methods
    
    public String getMenuId() {
        return menuId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<MenuSection> getMenuSections() {
        return new ArrayList<>(menuSections); // Return a copy to prevent external modification
    }
}
