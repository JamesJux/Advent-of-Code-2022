package com.advent.day7;

import java.util.ArrayList;
import java.util.List;

public class Ordner {
    public String name;
    public List<Ordner> ordnerListe = new ArrayList<Ordner>();
    public List<Datei> dateiListe = new ArrayList<Datei>();
    public Ordner parent;

    public Ordner(String name, Ordner parent) {
        super();
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public List<Ordner> getOrdnerListe() {
        return ordnerListe;
    }

    public void addToOrdnerListe(Ordner ordner) {
        ordnerListe.add(ordner);
    }

    public List<Datei> getDateiListe() {
        return dateiListe;
    }

    public void addToDateiListe(Datei datei) {
        dateiListe.add(datei);
    }

    public Ordner getParent() {
        return parent;
    }

    public void setParent(Ordner parent) {
        this.parent = parent;
    }

    public int getGroesse() {
        int groesse = 0;
        for (Datei datei : dateiListe) {
            groesse += datei.getGroessse();
        }
        for (Ordner ordner : ordnerListe) {
            groesse += ordner.getGroesse();
        }
        return groesse;
    }

    public Ordner getOrdner(String string) {
        for (Ordner ordner : ordnerListe) {
            if (ordner.getName().equals(string))
                return ordner;
        }
        return null;
    }

    public List<Ordner> getSubordnerList(List<Ordner> list) {
        list.add(this);
        for (Ordner ordner : ordnerListe) {
            ordner.getSubordnerList(list);
        }
        return list;
    }
}
