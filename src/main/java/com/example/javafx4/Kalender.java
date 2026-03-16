package com.example.javafx4;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

public class Kalender {
    private ArrayList<Jaar> jaren = new ArrayList<>();
    private Jaar jaar2025 = new Jaar(2025);

    public void voegJaarToe(Jaar jaar) {
        jaren.add(jaar);
    }

    public Jaar getJaar2025() {
        return jaar2025;
    }

    public void vulMaandInGrid(int maand, GridPane grid, Label maand_text) {
        String maandNaam = Month.of(maand).getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        maand_text.setText(maandNaam);

        Maand maandObject = jaar2025.getMaand(maand);
        LocalDate startDatum = LocalDate.of(2025, maand, 1);
        int startDagVanDeWeek = startDatum.getDayOfWeek().getValue();
        int aantalDagenInDeMaand = startDatum.lengthOfMonth();

        grid.getChildren().clear();

        int dagNummer = 1;
        Week currentWeek = new Week(1);

        for (int rij = 0; rij < 5; rij++) {
            for (int kolom = 0; kolom < 7; kolom++) {
                if (rij == 0 && kolom >= startDagVanDeWeek - 1) {
                    if (dagNummer <= aantalDagenInDeMaand) {
                        VBox dagVBox = new VBox();
                        Label dagLabel = new Label(String.valueOf(dagNummer));

                        dagVBox.getChildren().add(dagLabel);

                        Dag bestaandeDag = maandObject.getDag(dagNummer);
                        Dag dag;

                        if (bestaandeDag != null) {
                            dag = bestaandeDag;
                        } else {
                            String dagNaam = LocalDate.of(2025, maand, dagNummer).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
                            dag = new Dag(dagNummer, dagNaam);
                        }

                        currentWeek.voegDagToe(dag);

                        if (currentWeek.getDagen().size() == 7) {
                            maandObject.voegWeekToe(currentWeek);
                            currentWeek = new Week(currentWeek.getTijdNummer() + 1);
                        }

                        int aantalEvents = 0;

                        for (Event event : dag.getEvents()) {
                            if (aantalEvents < 6) {
                                HBox dagHBox = new HBox();
                                Label eventNaam = new Label(event.getNaam());
                                Label eventTijd = new Label(" " + event.getTijd());
                                dagHBox.getChildren().add(eventNaam);
                                dagHBox.getChildren().add(eventTijd);
                                dagVBox.getChildren().add(dagHBox);
                            }
                            aantalEvents++;
                        }

                        grid.add(dagVBox, kolom, rij);
                        dagNummer++;

                    }
                } else if (rij > 0 && dagNummer <= aantalDagenInDeMaand) {

                    VBox dagVBox = new VBox();
                    Label dagLabel = new Label(String.valueOf(dagNummer));
                    dagVBox.getChildren().add(dagLabel);

                    Dag bestaandeDag = maandObject.getDag(dagNummer);
                    Dag dag;

                    if (bestaandeDag != null) {
                        dag = bestaandeDag;
                    } else {
                        String dagNaam = LocalDate.of(2025, maand, dagNummer).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
                        dag = new Dag(dagNummer, dagNaam);
                    }
                    currentWeek.voegDagToe(dag);

                    if (currentWeek.getDagen().size() == 7) {
                        maandObject.voegWeekToe(currentWeek);
                        currentWeek = new Week(currentWeek.getTijdNummer() + 1);
                    }

                    int aantalEvents = 0;

                    for (Event event : dag.getEvents()) {
                        if (aantalEvents < 6) {
                            HBox dagHBox = new HBox();
                            Label eventNaam = new Label(event.getNaam());
                            Label eventTijd = new Label(" " + event.getTijd());
                            dagHBox.getChildren().add(eventNaam);
                            dagHBox.getChildren().add(eventTijd);
                            dagVBox.getChildren().add(dagHBox);
                        }
                        aantalEvents++;
                    }

                    grid.add(dagVBox, kolom, rij);
                    dagNummer++;
                }
            }
        }

        if (!currentWeek.getDagen().isEmpty()) {
            maandObject.voegWeekToe(currentWeek);
        }
    }
}
