package com.barcrawlr.barcrawlr;

public class Bar {
    private String name;
    private boolean attended;

    public boolean haveAttended() {
        return attended;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttended(boolean attended) {
        this.attended = attended;
    }
}
