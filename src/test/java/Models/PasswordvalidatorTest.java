package Models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordvalidatorTest {

    @Test
    void testValidPassword() {
        assertTrue(Passwordvalidator.validate("Valid1@Password"));
        assertTrue(Passwordvalidator.validate("A1@longerPassword"));
    }

    @Test
    void testInvalidPassword_NoUppercase() {
        assertFalse(Passwordvalidator.validate("invalid1@password"));
    }

    @Test
    void testInvalidPassword_NoLowercase() {
        assertFalse(Passwordvalidator.validate("INVALID1@PASSWORD"));
    }

    @Test
    void testInvalidPassword_NoDigit() {
        assertFalse(Passwordvalidator.validate("NoDigit@Here"));
    }

    @Test
    void testInvalidPassword_NoSpecialCharacter() {
        assertFalse(Passwordvalidator.validate("NoSpecial1Character"));
    }

    @Test
    void testInvalidPassword_TooShort() {
        assertFalse(Passwordvalidator.validate("Short1@"));
        assertFalse(Passwordvalidator.validate("Sh1@"));
    }

    @Test
    void testInvalidPassword_Null() {
        assertFalse(Passwordvalidator.validate(null));
    }

    @Test
    void testInvalidPassword_Empty() {
        assertFalse(Passwordvalidator.validate(""));
    }
}
