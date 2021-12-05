package com.npkompleet.receipt.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.npkompleet.model.Item;
import com.npkompleet.receipt.InputParser;

import org.junit.jupiter.api.Test;

public class InputParserTest {
    @Test
    public void shouldParseInputProperly() {
        Item item = InputParser.parseItem("1 book at 10.00");
        assertTrue(item.isBasicTaxExempt());
        assertFalse(item.isImported());
        assertEquals(1, item.getQuantity());
        assertEquals(10.00, item.getAmount(), 0.02);
        assertEquals("book", item.getName());

        Item item1 = InputParser.parseItem("7 kilogram of medical herbs at 99.99");
        assertTrue(item1.isBasicTaxExempt());
        assertFalse(item1.isImported());
        assertEquals("kilogram of medical herbs", item1.getName());
        assertEquals(7, item1.getQuantity());
        assertEquals(99.99f, item1.getAmount());
    }

    @Test
    public void shouldParseInputWithPluralProperly() {
        Item item = InputParser.parseItem("2 imported medical pills at 23.23");
        assertTrue(item.isImported());
        assertEquals(2, item.getQuantity());
        assertEquals(23.23, item.getAmount(), 0.02);
        assertEquals("medical pills", item.getName());
        assertTrue(item.isBasicTaxExempt());
    }

    @Test
    public void shouldParseInputWithImportedProperly() {
        Item item = InputParser.parseItem("3 cans of imported mushrooms at 9.01");
        assertTrue(item.isImported());
        assertEquals(3, item.getQuantity());
        assertEquals(9.01, item.getAmount(), 0.02);
        assertEquals("cans of mushrooms", item.getName());
        assertFalse(item.isBasicTaxExempt());
    }
}
