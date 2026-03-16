package com.example.javafx4;

import java.util.ArrayList;

public class Jaar extends TijdItem{
    private ArrayList<Maand> maanden = new ArrayList<>();

    public Jaar(int tijdNummer) {
        this.tijdNummer = tijdNummer;

        for (int maand_nummer = 1; maand_nummer <= 12; maand_nummer++) {
            Maand maand = new Maand(maand_nummer);
            voegMaandToe(maand);
        }
    }

    private void voegMaandToe(Maand maand) {
        maanden.add(maand);
    }

    public ArrayList<Maand> getMaanden() {
        return maanden;
    }

    public Maand getMaand(int maand_nummer) {
        return maanden.get(maand_nummer - 1); // want maanden is een array en begint vanaf 0 maar er is geen maand 0
    }
}
