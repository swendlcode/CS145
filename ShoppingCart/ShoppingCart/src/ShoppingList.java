/**
 * The ShoppingList class represents a shopping list.
 *
 *  @author [Pavel Stepanov]
 *  @version [Date: 03-07-2023]
 */
public class ShoppingList {
    private ShoppingItem[] items;
    private int size;

    /**
     * Constructs a new ShoppingList with a capacity of 10 items.
     */
    public ShoppingList() {
        this.items = new ShoppingItem[10];
        this.size = 0;
    }

    /**
     * Adds the specified ShoppingItem to this shopping list.
     *
     * @param item the ShoppingItem to add
     * @return true if the item was added successfully, false otherwise
     */
    public boolean add(ShoppingItem item) {
        // Adds a new item to the shopping list if the list is not full.
        if (size < 10) {
            items[size++] = item;
            // Returns true if the item was added successfully, false otherwise.
            return true;
        }
        return false;
    }

    /**
     * Returns the total cost of all the items in this shopping list.
     *
     * @return the total cost of all the items in this shopping list
     */
    public double getTotalCost() {
        // Returns the total cost of all items in the shopping list by iterating through
        double totalCost = 0.0;
        // each item and adding their respective costs.
        for (int i = 0; i < size; i++) {
            totalCost += items[i].getCost();
        }
        return totalCost;
    }

    /**
     * Returns a string representation of this shopping list, listing all the items in the list.
     *
     * @return a string representation of this shopping list
     */
    public String toString() {
        if (size == 0) {
            return "No items in your shopping list.";
        }

        StringBuilder sb = new StringBuilder("Shopping List:\n");
        for (int i = 0; i < size; i++) {
            sb.append(items[i].toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Searches for a ShoppingItem in this shopping list with the specified name.
     *
     * @param itemName the name of the item to search for
     * @return the ShoppingItem with the specified name, or null if no such item exists
     */
    public ShoppingItem searchByName(String itemName) {
        for (int i = 0; i < size; i++) {
            if (items[i].getName().equalsIgnoreCase(itemName)) {
                return items[i];
            }
        }
        return null;
    }

    /**
     * Returns the number of items in this shopping list.
     *
     * @return the number of items in this shopping list
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns an array of all the ShoppingItems in this shopping list.
     *
     * @return an array of all the ShoppingItems in this shopping list
     */
    public ShoppingItem[] getItems() {
        return items;
    }

}
