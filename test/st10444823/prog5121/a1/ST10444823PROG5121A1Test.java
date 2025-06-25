package st10444823.prog5121.a1;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.Test;


public class ST10444823PROG5121A1Test {

    public ST10444823PROG5121A1Test() {
    }

    @Test
    public void testCorrectUsernameFormat() {
        LogIn login = new LogIn("kyl_1", "Ch&&sec@ke99!", "kyl_1", "Ch&&sec@ke99!", "Kyle", "Smith", "+27838968976","");
        assertEquals("kyl_1", login.getStoredUsername());
    }

    @Test
    public void testWelcomeMessage() {
        LogIn login = new LogIn("kyl_1", "Ch&&sec@ke99!", "kyl_1", "Ch&&sec@ke99!", "Kyle", "Smith", "+27838968976","");
        assertEquals("Welcome Kyle Smith, it is great to see you.", login.returnLoginStatus());
    }

    @Test
    public void testIncorrectUsernameFormat() {
        LogIn login = new LogIn("kyle!!!", "Ch&&sec@ke99!", "kyle!!!", "Ch&&sec@ke99!", "Kyle", "Smith", "+27831234567","");
        assertEquals("kyle!!!", login.getStoredUsername());
    }

    @Test
    public void testIncorrectLoginMessage() {
        LogIn login = new LogIn("kyle!!!", "Ch&&sec@ke99!", "kyle!!!", "Ch&&sec@ke99!", "Kyle", "Smith", "+27831234567","");
        String expected = "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        assertEquals(expected, login.registerUser());
    }

    @Test
    public void testPassword() {
        LogIn login = new LogIn("kyle", "Ch&&sec@ke99!", "kyle", "Ch&&sec@ke99!", "Kyle", "Smith", "+27831234567","");
        assertEquals("Ch&&sec@ke99!", login.getStoredPassword());
    }

    @Test
    public void testPasswordMessage() {
        LogIn login = new LogIn("kyle", "Ch&&sec@ke99!", "kyle", "Ch&&sec@ke99!", "Kyle", "Smith", "+27831234567", "");
        assertEquals("Ch&&sec@ke99!", login.getStoredPassword());
    }

    @Test
    public void testIncorrectPasswordFormat() {
        LogIn login = new LogIn("kyle", "Password", "kyle", "Password", "Kyle", "Smith", "+27831234567", "");
        assertEquals("Password", login.getStoredPassword());
    }

    @Test
    public void testPasswordInvalidMessage() {
        LogIn login = new LogIn("kyle", "Password", "kyle", "Password", "Kyle", "Smith", "+27831234567", "");
        String expected = "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        assertEquals(expected, login.registerUser());
    }

    @Test
    public void testCellphoneNumberMessage() {
        LogIn login = new LogIn("kyle", "Ch&&sec@ke99!", "kyle", "Ch&&sec@ke99!", "Kyle", "Smith", "+27831234567", "");
        assertEquals("+27831234567", login.getNumber());
    }

    @Test
    public void testCellphoneNumberValidation() {
        LogIn login = new LogIn("kyle", "Ch&&sec@ke99!", "kyle", "Ch&&sec@ke99!", "Kyle", "Smith", "+27831234567", "");
        assertTrue(login.checkNumber());
    }

    @Test
    public void testInvalidCellphoneNumberValidation() {
        LogIn login = new LogIn("kyle", "Ch&&sec@ke99!", "kyle", "Ch&&sec@ke99!", "Kyle", "Smith", "0831234567", "");
        assertFalse(login.checkNumber());
    }

    @Test
    public void testLogin() {
        LogIn login = new LogIn("kyl_1", "Ch&&sec@ke99!", "kyl_1", "Ch&&sec@ke99!", "Kyle", "Smith", "+27838968976", "");
        assertTrue(login.loginUser());
    }

    @Test
    public void testInvalidLogin() {
        LogIn login = new LogIn("wrongUser", "wrongPass", "kyl_1", "Ch&&sec@ke99!", "Kyle", "Smith", "+27838968976", "");
        assertFalse(login.loginUser());
    }

    @Test
    public void testValidUsernameFormat() {
        LogIn login = new LogIn("user", "pass", "kyl_1", "Ch&&sec@ke99!", "Kyle", "Smith", "+27831234567", "");
        assertTrue(login.checkUsername());
    }

    @Test
    public void testInvalidUsernameFormatCheck() {
        LogIn login = new LogIn("user", "pass", "kyle!!!", "Ch&&sec@ke99!", "Kyle", "Smith", "+27831234567", "");
        assertFalse(login.checkUsername());
    }

    @Test
    public void testValidPasswordFormat() {
        LogIn login = new LogIn("user", "pass", "kyl_1", "Ch&&sec@ke99!", "Kyle", "Smith", "+27831234567", "");
        assertTrue(login.checkPassword());
    }

    @Test
    public void testInvalidPasswordFormat() {
        LogIn login = new LogIn("user", "pass", "kyl_1", "password", "Kyle", "Smith", "+27831234567", "");
        assertFalse(login.checkPassword());
    }
}
