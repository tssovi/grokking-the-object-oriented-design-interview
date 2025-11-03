import java.util.ArrayList;
import java.util.List;

/**
 * Restaurant.java
 * 
 * This file contains classes related to the restaurant structure,
 * including the kitchen, branches, restaurant, and table chart.
 */

/**
 * Represents the kitchen in a restaurant branch.
 * 
 * The kitchen is where food is prepared. It has a name and a team of chefs
 * who are responsible for preparing orders.
 */
class Kitchen {
    // Private fields for kitchen information
    private String name;
    private List<Chef> chefs;

    /**
     * Constructor to create a new Kitchen.
     * 
     * @param name Name or identifier for the kitchen (e.g., "Main Kitchen", "Pastry Kitchen")
     */
    public Kitchen(String name) {
        this.name = name;
        this.chefs = new ArrayList<>();
    }

    /**
     * Assigns a chef to work in this kitchen.
     * 
     * This method adds a chef to the kitchen's team, allowing them to
     * receive and prepare orders.
     * 
     * @param chef The Chef object to assign to this kitchen
     * @return boolean indicating whether the chef was successfully assigned
     */
    public boolean assignChef(Chef chef) {
        // TODO: Implement chef assignment logic
        // This would typically involve:
        // 1. Validate chef object
        // 2. Check if chef is already assigned
        // 3. Add chef to kitchen's chef list
        // 4. Update chef's assignment in database
        // 5. Notify scheduling system
        if (chef != null && !chefs.contains(chef)) {
            chefs.add(chef);
            return true;
        }
        return false;
    }

    /**
     * Removes a chef from this kitchen.
     * 
     * @param chef The Chef to remove
     * @return boolean indicating success
     */
    public boolean removeChef(Chef chef) {
        return chefs.remove(chef);
    }

    // Getter methods
    
    public String getName() {
        return name;
    }

    public List<Chef> getChefs() {
        return new ArrayList<>(chefs); // Return a copy to prevent external modification
    }
}

/**
 * Represents a branch (location) of the restaurant.
 * 
 * A restaurant can have multiple branches, each with its own location,
 * kitchen, and table arrangement. This class manages branch-specific
 * operations and resources.
 */
class Branch {
    // Private fields for branch information
    private String name;
    private Address location;
    private Kitchen kitchen;
    private List<TableChart> tableCharts;
    private List<Table> tables;

    /**
     * Constructor to create a new Branch.
     * 
     * @param name Name of the branch (e.g., "Downtown Branch", "Airport Location")
     * @param location Physical address of the branch
     * @param kitchen The kitchen facility for this branch
     */
    public Branch(String name, Address location, Kitchen kitchen) {
        this.name = name;
        this.location = location;
        this.kitchen = kitchen;
        this.tableCharts = new ArrayList<>();
        this.tables = new ArrayList<>();
    }

    /**
     * Adds a table chart to this branch.
     * 
     * Table charts are visual representations of the seating layout,
     * helping staff manage table assignments efficiently.
     * 
     * @param tableChart The TableChart object to add
     * @return boolean indicating whether the table chart was successfully added
     */
    public boolean addTableChart(TableChart tableChart) {
        // TODO: Implement table chart addition logic
        // This would typically involve:
        // 1. Validate table chart
        // 2. Check for duplicates
        // 3. Add to branch's table chart list
        // 4. Update database
        if (tableChart != null && !tableCharts.contains(tableChart)) {
            tableCharts.add(tableChart);
            return true;
        }
        return false;
    }

    /**
     * Adds a table to this branch.
     * 
     * @param table The Table to add
     * @return boolean indicating success
     */
    public boolean addTable(Table table) {
        if (table != null && !tables.contains(table)) {
            tables.add(table);
            return true;
        }
        return false;
    }

    // Getter methods
    
    public String getName() {
        return name;
    }

    public Address getLocation() {
        return location;
    }

    public Kitchen getKitchen() {
        return kitchen;
    }

    public List<TableChart> getTableCharts() {
        return new ArrayList<>(tableCharts); // Return a copy to prevent external modification
    }

    public List<Table> getTables() {
        return new ArrayList<>(tables); // Return a copy to prevent external modification
    }
}

