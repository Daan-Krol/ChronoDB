package com.example.javafx4;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class ChronoDB_Controller {
    @FXML
    private Label maand_text;
    @FXML
    private TextArea user_promt;
    @FXML
    private Button SendButton;
    @FXML
    private GridPane MaandGrid;
    @FXML
    private Button GetLastCommandButton;
    @FXML
    private Button AddButton;
    @FXML
    private Button RemoveButton;
    @FXML
    private Button EventButton;
    @FXML
    private Button VorigeMaandButton;
    @FXML
    private Button VolgendeMaandButton;
    @FXML
    private Label FeedbackLabel;

    @FXML
    public void initialize() {
        String monthString = LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
        maand_text.setText(monthString);
        user_promt.setText("");
        SendButton.setOnAction(event -> handleSendButton());
        GetLastCommandButton.setOnAction(event -> handleGetLastCommandButton());
        AddButton.setOnAction(event -> handleAddButton());
        RemoveButton.setOnAction(event -> handleRemoveButton());
        EventButton.setOnAction(event -> handleEventButton());
        VorigeMaandButton.setOnAction(event -> handleVorigeMaandButton());
        VolgendeMaandButton.setOnAction(event -> handleVolgendeMaandButton());
        Main.setMaand_grid(MaandGrid);
        Main.setMaand_text(maand_text);

        int i = 1;
        while (i<=12) {
            Main.gebruiker.kalender.vulMaandInGrid(i, MaandGrid, maand_text);
            i++;
        }
        Main.gebruiker.kalender.vulMaandInGrid(LocalDate.now().getMonth().getValue(), MaandGrid, maand_text);
    }

    private void handleVorigeMaandButton() {
        String maandNaam = maand_text.getText();
        int maandNummer = Month.valueOf(maandNaam.toUpperCase()).getValue();
        if (maandNummer > 1) {
            maandNummer--;
            Main.gebruiker.kalender.vulMaandInGrid(maandNummer, MaandGrid, maand_text);
        }
    }

    private void handleVolgendeMaandButton() {
        String maandNaam = maand_text.getText();
        int maandNummer = Month.valueOf(maandNaam.toUpperCase()).getValue();

        if (maandNummer < 12) {
            maandNummer++;
            Main.gebruiker.kalender.vulMaandInGrid(maandNummer, MaandGrid, maand_text);
        }

    }

    private void handleGetLastCommandButton() {
        user_promt.setText(Main.gebruiker.commandos.get(Main.gebruiker.commandos.size()-1));
    }

    private void handleAddButton() {
        user_promt.setText("ADD");
    }

    private void handleRemoveButton() {
        user_promt.setText("REMOVE");
    }

    private void handleEventButton() {
        user_promt.setText(user_promt.getText()+ " EVENT");
    }

    private void handleSendButton() {
        Main.gebruiker.newInput(user_promt.getText(), FeedbackLabel);
        System.out.println("1");
        user_promt.clear();
    }

}