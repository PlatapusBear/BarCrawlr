package com.barcrawlr.barcrawlr;

import java.io.Serializable;

public class CardInfo implements Serializable {

    private int drawableId;
    private String name;
    private String location;
    private String price;

    public CardInfo(int drawableId, String name, String location, String price) {
        this.drawableId = drawableId;
        this.name = name;
        this.location = location;
        this.price = price;

    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public String getPrice() { return price; }

    public void setPrice(String price) { this.price = price; }


}
