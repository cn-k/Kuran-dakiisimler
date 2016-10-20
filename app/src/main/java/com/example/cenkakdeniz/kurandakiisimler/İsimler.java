package com.example.cenkakdeniz.kurandakiisimler;

/**
 * Created by cenk.akdeniz on 17.10.2016.
 */
public class İsimler {

    private String ad;
    private String anlami;

    public İsimler(String name , String anlami){

        this.ad =name ;
        this.anlami=anlami;

    }

    public String getAnlami() {
        return anlami;
    }

    public void setAnlami(String anlami) {
        this.anlami = anlami;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String name) {
        this.ad = name;
    }

}
