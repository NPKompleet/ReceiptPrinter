package com.npkompleet.model;

public class Tax {

    private Item item;

    public Tax(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}