/**
 * Represents the entire restaurant organization.
 * 
 * This is the top-level class that manages all branches of the restaurant.
 * It provides a centralized way to manage multiple locations and their operations.
 */
class Restaurant {
    // Private fields for restaurant information
    private String name;
    private List<Branch> branches;

    /**
     * Constructor to create a new Restaurant.
     * 
     * @param name Name of the restaurant (e.g., "The Golden Fork", "Bella Italia")
     */
    public Restaurant(String name) {
        this.name = name;
        this.branches = new ArrayList<>();
    }

    /**
     * Adds a new branch to the restaurant.
     * 
     * This method allows the restaurant to expand by adding new locations.
     * 
     * @param branch The Branch object to add to the restaurant
     * @return boolean indicating whether the branch was successfully added
     */
    public boolean addBranch(Branch branch) {
        // TODO: Implement branch addition logic
        // This would typically involve:
        // 1. Validate branch information
        // 2. Check for duplicate locations
        // 3. Add branch to restaurant's branch list
        // 4. Set up branch in database
        // 5. Initialize branch operations
        if (branch != null && !branches.contains(branch)) {
            branches.add(branch);
            return true;
        }
        return false;
    }

    /**
     * Removes a branch from the restaurant.
     * 
     * @param branch The Branch to remove
     * @return boolean indicating success
     */
    public boolean removeBranch(Branch branch) {
        return branches.remove(branch);
    }

    /**
     * Finds a branch by name.
     * 
     * @param branchName Name of the branch to find
     * @return The Branch object if found, null otherwise
     */
    public Branch findBranch(String branchName) {
        for (Branch branch : branches) {
            if (branch.getName().equalsIgnoreCase(branchName)) {
                return branch;
            }
        }
        return null;
    }

    // Getter methods
    
    public String getName() {
        return name;
    }

    public List<Branch> getBranches() {
        return new ArrayList<>(branches); // Return a copy to prevent external modification
    }
}

/**
 * Represents a visual chart showing the table layout in a restaurant.
 * 
 * Table charts help staff visualize the seating arrangement and manage
 * table assignments more effectively. They can include floor plans,
 * table positions, and capacity information.
 */
class TableChart {
    // Private fields for table chart information
    private String tableChartId;
    private byte[] tableChartImage; // Stores the image data of the table layout
    private String description;

    /**
     * Constructor to create a new TableChart.
     * 
     * @param tableChartId Unique identifier for the table chart
     */
    public TableChart(String tableChartId) {
        this.tableChartId = tableChartId;
        this.tableChartImage = new byte[0]; // Initialize with empty byte array
        this.description = "";
    }

    /**
     * Constructor to create a new TableChart with image and description.
     * 
     * @param tableChartId Unique identifier for the table chart
     * @param tableChartImage Image data representing the table layout
     * @param description Text description of the table layout
     */
    public TableChart(String tableChartId, byte[] tableChartImage, String description) {
        this.tableChartId = tableChartId;
        this.tableChartImage = tableChartImage;
        this.description = description;
    }

    /**
     * Prints or displays the table chart.
     * 
     * This method would render the table chart for viewing by staff.
     * In a real implementation, this might display the image on a screen
     * or print it to a physical format.
     */
    public void print() {
        // TODO: Implement table chart printing logic
        // This would typically involve:
        // 1. Load table chart image
        // 2. Format for display/printing
        // 3. Render to appropriate output device
        // 4. Include table status information
        System.out.println("Table Chart ID: " + tableChartId);
        System.out.println("Description: " + description);
        System.out.println("Image size: " + tableChartImage.length + " bytes");
        // In a real application, this would display the actual image
    }

    /**
     * Updates the table chart image.
     * 
     * @param newImage New image data for the table chart
     * @return boolean indicating success
     */
    public boolean updateImage(byte[] newImage) {
        if (newImage != null) {
            this.tableChartImage = newImage;
            return true;
        }
        return false;
    }

    // Getter methods
    
    public String getTableChartId() {
        return tableChartId;
    }

    public byte[] getTableChartImage() {
        return tableChartImage.clone(); // Return a copy to prevent external modification
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
