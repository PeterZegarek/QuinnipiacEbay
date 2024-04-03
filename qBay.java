import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
 * CSC-109 Challenge1
 * Peter Zegarek, Connor Nylund, Jean LaFrance
 * Proof of concept program of a marketplace application to
 * allow buying/selling of items
 */


public class qBay {

    private Scanner scanner = new Scanner(System.in);
    private List<Item> itemsForSale = new ArrayList<>();

    public static void main(String args[]) {
        qBay market = new qBay();
        market.start();
    }

    public void start() {
        //Array of student login information.
        String[][] studentLogins = {{"jean.lafrance@qu.edu", "1234"}, {"cn@qu.edu", "125"}, {"peter.zegarek@qu.edu", "bestpwrd"}};

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
            userEmail = scan.nextLine();
            System.out.println("Please enter your password:\t");
            userPW = scan.nextLine();

            //check user entered information
            for(int i = 0; i < studentLogins.length; i++) {

                //Validate email
                if(userEmail.equals(studentLogins[i][0].toLowerCase())) {

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
        
        System.out.println("\nPlease choce what you would like to do.");
        System.out.println("1. buy");
        System.out.println("2. sell");
        System.out.println("3. logout");
        System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    //buy();
                    break;
                case 2:
                    sell();
                    break;
                case 3:
                    //logout();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
    }
        // test item shown below:
        // put in price, name of item, category, student name, student email
        // Item item = new Item(10, "Car", "Automobiles", "Peter Zegarek", "pzegarek@qu.edu");
        // System.out.println(item);

        
public void sell() {
        while (true) {
            System.out.println("\nSell Menu:");
            System.out.println("1. See current items for sale");
            System.out.println("2. Add a new item for sale");
            System.out.println("3. Back to main menu");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    displayItemsForSale();
                    break;
                case 2:
                    addNewItem();
                    break;
                case 3:
                    start();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    public void displayItemsForSale() {
        if (itemsForSale.isEmpty()) {
            System.out.println("\nNo items currently for sale.");
        } else {
            System.out.println("\nCurrent Items for Sale:");
            for (Item item : itemsForSale) {
                System.out.println(item);
            }
        }
    }

    public void addNewItem() {
        System.out.print("Enter name of the item: ");
        String name = scanner.nextLine();

        System.out.print("Enter category of the item: ");
        String category = scanner.nextLine();

        System.out.print("Enter price of the item: ");
        int price = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Item newItem = new Item(price, name, category);
        itemsForSale.add(newItem);

        System.out.println("Item added successfully.");
    }
}
