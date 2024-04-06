import java.util.ArrayList;
import java.util.Scanner;
/*
 * CSC-109 Challenge1
 * Peter Zegarek, Connor Nylund, Jean LaFrance
 * Proof of concept program of a marketplace application to
 * allow buying/selling of items
 */


public class qBay {
    // Student login information.
    private static final String[][] studentLogins = {{"test.", "test"}, {"jean.lafrance@qu.edu", "1234"}, {"connor.nylund@qu.edu", "password"}, {"peter.zegarek@qu.edu", "bestpwrd"}};
    // static list of already existing items
    private static ArrayList<Item> items = new ArrayList<Item>();
    // static cart
    private static Cart cart = new Cart();
    // Scanner
    private static final Scanner scanner = new Scanner(System.in);

    private static String userEmail;      //User entered email
    private static String userName;       //user name

    public static void main(String args[]) {
        qBay market = new qBay();
        market.start();
    }

    public void start() {
        boolean quit = false;;

        //Populate sell list with test values
        items.add(new Item(15, "Intro to GIT", "Textbook", "John", "john.smith@quinnipiac.edu"));
        items.add(new Item(80, "TV", "Electronics", "Rick", "rick.sanchez@quinnipiac.edu"));
        items.add(new Item(50, "golf club", "Sports", "Tiger", "Tiger.woods@quinnipiac.edu"));
        items.add(new Item(3500, "Car", "Transportation", "Peter", "peter.zegarek@quinnipiac.edu"));
        items.add(new Item(500, "laptop", "Electronics", "Bill", "bill.gates@quinnipiac.edu"));

        welcome();

        //Ask for login information
        System.out.println("\nPlease enter your email:\t");
        userEmail = scanner.nextLine().toLowerCase();
        userName = userEmail.substring(0, 1).toUpperCase() +
            userEmail.substring(1, userEmail.indexOf('.'));

        login();

        //Application loop
        while(!quit) {
            //Call menuSelection() to get user input
            switch (menuSelection()) {
                case 1:
                    buy();
                    break;
                case 2:
                    sell();
                    break;
                case 3:
                    quit = true;
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }

        logout();
    }

    public void sell() {
        while (true) {
            System.out.println("\nSell Menu:");
            System.out.println("1. See current items for sale");
            System.out.println("2. Add a new item for sale");
            System.out.println("3. Back to main menu");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.println();

            switch (choice) {
                case 1:
                    displayItemsForSale();
                    break;
                case 2:
                    addNewItem();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    //Displays user's current items up for sale
    public void displayItemsForSale() {
        boolean itemsListed = false;

        System.out.println("\nCurrent Items for Sale:");

        //Displays only items listed by user
        for(Item item : items) {
            if(item.getStudentName().toLowerCase().equals(userName.toLowerCase())) {
                System.out.println(item);
                itemsListed = true;
            }
        }

        if(!itemsListed) {
            System.out.println("\tNo listed items");
        }
    }

    //Adds new item to marketplace
    public void addNewItem() {
        System.out.print("Enter name of the item: ");
        String name = scanner.nextLine();

        System.out.print("Enter category of the item: ");
        String category = scanner.nextLine();

        System.out.print("Enter price of the item: ");
        int price = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Item newItem = new Item(price, name, category, userName, userEmail);
        items.add(newItem);

        System.out.println("Item added successfully.");
    }

    //Displays menu. Returns user selection
    public int menuSelection() {
        System.out.println("\nPlease choce what you would like to do.");
        System.out.println("1. buy");
        System.out.println("2. sell");
        System.out.println("3. logout");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.println();

        return choice;

    }

    //Welcome message with beautiful ASCII art
    public void welcome() {
        System.out.println("\nWelcome to Quinnipiac Marketplace!");
        System.out.println(" ______     ______     ______     __  __    \n/\\  __ \\   " + 
        "/\\  == \\   /\\  __ \\   /\\ \\_\\ \\   \n\\ \\ \\/\\_\\  \\ \\  __<   \\ \\  __ " + 
        "\\  \\ \\____ \\  \n \\ \\___\\_\\  \\ \\_____\\  \\ \\_\\ \\_\\  \\/\\_____\\ \n  " + 
        "\\/___/_/   \\/_____/   \\/_/\\/_/   \\/_____/ \n                                            ");
        System.out.println("For all of your buying and selling needs");
        
    }

    //Log in to application. Verification of user login details
    public void login() {
        Boolean loginValid = false;     //Used in login validation
        int loginAttempts = 0;          //Too many attempts will "Lock account"
        String userPW;                  //User entered password

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

            //Ask for user password
            System.out.println("Please enter your password:\t");
            userPW = scanner.nextLine();

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
        System.out.println("\nGreetings, " + userName);
    }

    //Log out of application
    public void logout() {
        System.out.println("\nThank you for using Quinnipiac Marketplace");
        scanner.close();
    }


    // buy menu function
    public static void buy(){

        // this loop will be broken when go back to main menu is selected
        while (true){
            System.out.println("What would you like to buy? \nHere are our items on display:");

            // print out the possible items
            for (int counter = 0; counter < qBay.items.size(); counter++){
                System.out.println("Item " + (counter+1) + ":");
                System.out.println(qBay.items.get(counter).getName() + "\n");
            }

            // allow the user to select a certain one
            System.out.println("If you want to know more about a certain item (or buy it), just type in the number of the item. Or type 0 to go to the Main Menu.");
            int itemSelected = scanner.nextInt();
            // if they selected 0 break the loop
            if (itemSelected == 0){
                break;
            }
            System.out.println(qBay.items.get(itemSelected-1));

            System.out.println("Would you like to add this item to your cart? 1 for yes, 2 for no, or 0 to go to the main menu.");
            int addToCart = scanner.nextInt();
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
    }
}
