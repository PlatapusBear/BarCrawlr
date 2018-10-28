package com.barcrawlr.barcrawlr;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Bar extends RealmObject{
    @PrimaryKey
    private String barName;
    private boolean attended;

    public boolean haveAttended() {
        return attended;
    }

    public String getBarName() {
        return barName;
    }

    public void setBarName(String barName) {
        this.barName = barName;
    }

    public void setAttended(boolean attended) {
        this.attended = attended;
    }
}
