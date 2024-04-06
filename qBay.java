import java.util.ArrayList;
import java.util.Scanner;

/*
 * CSC-109 Challenge1
 * Peter Zegarek, Connor Nylund, Jean LaFrance
 * Proof of concept program of a marketplace application to
 * allow buying/selling of items
 */


public class qBay {

    // static list of already existing items
    public static ArrayList<Item> items = new ArrayList<Item>();
    // static cart
    public static Cart cart = new Cart();

    public static void main(String args[]) {

        //Array of student login information.
        String[][] studentLogins = {{"test.", "test"}, {"jean.lafrance@qu.edu", "1234"}, {"connor.nylund@qu.edu", "password"}, {"peter.zegarek@qu.edu", "bestpwrd"}};

        String userEmail;               //User entered email for login
        String userPW;                  //User entered password for login
        Boolean loginValid = false;     //Used in login validation
        int loginAttempts = 0;          //Too many attempts will "Lock account"

        Scanner scan = new Scanner(System.in);

        //Welcome message with beautiful ASCII art
        System.out.println("\nWelcome to Quinnipiac Marketplace!");
        System.out.println(" ______     ______     ______     __  __    \n/\\  __ \\   " + 
        "/\\  == \\   /\\  __ \\   /\\ \\_\\ \\   \n\\ \\ \\/\\_\\  \\ \\  __<   \\ \\  __ " + 
        "\\  \\ \\____ \\  \n \\ \\___\\_\\  \\ \\_____\\  \\ \\_\\ \\_\\  \\/\\_____\\ \n  " + 
        "\\/___/_/   \\/_____/   \\/_/\\/_/   \\/_____/ \n                                            ");
        System.out.println("For all of your buying and selling needs");

        //Login loop
        do {
            //Login attemts. Three incorrect will force close program
            if(loginAttempts >= 3) {
                System.out.println("\nAccount locked due to suspicious activity");

                //Allow time for user to read before force close
                try {
                    Thread.sleep(500);
                }
                catch(InterruptedException e) {
                    System.out.println("Exception error");
                }

                System.exit(0);     //Force close program
            }

            loginAttempts++;

            //Ask for login information
            System.out.println("\nPlease enter your email:\t");
            userEmail = scan.nextLine().toLowerCase();
            System.out.println("Please enter your password:\t");
            userPW = scan.nextLine();

            //check user entered information
            for(int i = 0; i < studentLogins.length; i++) {

                //Validate email
                if(userEmail.equals(studentLogins[i][0])) {

                    //Validate associated password
                    if(userPW.equals(studentLogins[i][1])) {
                        loginValid = true;  //Correct email and password
                    }
                }
            }

        } while(!loginValid);

        //Personal greeting
        System.out.println("\nGreetings, " + 
        userEmail.substring(0, 1).toUpperCase() +
        userEmail.substring(1, userEmail.indexOf('.')));



        // add stuff to the buy menu 
        Item item = new Item(100, "Car", "Automobiles", "Peter Zegarek", "pzegarek@qu.edu");
        Item item2 = new Item(5, "Water Bottle", "Useful Items", "Alex Thimineur", "Alexander.Thimeneur@qu.edu");
        Item item3 = new Item(2, "Silly Bands", "Random", "Chris Anzilotti", "canzilotti@qu.edu");
        Item item4 = new Item(50, "Mini Fridge", "Dorm Essentials", "Peter Zegarek", "pzegarek@qu.edu");
        Item item5 = new Item(25, "CSC110 Book", "Books", "Jean LaFrance", "jlafrance@qu.edu");
        addItem(item);
        addItem(item2);
        addItem(item3);
        addItem(item4);
        addItem(item5);
        buy();

    }


    // buy menu function
    public static void buy(){

        Scanner scan = new Scanner(System.in);

        // this loop will be broken when go back to main menu is selected
        while (true){
            System.out.println("What would you like to buy? \nHere are our items on display:\n");

            // print out the possible items
            for (int counter = 0; counter < qBay.items.size(); counter++){
                System.out.println("Item " + (counter+1) + ":");
                System.out.println(qBay.items.get(counter).getName() + "\n");
            }

            // allow the user to select a certain one
            System.out.println("If you want to know more about a certain item (or buy it), just type in the number of the item. Or type 0 to go to the Main Menu.");
            int itemSelected = scan.nextInt();
            // if they selected 0 break the loop
            if (itemSelected == 0){
                break;
            }
            System.out.println(qBay.items.get(itemSelected-1));

            System.out.println("Would you like to add this item to your cart? 1 for yes, 2 for no, or 0 to go to the main menu.");
            int addToCart = scan.nextInt();
            // if they type in zero, break the loop
            if (addToCart == 0){
                break;
            }
            // if it's a 1, add it to the cart
            else if (addToCart == 1){
                System.out.println("You have added " + qBay.items.get(itemSelected-1).getName() + " to your cart.");
                qBay.cart.add(qBay.items.get(itemSelected-1));
                // sleep the thread quick so they can see that
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // we'll just assume invalid input means they didn't want to buy it
            else {
                System.out.println("No problem. We hope you can find something else.");
                // sleep thread quick
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
        }
        scan.close();

    }

    // add an item to the list of items to buy
    public static void addItem(Item item){
        qBay.items.add(item);

    }
        
}