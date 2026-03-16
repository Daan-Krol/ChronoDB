package com.example.javafx4;

import java.util.ArrayList;

public class Maand extends TijdItem{
    private ArrayList<Week> weken = new ArrayList<>();

    public Maand(int tijdNummer) {
        this.tijdNummer = tijdNummer;
    }

    public ArrayList<Week> getWeken() {
        return weken;
    }

    public void voegWeekToe(Week week) {
        weken.add(week);
    }

    public Dag getDag(int dagNummer) { // krijg dag object met een dag nummer,
        for (Week week : weken) {
            for (Dag dag : week.getDagen()) {
                if (dag.getTijdNummer() == dagNummer) return dag;
            }
        }
        return null;
    }
}
