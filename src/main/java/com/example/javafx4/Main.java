package com.example.javafx4;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Main {
    public static Gebruiker gebruiker = new Gebruiker();
    public static GridPane maand_grid;
    public static Label maand_text;

    public static void setMaand_grid(GridPane maand_grid) {
        Main.maand_grid = maand_grid;
    }

    public static void setMaand_text(Label maand_text) {
        Main.maand_text = maand_text;
    }

    public static void main(String[] args) {
        ChronoDB.main(args);
    }


}
