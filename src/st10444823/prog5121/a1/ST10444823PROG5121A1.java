package st10444823.prog5121.a1;

import java.util.Scanner;
import javax.swing.JOptionPane;

public class ST10444823PROG5121A1 {
    public static void main(String[] args) {
        // Declaration
        String username;
        String password;
        String number;
        String firstName;
        String lastName;
        String loginUsername;
        String loginPassword;

        Scanner input = new Scanner(System.in);

        // Registration inputs
        //Joyce Farrel - Java Programming 9th Edition
        System.out.print("Enter Username: ");
        username = input.nextLine();

        System.out.print("Enter Password: ");
        password = input.nextLine();

        System.out.print("Enter cell phone number: ");
        number = input.nextLine();

        // Temp LogIn object for registration validation
        // Assisted by Microsoft Copilot (AI)
        //Joyce Farrel - Java Programming 9th Edition
        LogIn registration = new LogIn("", "", username, password, "", "", number, "");

        // Show validation results
        //Joyce Farrel - Java Programming 9th Edition
        System.out.println(registration.registerUser());

        if (registration.checkUsername() && registration.checkPassword() && registration.checkNumber()) {
            // Loggin in 
            System.out.println("\nRegistration successful.\nPlease enter login details below:");

            System.out.print("Enter First Name: ");
            firstName = input.nextLine();

            System.out.print("Enter Last Name: ");
            lastName = input.nextLine();

            System.out.print("Enter Username: ");
            loginUsername = input.nextLine();

            System.out.print("Enter Password: ");
            loginPassword = input.nextLine();

            // Create full LogIn object with login details
            LogIn login = new LogIn(loginUsername, loginPassword, username, password, firstName, lastName, number, "");

            // Check login result
            String status = login.returnLoginStatus();
            JOptionPane.showMessageDialog(null, status);

            if (login.loginUser()) {
                // Prepare message
                String messageID = "1"; 
                String recipientCell = number; 
                String messageContent = ""; 
                String messageHash = "";
                int messageCount = 0;

                // Create Message object
                Message messageObj = new Message(messageID, recipientCell, messageHash, messageContent, messageCount);

                // Show Main menu from Login
                login.showMainMenu();
                
                
            }

        } else {
            System.out.println("Registration failed. Please check your details.");
        }

        input.close();
    }
}
