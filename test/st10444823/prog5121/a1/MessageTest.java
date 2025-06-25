package st10444823.prog5121.a1;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class MessageTest {

    private String messageID;
    private String recipientNumber;
    private String messageContent;

    @Before
    public void setUp() {
        messageID = "001";
        recipientNumber = "+278388884567";
        messageContent = "Hello World!";

        // Clear static data before each test
        Message.getSentMessage().clear();
        Message.getMessageHashes().clear();
        Message.getMessageIDs().clear();
        Message.getStoredMessages().clear();
        Message.getDiscardedMessages().clear();
    }

    @Test
    public void testMessageLengthValid() {
        assertTrue("Message should not exceed 250 characters", messageContent.length() <= 250);
    }

    @Test
    public void testRecipientFormatValid() {
        int result = Message.checkRecipientCell(recipientNumber);
        assertEquals("Recipient number should be valid", 1, result);
    }

    @Test
    public void testRecipientFormatInvalid() {
        String invalidNumber = "08388884567";
        int result = Message.checkRecipientCell(invalidNumber);
        assertEquals("Recipient number should be invalid", 0, result);
    }

    @Test
    public void testMessageIdValid() {
        Message msg = new Message("1234567890", recipientNumber, "", messageContent, 1);
        assertTrue("Message ID should be valid", msg.checkMessageId());
    }

    @Test
    public void testMessageIdInvalid() {
        Message msg = new Message("12345678901", recipientNumber, "", messageContent, 1);
        assertFalse("Message ID should be invalid", msg.checkMessageId());
    }

    @Test
    public void testHashCreation() {
        String expectedHash = "001:12:HELLO:WORLD!";
        String actualHash = Message.createMessageHash(messageID, messageContent);
        assertEquals("Message hash should match expected format", expectedHash, actualHash);
    }

    @Test
    public void testSendMessageAction() {
        // Simulate sending a message directly without GUI interaction
        String hash = Message.createMessageHash(messageID, messageContent);
        Message msg = new Message(messageID, recipientNumber, hash, messageContent, 1);
        Message.getSentMessage().add(msg);

        assertFalse(Message.getSentMessage().isEmpty());
        assertEquals("Message should be stored in sent list", msg, Message.getSentMessage().get(0));
    }

    @Test
    public void testStoreMessageToJsonAndReload() {
        // Store the message
        String hash = Message.createMessageHash(messageID, messageContent);
        Message stored = new Message(messageID, recipientNumber, hash, messageContent, 1);
        Message.getStoredMessages().add(stored);
        Message.storeMessage();

        // Load messages from JSON
        Message.getStoredMessages().clear(); // Clear first
        Message.loadFromJson();

        List<Message> loadedMessages = Message.getStoredMessages();
        assertFalse("Messages should be loaded from JSON", loadedMessages.isEmpty());
        assertEquals("Stored and loaded message should match", messageID, loadedMessages.get(0).getMessageID());
    }

    @Test
    public void testDeleteMessageByHash() {
        String hash = Message.createMessageHash(messageID, messageContent);
        Message msg = new Message(messageID, recipientNumber, hash, messageContent, 1);
        Message.getSentMessage().add(msg);
        Message.getMessageHashes().add(hash);
        Message.getMessageIDs().add(messageID);

        // Delete by hash
        Message.deleteByHash(hash);
        assertTrue("Message should be deleted from sent messages", Message.getSentMessage().isEmpty());
    }

    @Test
    public void testTotalMessagesSent() {
        Message msg1 = new Message("101", recipientNumber, "", "Hi", 1);
        Message msg2 = new Message("102", recipientNumber, "", "Hello", 1);
        Message.getSentMessage().add(msg1);
        Message.getSentMessage().add(msg2);

        assertEquals("Total messages sent should be 2", 2, Message.returnTotalMessages());
    }
}
