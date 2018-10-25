package com.barcrawlr.barcrawlr;

public class CardInfo {

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

    public String getLocation() {
        return location;
    }

    public String getPrice(){return price;}

    public String setPrice(String price){this.price=price;
        return price;
    }
}
