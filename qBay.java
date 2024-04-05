import java.util.Scanner;

/*
 * CSC-109 Challenge1
 * Peter Zegarek, Connor Nylund, Jean LaFrance
 * Proof of concept program of a marketplace application to
 * allow buying/selling of items
 */


public class qBay {

    public static void main(String args[]) {

        //Array of student login information.
        String[][] studentLogins = {{"jean.lafrance@qu.edu", "1234"}, {"connor.nylund@qu.edu", "password"}, {"peter.zegarek@qu.edu", "bestpwrd"}};

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


        // test item shown below:
        // put in price, name of item, category, student name, student email
        // Item item = new Item(10, "Car", "Automobiles", "Peter Zegarek", "pzegarek@qu.edu");
        // System.out.println(item);
    }
}