package pass.grafikus;

import pass.controller.Controller;
import pass.model.human.Ember;
import pass.model.labyrinth.Ajto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A DoorButton osztály egy gombot valósít meg, amelyet az Ajto objektumok kezelésére használnak.
 */
public class DoorButton extends JButton {
    /**
     * Konstruktor, amely létrehoz egy DoorButton objektumot egy adott Ajto objektumhoz.
     *
     * @param ajto Az Ajto objektum, amelyhez a gomb tartozik.
     */
    public DoorButton(Ajto ajto) {
        setBackground(Color.WHITE);
        addActionListener(new ActionListener() {
            /**
             * Definiálja, hogy mi történik, amikor a gombra kattintanak.
             *
             * @param e Az esemény, amely a gombra kattintást jelzi.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Ember ember = Controller.getSorosJatekos();
                Controller.AjtoHasznalat(ajto, ember);
                ember.notifyObservers();
            }
        });
    }

    /**
     * A gomb grafikus komponensét festi ki.
     *
     * @param g A grafikus kontextus, amelyre rajzolunk.
     */

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
    }
}