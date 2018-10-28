package com.barcrawlr.barcrawlr;

import java.io.Serializable;

public class CardInfo implements Serializable {

    private int drawableId;
    private String name;
    private String location;
    private String price;
    private String longDescription;
    private String shortDescription;


    public CardInfo(int drawableId, String name, String location, String price, String longDescription, String shortDescription) {
        this.drawableId = drawableId;
        this.name = name;
        this.location = location;
        this.price = price;
        this.longDescription = longDescription;
        this.shortDescription = shortDescription;

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

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

}
