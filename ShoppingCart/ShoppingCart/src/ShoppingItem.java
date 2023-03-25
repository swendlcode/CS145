/**
 * The ShoppingItem class represents an item in a shopping list.
 *
 *  @author [Pavel Stepanov]
 *  @version [Date: 03-07-2023]
 */
public class ShoppingItem {
    private String name;
    private int quantity;
    private double price;

    /**
     * Constructs a new ShoppingItem with the specified name, quantity, and price per unit.
     *
     * @param name the name of the item
     * @param quantity the quantity of the item
     * @param priceperunit the price per unit of the item
     */
    public ShoppingItem(String name, int quantity, double priceperunit) {
        this.name = name;
        this.quantity = quantity;
        this.price = priceperunit;
    }

    /**
     * Returns the name of this item.
     *
     * @return the name of this item
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the total cost of this item based on its quantity and price per unit.
     *
     * @return the total cost of this item
     */
    public double getCost() {
        return quantity * price;
    }

    /**
     * Sets the quantity of this item.
     *
     * @param quantity the new quantity of this item
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns a string representation of this item in the format "quantity name\n".
     *
     * @return a string representation of this item
     */
    public String toString () {
        return quantity + " " + name + "\n";
    }

    /**
     * Returns the quantity of this item.
     *
     * @return the quantity of this item
     */
    public int getQuantity() {
        return quantity;
    }
}
