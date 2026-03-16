package com.example.javafx4;

import java.util.ArrayList;

public class Week extends TijdItem {
    private ArrayList<Dag> dagen = new ArrayList<>();
    private ArrayList<Notitie> notities = new ArrayList<>();

    public Week(int tijdNummer) {
        this.tijdNummer = tijdNummer;
    }

    public int getWeek_nummer() {
        return tijdNummer;
    }

    public void voegDagToe(Dag dag) {
        dagen.add(dag);
    }

    public void voegNotitieToe(Notitie notitie) {
        notities.add(notitie);
    }

    public void verwijderNotitie(Notitie notitie) {
        notities.remove(notitie);
    }

    public ArrayList<Dag> getDagen() {
        return dagen;
    }
}
