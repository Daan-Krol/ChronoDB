package com.example.javafx4;

import javafx.scene.control.Label;

import java.util.ArrayList;

public class Gebruiker {
    Kalender kalender = new Kalender();
    ArrayList<String> commandos = new ArrayList<>();

    public void newInput(String input, Label feedbackLabel) {
        if (!input.isBlank()) {
            commandos.add(input);
        }

        Commando commando = new Commando(input, kalender, feedbackLabel);
        commando.verwerkInput();
        System.out.println("2");


    }

}
