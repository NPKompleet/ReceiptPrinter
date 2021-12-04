package com.npkompleet.receipt.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(false, InputValidator.isValidInput(input1));
        assertEquals(false, InputValidator.isValidInput(input2));
        assertEquals(false, InputValidator.isValidInput(input3));
        assertEquals(false, InputValidator.isValidInput(input4));
        assertEquals(false, InputValidator.isValidInput(input5));
        assertEquals(false, InputValidator.isValidInput(input6));
    }

    @Test
    public void shouldBeValid() {
        String input1 = "1 book at 10.00";
        String input2 = "1 book of spells at 39.99";
        String input3 = "1 long red imported shoe at 22.33";
        String input4 = "2 black shirts at 12.12";
        assertEquals(true, InputValidator.isValidInput(input1));
        assertEquals(true, InputValidator.isValidInput(input2));
        assertEquals(true, InputValidator.isValidInput(input3));
        assertEquals(true, InputValidator.isValidInput(input4));
    }
}
