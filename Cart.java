/*
 * Cart class
 * 03/27/2024
 * Cart class used in QBay application to store
 * user's selected items before purchase
 */

import java.util.ArrayList;

public class Cart {
    
    //Using arraylist as it can be modified as user selects new items
    private ArrayList<Item> cartItems;
    private ArrayList<Item> items;

    public Cart(ArrayList<Item> items) {
        //Initialize arraylist
        this.cartItems = new ArrayList<Item>();
        this.items = items;
    }

    public boolean isEmpty() {
        return cartItems.size() == 0;
    }

    //Add item to cart
    public void add(Item item) {
        cartItems.add(item);
    }

    //Remove item from cart
    public void remove(Item item) {
        cartItems.remove(item);
    }

    //Clear cart
    public void clearCart() {
        cartItems.clear();
    }

    //Print cart contents
    public void display() {

        int totalCost = 0;  //Total cost of items in cart
        Item currentItem;   //Iteration variable

        //Trim arraylist size to only current items
        cartItems.trimToSize();

        //Loop through ArrayList
        for(int i = 0; i < cartItems.size(); i++) {

            currentItem = cartItems.get(i);

            //Display individual items. Formatted nicely
            System.out.printf("\n%-12s$%d",
                currentItem.getName(),
                currentItem.getPrice());

            //Adjust cost
            totalCost += currentItem.getPrice();
        }

        //Output total cost
        System.out.printf("\n\n%-12s$%d\n","Total:", totalCost);
    }

    //Purchase items in cart
    public void checkout() {

        //Confirmation message
        System.out.println("\nPurchase confirmed");

        //Remove items from buy list
        for(Item item : cartItems) {
            items.remove(item);
        }
        //Clear cart
        this.clearCart();
    }
}
