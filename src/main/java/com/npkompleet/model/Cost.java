package com.npkompleet.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Cost {

    private Item item;

    public Cost(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public float getItemCost() {
        return item.getAmount() + getItemTotalSalesTax();
    }

    public float getItemTotalSalesTax() {
        float importTax = item.isImported() ? getRoundedUpTax(0.05f * item.getAmount()) : 0;
        float basicTax = item.isBasicTaxExempt() ? 0 : getRoundedUpTax(0.1f * item.getAmount());
        return importTax + basicTax;
    }

    /**
     * Rounds up the sales tax of an item to the nearest 0.05.
     * 
     * @param tax The sales tax.
     * @return The roundedUp tax.
     */
    private float getRoundedUpTax(float tax) {
        if (tax < 0)
            throw new IllegalArgumentException();

        // Round up number to the nearest 0.05 by
        // - First rounding up to 2 decimal places
        // - If the second decimal position is less than 5,
        // round up to 5.
        // - If it is greater than 5, set to 0
        // then increment first decimal position by 1
        // - If first decimal position was 9 before increment,
        // increment integer part by 1
        float salesTax = round(tax, 2);
        String taxValueString = String.valueOf(salesTax);
        String[] valueArray = taxValueString.split("\\.");
        int integerComponent = Integer.parseInt(valueArray[0]);
        String decimalComponentStr = valueArray[1];

        int firstDecimalNumber = Integer.parseInt(String.valueOf(decimalComponentStr.charAt(0)));
        int secondDecimalNumber = decimalComponentStr.length() == 2
                ? Integer.parseInt(String.valueOf(decimalComponentStr.charAt(1)))
                : 0;

        if (secondDecimalNumber < 5 && secondDecimalNumber != 0) {
            secondDecimalNumber = 5;
        } else if (secondDecimalNumber > 5) {
            secondDecimalNumber = 0;
            integerComponent = firstDecimalNumber == 9 ? integerComponent + 1 : integerComponent;
            firstDecimalNumber = firstDecimalNumber == 9 ? 0 : firstDecimalNumber + 1;
        }
        String roundedUpTaxString = integerComponent + "." + firstDecimalNumber + secondDecimalNumber;
        return Float.parseFloat(roundedUpTaxString);
    }

    private float round(float value, int places) {
        BigDecimal bigDecimal = new BigDecimal(Float.toString(value));
        bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
        return bigDecimal.floatValue();
    }
}
