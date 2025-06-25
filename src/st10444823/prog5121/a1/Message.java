package st10444823.prog5121.a1;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.hash;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Message {

    // Joyce Farrel - Java Programming 9th Edition
    private String messageID;
    private String messageHash;
    private String messageContent;
    private String recipientCell;
    private int messageCount;

    //Array list 
    // Joyce Farrel - Java Programming 9th Edition
    // Array vs. ArrayList in Java Tutorial - What's The Difference? by Coding with John - https://www.youtube.com/watch?v=NbYgm0r7u6o
    static ArrayList<Message> sentMessage = new ArrayList<>();

    private static List<Message> discardedMessages = new ArrayList<>();

    private static List<Message> storedMessages = new ArrayList<>();

    private static List<String> messageHashes = new ArrayList<>();

    private static List<String> messageIDs = new ArrayList<>();

    //Constructor
    // Joyce Farrel - Java Programming 9th Edition
    public Message(String messageID, String recipientCell, String messageHash, String messageContent, int messageCount) {
        this.messageID = messageID;
        this.recipientCell = recipientCell;
        this.messageHash = messageHash;
        this.messageContent = messageContent;
        this.messageCount = messageCount;
    }

    //Setters
    // Joyce Farrel - Java Programming 9th Edition
    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public void setMessageHash(String messageHash) {
        this.messageHash = messageHash;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public void setRecipientCell(String recipientCell) {
        this.recipientCell = recipientCell;
    }

    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }

    public static void setSentMessage(ArrayList<Message> sentMessage) {
        Message.sentMessage = sentMessage;
    }

    public static void setDiscardedMessages(List<Message> discardedMessages) {
        Message.discardedMessages = discardedMessages;
    }

    public static void setStoredMessages(List<Message> storedMessages) {
        Message.storedMessages = storedMessages;
    }

    public static void setMessageHashes(List<String> messageHashes) {
        Message.messageHashes = messageHashes;
    }

    public static void setMessageIDs(List<String> messageIDs) {
        Message.messageIDs = messageIDs;
    }

    //Getters
    // Joyce Farrel - Java Programming 9th Edition
    public String getMessageID() {
        return messageID;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public String getRecipientCell() {
        return recipientCell;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public static ArrayList<Message> getSentMessage() {
        return sentMessage;
    }

    public static List<Message> getDiscardedMessages() {
        return discardedMessages;
    }

    public static List<Message> getStoredMessages() {
        return storedMessages;
    }

    public static List<String> getMessageHashes() {
        return messageHashes;
    }

    public static List<String> getMessageIDs() {
        return messageIDs;
    }

    //Methods for checking conditions are met
    // IIE - PROG5121POE
    // Joyce Farrel - Java Programming 9th Edition
    //Checking if ID is not more than 10 numbers or characters
    public boolean checkMessageId() {
        return messageID != null && messageID.length() <= 10;
    }

    //Checking if cell number met requirements
    public static int checkRecipientCell(String recipientCell) {
        if (recipientCell != null && recipientCell.startsWith("+27") && recipientCell.length() >= 10) {
            return 1; // Valid
        } else {
            return 0; // Invalid
        }
    }

    //Creates message hash
    // Assisted by Microsoft Copilot (AI)
    //Joyce Farrel - Java Programming 9th Edition
    public static String createMessageHash(String id, String message) {
        String[] words = message.trim().split(" ");
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();
        return id + ":" + message.length() + ":" + firstWord + ":" + lastWord;
    }

    // Assisted by Microsoft Copilot (AI)
    //Joyce Farrel - Java Programming 9th Edition
    // Array vs. ArrayList in Java Tutorial - What's The Difference? by Coding with John - https://www.youtube.com/watch?v=NbYgm0r7u6o
    public static String sentMessage(String messageID, String recipient, String messageText) {
        String hash = createMessageHash(messageID, messageText);
        String[] options = {"Send", "Store", "Discard"};

        int choice = JOptionPane.showOptionDialog(
                null,
                "Message Hash:\n" + hash + "\n\nChoose an action:",
                "Message Action",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        Message msg = new Message(messageID, recipient, hash, messageText, 1);

        switch (choice) {
            case 0 -> {
                sentMessage.add(msg);
                messageHashes.add(hash);
                messageIDs.add(messageID);
                return "Message Sent.";
            }
            case 1 -> {
                storedMessages.add(msg);
                storeMessage();
                return "Message Stored.";
            }
            case 2 -> {
                discardedMessages.add(msg);
                return "Message Discarded.";
            }
            default -> {
                return "No action taken.";
            }
        }
    }

    // Assisted by Microsoft Copilot (AI)
    //Joyce Farrel - Java Programming 9th Edition
    public static String printMessages() {
        if (sentMessage.isEmpty()) {
            return "No messages have been sent yet.";
        }

        StringBuilder result = new StringBuilder();
        for (Message msg : sentMessage) {
            result.append("ID: ").append(msg.getMessageID())
                    .append("\nTo: ").append(msg.getRecipientCell())
                    .append("\nHash: ").append(msg.getMessageHash())
                    .append("\nMessage: ").append(msg.getMessageContent())
                    .append("\n--------------------------\n");
        }

        return result.toString();
    }

    //Joyce Farrel - Java Programming 9th Edition
    public static int returnTotalMessages() {
        return sentMessage.size();
    }

    public static void featuredMenu() {
        while (true) {
            String option = JOptionPane.showInputDialog("""
                    QuickChat - Main Menu
                    
                    1. View Sent Messages
                    2. Longest Message                                   
                    3. Search by Message ID
                    4. Search by Recipient
                    5. Delete by Message Hash
                    6. View Full Report
                    7. Load Stored Messages
                    0. Back to Main Menu 
                    """);

            if (option == null || option.equals("0")) {
                break;
            }

            switch (option) {
                case "1" -> {
                    JOptionPane.showMessageDialog(null, Message.printMessages());
                }

                case "2" ->
                    showLongestMessage();

                case "3" -> {
                    String id = JOptionPane.showInputDialog("Enter message ID to search:");
                    findById(id);
                }

                case "4" -> {
                    String rec = JOptionPane.showInputDialog("Enter recipient number:");
                    findByRecipient(rec);
                }

                case "5" -> {
                    String hash = JOptionPane.showInputDialog("Enter message hash:");
                    deleteByHash(hash);
                }

                case "6" ->
                    fullReport();

                case "7" ->
                    loadFromJson();

                default ->
                    JOptionPane.showMessageDialog(null, "Invalid option.");
            }
        }
        
        
    }
    //Asmksited by Open.ai
    public static void findById(String id) {
        for (Message msg : sentMessage) {
            if (msg.getMessageID().equals(id)) {
                JOptionPane.showMessageDialog(null, msg.getMessageContent());
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Message ID not found.");
    }

    public static void findByRecipient(String number) {
        for (Message msg : sentMessage) {
            if (msg.getRecipientCell().equals(number)) {
                JOptionPane.showMessageDialog(null, msg.getMessageContent());
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Recipient not found.");
    }

    public static void deleteByHash(String hash) {
        for (int i = 0; i < sentMessage.size(); i++) {
            if (sentMessage.get(i).getMessageHash().equals(hash)) {
                sentMessage.remove(i);
                messageHashes.remove(i);
                messageIDs.remove(i);
                JOptionPane.showMessageDialog(null, "Message deleted.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Hash not found.");
    }

    public static void fullReport() {
        JOptionPane.showMessageDialog(null, printMessages());
    }

    //Assited by Copilot
    //Joyce Farrel - Java Programming 9th Edition
    public static void showLongestMessage() {
        if (sentMessage.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No messages.");
            return;
        }
        Message longest = sentMessage.get(0);
        for (Message msg : sentMessage) {
            if (msg.getMessageContent().length() > longest.getMessageContent().length()) {
                longest = msg;
            }
        }
        JOptionPane.showMessageDialog(null, "Longest Message:\n" + longest.getMessageContent());
    }

    //Assited by Open.ai
    //Java - load and save data with JSON-simple. by Mr Cressey's Class Videos - https://www.youtube.com/watch?v=ywLKpHw1MjQ&t=46s
    public static void storeMessage() {
        JSONArray messagesArray = new JSONArray();
        for (Message msg : storedMessages) {
            JSONObject obj = new JSONObject();
            obj.put("id", msg.getMessageID());
            obj.put("hash", msg.getMessageHash());
            obj.put("recipient", msg.getRecipientCell());
            obj.put("text", msg.getMessageContent());
            messagesArray.add(obj);
        }
        try (FileWriter file = new FileWriter("messages.json")) {
            file.write(messagesArray.toJSONString());
            file.flush();
            JOptionPane.showMessageDialog(null, "Messages saved successfully.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error saving messages.\n" + e.getMessage());
        }
    }

    //Assited by Open.ai
    //Java - load and save data with JSON-simple. by Mr Cressey's Class Videos - https://www.youtube.com/watch?v=ywLKpHw1MjQ&t=46s
    public static void loadFromJson() {
        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader("messages.json");
            Object obj = parser.parse(reader);
            JSONArray jsonArray = (JSONArray) obj;
            for (Object item : jsonArray) {
                JSONObject jsonMsg = (JSONObject) item;
                String id = (String) jsonMsg.get("id");
                String hash = (String) jsonMsg.get("hash");
                String recipient = (String) jsonMsg.get("recipient");
                String text = (String) jsonMsg.get("text");
                storedMessages.add(new Message(id, recipient, hash, text, 0));
            }
            reader.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading messages.\n" + e.getMessage());
        }
    }
  
}
