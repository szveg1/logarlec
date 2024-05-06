package pass.controller;

import pass.model.human.Ember;
import pass.model.labyrinth.Labirintus;
import pass.model.labyrinth.Szoba;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public static void startGame() {
        Thread gameThread = new Thread(() -> {
            while (Labirintus.getTimeLeft() >= 0) {
                boolean korvege = true;
                List<Szoba> szobak = new ArrayList<>(Labirintus.getInstance().getSzobak());
                for (Szoba sz : szobak) {
                    List<Ember> emberek = new ArrayList<>(sz.getEmberek());
                    for (Ember e : emberek) {
                        if (!e.getLepett()) {

                        }
                    }
                }
                if (korvege) Controller.Tick(1);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        gameThread.start();
    }

    public static void kovetkezoLepes(){
        boolean mindenkiLepett = true;
        for (Szoba sz : Labirintus.getInstance().getSzobak()) {
            for (Ember e : sz.getEmberek()) {
                if (!e.getLepett()) {
                    mindenkiLepett = false;
                    break;
                }
            }
        }
        if (mindenkiLepett) {

        }
    }
}
