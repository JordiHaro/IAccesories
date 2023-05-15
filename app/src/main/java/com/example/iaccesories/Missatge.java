package com.example.iaccesories;

public class Missatge {

    private String nomUsuari, missatge, mUid;


    public Missatge(String nomUsuari, String missatge, String mUid) {
        this.nomUsuari = nomUsuari;
        this.missatge = missatge;
        this.mUid = mUid;
    }

    public String getNomUsuari() {
        return nomUsuari;
    }

    public void setNomUsuari(String nomUsuari) {
        this.nomUsuari = nomUsuari;
    }

    public String getMissatge() {
        return missatge;
    }

    public void setMissatge(String missatge) {
        this.missatge = missatge;
    }

    public String getmUid() {
        return mUid;
    }

    public void setmUid(String mUid) {
        this.mUid = mUid;
    }

    @Override
    public String toString() {
        return nomUsuari + ": " + missatge;
    }
}
