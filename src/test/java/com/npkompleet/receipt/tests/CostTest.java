package com.npkompleet.receipt.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.npkompleet.model.Cost;
import com.npkompleet.model.Item;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CostTest {
    @Mock
    Item itemMock;

    @InjectMocks
    Cost cost;

    @Test
    public void shouldRoundUpTaxProperly() {
        when(itemMock.getAmount()).thenReturn(14.99f);
        when(itemMock.isImported()).thenReturn(false);
        when(itemMock.isBasicTaxExempt()).thenReturn(false);
        assertEquals(1.50f, cost.getItemTotalSalesTax());

        when(itemMock.isImported()).thenReturn(true);
        assertEquals(2.25f, cost.getItemTotalSalesTax());

        when(itemMock.getAmount()).thenReturn(0f);
        assertEquals(0.00f, cost.getItemTotalSalesTax());

        when(itemMock.getAmount()).thenReturn(-14.99f);
        assertThrows(IllegalArgumentException.class, () -> cost.getItemTotalSalesTax());
    }

    @Test
    public void shouldCalculateAccurateTotalCost() {
        when(itemMock.getAmount()).thenReturn(14.99f);
        when(itemMock.isImported()).thenReturn(false);
        when(itemMock.isBasicTaxExempt()).thenReturn(false);
        assertEquals(16.49f, cost.getItemCost());

        when(itemMock.getAmount()).thenReturn(11.25f);
        when(itemMock.isImported()).thenReturn(true);
        when(itemMock.isBasicTaxExempt()).thenReturn(true);
        assertEquals(11.85f, cost.getItemCost());

        when(itemMock.getAmount()).thenReturn(11.25f);
        when(itemMock.isImported()).thenReturn(true);
        when(itemMock.isBasicTaxExempt()).thenReturn(false);
        assertEquals(13.00f, cost.getItemCost());
    }
}
