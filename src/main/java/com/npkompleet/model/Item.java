package com.npkompleet.model;

/**
 * This class represents the good or item sold
 */
public class Item {
    private String name;
    private int quantity;
    private float amount;
    private boolean isImported;
    private boolean isSalesTaxExempt;

    public Item() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public boolean isImported() {
        return isImported;
    }

    public void setImported(boolean isImported) {
        this.isImported = isImported;
    }

    public boolean isSalesTaxExempt() {
        return isSalesTaxExempt;
    }

    public void setSalesTaxExempt(boolean isSalesTaxExempt) {
        this.isSalesTaxExempt = isSalesTaxExempt;
    }

}
