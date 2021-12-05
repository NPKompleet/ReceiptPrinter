package com.npkompleet.receipt.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.npkompleet.receipt.InputValidator;

import org.junit.jupiter.api.Test;

public class InputValidatorTest {
    @Test
    public void shouldBeInvalid() {
        String input1 = "1 at 10.00";
        String input2 = "1 book at 10.13 music CD";
        String input3 = "1 red colored book of poems at 22.33";
        String input4 = "box of doughnuts at 22.00";
        String input5 = "one box of doughnuts at 22.00";
        String input6 = "";
        String input7 = "-1 book of spells at 39.99";
        String input8 = "1 book of spells at -39.99";
        assertFalse(InputValidator.isValidInput(input1));
        assertFalse(InputValidator.isValidInput(input2));
        assertFalse(InputValidator.isValidInput(input3));
        assertFalse(InputValidator.isValidInput(input4));
        assertFalse(InputValidator.isValidInput(input5));
        assertFalse(InputValidator.isValidInput(input6));
        assertFalse(InputValidator.isValidInput(input7));
        assertFalse(InputValidator.isValidInput(input8));
    }

    @Test
    public void shouldBeValid() {
        String input1 = "1 book at 10.00";
        String input2 = "1 book of spells at 39.99";
        String input3 = "1 long red imported shoe at 22.33";
        String input4 = "2 black shirts at 12.12";
        assertTrue(InputValidator.isValidInput(input1));
        assertTrue(InputValidator.isValidInput(input2));
        assertTrue(InputValidator.isValidInput(input3));
        assertTrue(InputValidator.isValidInput(input4));
    }
}
