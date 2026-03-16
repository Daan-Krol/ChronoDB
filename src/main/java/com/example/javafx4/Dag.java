package com.example.javafx4;

import java.util.ArrayList;

public class Dag extends TijdItem {
    private String dagNaam;
    private ArrayList<Event> events = new ArrayList<>();

    public Dag(int tijdNummer, String dagNaam) {
        this.tijdNummer = tijdNummer;
        this.dagNaam = dagNaam;
    }

    public String getDagNaam() {
        return dagNaam;
    }

    public void voegEventToe(Event nieuwEvent) {
        for (Event event : events) {
            if (event.getNaam().equals(nieuwEvent.getNaam()) && event.getTijd().equals(nieuwEvent.getTijd())) {
                return;
            }
        }
        events.add(nieuwEvent);
    }

    public void verwijderEvent(String event) {
        events.removeIf(e -> e.getNaam().equals(event));

//        for (Event eventObject : events) {
//            if (eventObject.getNaam().equals(event)) {
//                events.remove(eventObject);
//            }
//        }
    }

    public ArrayList<Event> getEvents() {
        return events;
    }
}
