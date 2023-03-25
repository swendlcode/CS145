/**
 *
 * CS& 145 Data Structures
 *
 * Programming Assignment: ShoppingCart
 *
 *
 Write a class named ShoppingList that represents a person’s list of items to buy from the market and
 another class named ShoppingItem that represents a request to purchase a particular item in each quantity (e.g., four boxes of cookies).
 The ShoppingList class should use an array field to store the grocery items and keep track of its size (number of items in the list so far). Assume that a shopping list will have no more than 10 items.
 @author [Pavel Stepanov]
 @version [Date: 03-07-2023]
 */

import java.util.Scanner;

public class ShoppingClient {

    public static void main(String[] args) {
        System.out.println("\n CS& 145 Data Structures Programming Assignment: ShoppingCart");
        System.out.println();
        ShoppingList shoppingList = new ShoppingList();
        Scanner scanner = new Scanner(System.in);

        int option = 0;
        while (option != 5) {
            System.out.println("1. Display menu");
            System.out.println("2. Add a shopping item");
            System.out.println("3. Change quantity of an item");
            System.out.println("4. Display shopping list");
            System.out.println("5. Exit");
            System.out.print("Enter option number (1-5): ");

            if (scanner.hasNextInt()) {
                option = scanner.nextInt();

                if (option == 1) {
                    System.out.println();
                }
                else if (option == 2) {
                    addItem(shoppingList, scanner);
                }

                else if (option == 3) {
                    if (shoppingList.getTotalCost() == 0) {
                        System.out.println("No items in your shopping list.");
                        System.out.println();
                    }
                    else {
                        changeQuantity(shoppingList, scanner);
                    }
                }
                else if (option == 4) {
                    displayList(shoppingList);
                }
                else if (option == 5) {
                    System.out.println("Have a good day!");
                }
                else {
                    System.out.println("Invalid option. Please enter a number from 1 to 5.");
                    System.out.println();
                }
            }
            else {
                System.out.println("Invalid input. Please enter a number from 1 to 5.");
                System.out.println();
                scanner.next();

            }
        }
    }

    private static void addItem(ShoppingList shoppingList, Scanner scanner) {
        System.out.print("Enter item name: ");
        String itemName = scanner.next();
        System.out.print("Enter quantity: ");
        int quantity;
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            System.out.println();
            scanner.next();
        }
        quantity = scanner.nextInt();
        System.out.print("Enter unit price: ");
        double price;
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a number.");
            System.out.println();
            scanner.next();
        }
        price = scanner.nextDouble();

        ShoppingItem item = new ShoppingItem(itemName, quantity, price);
        if (shoppingList.add(item)) {
            System.out.println("Item added: " + item.toString());
            System.out.println();
        } else {
            System.out.println("Shopping list is full. Cannot add item.");
            System.out.println();
        }
    }

    private static void changeQuantity(ShoppingList shoppingList, Scanner scanner) {
        System.out.print("Enter item name: ");
        String itemName = scanner.next();
        ShoppingItem item = shoppingList.searchByName(itemName);
        if (item == null) {
            System.out.println("Item not found.");
            System.out.println();
        }
        System.out.print("Enter new quantity: ");
        int newQuantity = 0;
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            System.out.println();
            scanner.next();
        }
        newQuantity = scanner.nextInt();
        item.setQuantity(newQuantity);
        System.out.println("Quantity updated: " + item);
    }

    private static void displayList(ShoppingList shoppingList) {
        System.out.print("Your cart contains " + shoppingList.getSize() + " item(s):\n");
        for (int i = 0; i < shoppingList.getSize(); i++) {
            ShoppingItem item = shoppingList.getItems()[i];
            System.out.print("(" + item.getQuantity() + ") " + item.getName());
            if (i < shoppingList.getSize() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("\nTotal cost: $" + String.format("%.2f", shoppingList.getTotalCost()));
        System.out.println();

    }
}