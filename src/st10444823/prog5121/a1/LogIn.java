package st10444823.prog5121.a1;

import java.util.Random;
import javax.swing.*;

public class LogIn {

    private String inputUsername, inputPassword;
    private String storedUsername, storedPassword;
    private String firstName, lastName, number, choice;

    private int messageCount = 0;
    private static int totalMessagesSent = 0;
    private static int messagestoSend = 0;
    private static int messagesSent = 0;

    //Constructor
    public LogIn(String inputUsername, String inputPassword, String storedUsername, String storedPassword,
                 String firstName, String lastName, String number, String choice) 
    {
        this.inputUsername = inputUsername;
        this.inputPassword = inputPassword;
        this.storedUsername = storedUsername;
        this.storedPassword = storedPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.choice = choice;
    }

    //getter
    public String getInputUsername() {
        return inputUsername;
    }

    public String getInputPassword() {
        return inputPassword;
    }

    public String getStoredUsername() {
        return storedUsername;
    }

    public String getStoredPassword() {
        return storedPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNumber() {
        return number;
    }

    public String getChoice() {
        return choice;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public static int getTotalMessagesSent() {
        return totalMessagesSent;
    }

    public static int getMaxMessagesToSend() {
        return messagestoSend;
    }

    public static int getMessagesSentSoFar() {
        return messagesSent;
    }
    
    //setter

    public void setInputUsername(String inputUsername) {
        this.inputUsername = inputUsername;
    }

    public void setInputPassword(String inputPassword) {
        this.inputPassword = inputPassword;
    }

    public void setStoredUsername(String storedUsername) {
        this.storedUsername = storedUsername;
    }

    public void setStoredPassword(String storedPassword) {
        this.storedPassword = storedPassword;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }

    public static void setTotalMessagesSent(int totalMessagesSent) {
        LogIn.totalMessagesSent = totalMessagesSent;
    }

    public static void setMaxMessagesToSend(int maxMessagesToSend) {
        LogIn.messagestoSend= messagestoSend;
    }

    public static void setMessagesSentSoFar(int messagesSentSoFar) {
        LogIn.messagesSent = messagesSent;
    }
    

    public boolean checkUsername() {
        return storedUsername.length() <= 5 && storedUsername.contains("_");
    }

    //Checking for password requirements 
    public boolean checkPassword() {
        return storedPassword.length() >= 8 &&
               storedPassword.matches(".*[A-Z].*") &&
               storedPassword.matches(".*[a-z].*") &&
               storedPassword.matches(".*[0-9].*") &&
               storedPassword.matches(".*[!@#$%^&*()_+].*");
    }

    //Checking for phone number
    //Joyce Farrel - Java Programming 9th Edition
    public boolean checkNumber() {
        return number.startsWith("+27") && number.length() >= 10;
    }

    //Checking user's detail registration 
    //Joyce Farrel - Java Programming 9th Edition
    public String registerUser() {
        if (!checkUsername()) {
            return "Invalid Username.";
        }
        
        else if (!checkPassword()) {
            return "Invalid Password.";
        }
        
        else if (!checkNumber()) {
            return "Invalid Phone Number.";
        }
        
        else {
            return "All inputs are valid. Registration is successful.";
        }
    }

    //Allowing user to login based on requirements 
    //Joyce Farrel - Java Programming 9th Edition
    public boolean loginUser() {
        
        return inputUsername.equals(storedUsername) && inputPassword.equals(storedPassword);
    }

    //Login Message
    //Joyce Farrel - Java Programming 9th Edition
     public String returnLoginStatus() {
        return loginUser() ? "Welcome to QuickChat" : "Username or password is incorrect, please try again.";
    }
     
     //Showing main maneu & allowing user to choose number of messages
     // Assisted by Microsoft Copilot (AI)
    //Joyce Farrel - Java Programming 9th Edition
    public void showMainMenu() {
        String inputLimit = JOptionPane.showInputDialog("How many messages would you like to send?");
        try {
            messagestoSend = Integer.parseInt(inputLimit);
        } 
        
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number. Exiting.");
            System.exit(0);
        }

        while (true) {
            
            String input = JOptionPane.showInputDialog("""
                QuickChat Menu:
                                                       
                Option 1) Send Message                                               
                                                       
                Options 2) Messenge Menu
                                                       
                Option 3) Quit
                                                                               
                                                                                                                  
                Enter your choice (1-3):
            """);

            if (input == null) {
                JOptionPane.showMessageDialog(null, "Goodbye!");
                System.exit(0);
            }

            switch (input.trim()) {
                
                case "1":
                  for (int i = messagesSent; i < messagestoSend; i++){
                  sendMessage();
                  messagesSent++;
                  }
                  JOptionPane.showMessageDialog(null, "You have reached your limit");
                    break;  
                    
                case "2":
                    Message.featuredMenu();
                    
                    break;
                    
                case "3":
                    JOptionPane.showMessageDialog(null, "Goodbye!");
                    System.exit(0);
                    break;
                    
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice.");
            }
        }
    }
    
    //Checking user messages requirements are met & actions
    // Assisted by Microsoft Copilot (AI)
    //Joyce Farrel - Java Programming 9th Edition
   private void sendMessage() {
        Random rand = new Random();
        long randomTenDigits = 1000000000L + (long) (rand.nextDouble() * 9000000000L);
        String messageID = "MSG" + randomTenDigits;

        String recipient = JOptionPane.showInputDialog("Enter the recipient's cell number:");
        if (Message.checkRecipientCell(recipient) != 1) {
            JOptionPane.showMessageDialog(null, "Invalid recipient number.");
            return;
        }

        String msg = JOptionPane.showInputDialog("Enter your message (Max 250 characters):");
        if (msg == null || msg.length() > 250 || msg.isBlank()) {
            JOptionPane.showMessageDialog(null, "Invalid message.");
            return;
        }

        // Call your message handler with built-in Save/Send/Discard logic
        String result = Message.sentMessage(messageID, recipient, msg);

        // Increment counters and show result
        messageCount++;
        totalMessagesSent++;
        messagesSent++;

        JOptionPane.showMessageDialog(null, result);
    }
}