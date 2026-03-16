package com.example.javafx4;

import javafx.scene.control.Label;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Commando {
    private String user_input;
    private Kalender kalender;
    private Label feedbackLabel;

    public Commando(String user_input, Kalender kalender, Label feedbackLabel) {
        this.user_input = user_input;
        this.kalender = kalender;
        this.feedbackLabel = feedbackLabel;
    }

    public void verwerkInput() {
        System.out.println("3");

        String[] strings = user_input.split(" ");
        String type_actie = strings[0].toUpperCase();

        if (type_actie.equals("ADD")) {
            if (strings[2].toUpperCase().equals("REPEAT")) {
                if (strings[3].toUpperCase().equals("UNTIL")) {
                    String[] eindDatum = strings[4].split("-");
                    int eindDag = Integer.valueOf(eindDatum[0]);
                    int eindMaand = Integer.valueOf(eindDatum[1]);
                    String naam = strings[5];
                    String tijd = strings[6];
                    String[] datum = strings[7].split("-");
                    int dag = Integer.valueOf(datum[0]);
                    int maand = Integer.valueOf(datum[1]);
                    String dagNaam = LocalDate.of(2025, maand, dag).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);

                    voegHerhalendEventToe(maand, dag, dagNaam, eindMaand, eindDag, naam, tijd, kalender.getJaar2025());
                }else {
                    String type_input = strings[1];
                    String naam = strings[3];
                    String tijd = strings[4];
                    String[] datum = strings[5].split("-");
                    int dag = Integer.valueOf(datum[0]);
                    int maand = Integer.valueOf(datum[1]);
                    String dagNaam = LocalDate.of(2025, maand, dag).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);

                    voegHerhalendEventToe(maand, dag, dagNaam, 12, 31, naam, tijd, kalender.getJaar2025());
                }

            } else {
                String type_input = strings[1];
                String naam = strings[2];
                System.out.print(strings[3]);
                System.out.print(strings[4]);
                System.out.print(strings[1]);
                System.out.print(strings[2]);

                String tijd = strings[3];
                String[] datum = strings[4].split("-");
                int dag = Integer.valueOf(datum[0]);
                int maand = Integer.valueOf(datum[1]);

                voegEventToe(maand, dag, naam, tijd, kalender.getJaar2025());
            }
        }

        else if (type_actie.equals("REMOVE") || type_actie.equals("RMV")) {
            if (strings[2].toUpperCase().equals("REPEAT")) {
                if (strings[3].toUpperCase().equals("UNTIL")) {
                    String[] eindDatum = strings[4].split("-");
                    int eindDag = Integer.valueOf(eindDatum[0]);
                    int eindMaand = Integer.valueOf(eindDatum[1]);
                    String naam = strings[5];
                    String tijd = strings[6];
                    String[] datum = strings[7].split("-");
                    int dag = Integer.valueOf(datum[0]);
                    int maand = Integer.valueOf(datum[1]);
                    String dagNaam = LocalDate.of(2025, maand, dag).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);

                    verwijderHerhalendEvent(maand, dag, dagNaam, eindMaand, eindDag, naam, tijd, kalender.getJaar2025());
                }else {
                    String type_input = strings[1];
                    String naam = strings[3];
                    String tijd = strings[4];
                    String[] datum = strings[5].split("-");
                    int dag = Integer.valueOf(datum[0]);
                    int maand = Integer.valueOf(datum[1]);
                    String dagNaam = LocalDate.of(2025, maand, dag).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);

                    verwijderHerhalendEvent(maand, dag, dagNaam, 12, 31, naam, tijd, kalender.getJaar2025());
                }
            } else {
                String type_input = strings[1];
                String naam = strings[2];
                String tijd = strings[3];
                String[] datum = strings[4].split("-");
                int dag = Integer.valueOf(datum[0]);
                int maand = Integer.valueOf(datum[1]);

                verwijderEvent(maand, dag, naam, tijd, kalender.getJaar2025());
            }

        }

        else {
            feedbackLabel.setText(" Commando niet herkent! ");
        }

    }

    private void voegEventToe(int maand, int dagNummer, String eventNaam, String tijd, Jaar jaar) {

        Maand maandObject = jaar.getMaand(maand);
        Week week = maandObject.getWeken().get((dagNummer - 1) / 7);
        Dag dag = week.getDagen().get((dagNummer - 1) % 7);
        Event event = new Event();
        event.setNaam(eventNaam);
        event.setTijd(tijd);
        dag.voegEventToe(event);
        kalender.vulMaandInGrid(maand, Main.maand_grid, Main.maand_text);
        feedbackLabel.setText(" Event '" + eventNaam + "' toegevoegd ");
    }

    private void verwijderEvent(int maand, int dagNummer, String eventNaam, String tijd, Jaar jaar) {
        Maand maandObject = jaar.getMaand(maand);
        Week week = maandObject.getWeken().get((dagNummer - 1) / 7);
        Dag dag = week.getDagen().get((dagNummer - 1) % 7);
        System.out.println(dag.getEvents().size());
        dag.verwijderEvent(eventNaam);
        kalender.vulMaandInGrid(maand, Main.maand_grid, Main.maand_text);
        feedbackLabel.setText(" Event '" + eventNaam + "' verwijdert ");
    }

    private void voegHerhalendEventToe(int startMaand, int startDagNummer, String startDagNaam, int eindMaand, int eindDagNummer, String eventNaam, String tijd, Jaar jaar){
        int maand = startMaand;
        boolean eindDatumBereikt = false;

        while (maand <= eindMaand && !eindDatumBereikt) {
            Maand maandObject = jaar.getMaand(maand);

            for (int w = 0; w < maandObject.getWeken().size(); w++) {
                Week week = maandObject.getWeken().get(w);

                for (int d = 0; d < week.getDagen().size(); d++) {
                    Dag dag = week.getDagen().get(d);

                    if (maand == eindMaand && dag.getTijdNummer() > eindDagNummer) {
                        eindDatumBereikt = true;
                        break;
                    }
                    if (dag.getDagNaam().equals(startDagNaam)) {
                        Event event = new Event();
                        event.setNaam(eventNaam);
                        event.setTijd(tijd);
                        dag.voegEventToe(event);
                    }
                }
                if (eindDatumBereikt) {
                    break;
                }
            }

            maand++;
        }

        kalender.vulMaandInGrid(startMaand, Main.maand_grid, Main.maand_text);
        feedbackLabel.setText("Herhalend event '" + eventNaam + "'");

    }

    private void verwijderHerhalendEvent(int startMaand, int startDagNummer, String startDagNaam, int eindMaand, int eindDagNummer, String eventNaam, String tijd, Jaar jaar){
        int maand = startMaand;
        boolean eindDatumBereikt = false;

        while (maand <= eindMaand && !eindDatumBereikt) {
            Maand maandObject = jaar.getMaand(maand);

            for (int w = 0; w < maandObject.getWeken().size(); w++) {
                Week week = maandObject.getWeken().get(w);

                for (int d = 0; d < week.getDagen().size(); d++) {
                    Dag dag = week.getDagen().get(d);

                    if (maand == eindMaand && dag.getTijdNummer() > eindDagNummer) {
                        eindDatumBereikt = true;
                        break;
                    }
                    if (dag.getDagNaam().equals(startDagNaam)) {
                        dag.verwijderEvent(eventNaam);
                    }
                }
                if (eindDatumBereikt) {
                    break;
                }
            }

            maand++;
        }

        kalender.vulMaandInGrid(startMaand, Main.maand_grid, Main.maand_text);
        feedbackLabel.setText("Herhalend event verwijdert '" + eventNaam + "'");

    }
}

