package pass.grafikus;

import pass.controller.Controller;
import pass.model.human.*;

import javax.swing.*;
import java.awt.*;

/**
 * Az EmberLabel osztály egy címkét valósít meg, amelyet az Ember objektumok megjelenítésére használnak.
 */
public class EmberLabel extends JLabel implements EmberVisitor {
    /**
     * Konstruktor, amely létrehoz egy EmberLabel objektumot egy adott Ember objektumhoz.
     *
     * @param e Az Ember objektum, amelyhez a címke tartozik.
     */
    EmberLabel(Ember e) {
        setPreferredSize(new Dimension(100, 100));
        setBackground(new Color(0, 0, 0, 0));
        e.accept(this);
        if (e == Controller.getSorosJatekos()) {
            setBorder(BorderFactory.createLineBorder(Color.RED, 5));
        }
        if (!e.getEletbenVan()) {
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        }
        if (e.getAjult()) {
            setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
        }
    }

    /**
     * A Hallgato objektumokat kezeli, amikor azokat az EmberVisitor interfész metódusai hívják.
     *
     * @param h A Hallgato objektum, amit kezelni kell.
     */
    @Override
    public void visitHallgato(Hallgato h) {
        setIcon(new ImageIcon(new ImageIcon("Grafikus/src/main/resources/hallgato.png").getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT)));
    }

    /**
     * A Takarito objektumokat kezeli, amikor azokat az EmberVisitor interfész metódusai hívják.
     *
     * @param t A Takarito objektum, amit kezelni kell.
     */
    @Override
    public void visitTakarito(Takarito t) {
        setIcon(new ImageIcon(new ImageIcon("Grafikus/src/main/resources/takarito.png").getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT)));
    }

    /**
     * Az Oktato objektumokat kezeli, amikor azokat az EmberVisitor interfész metódusai hívják.
     *
     * @param o Az Oktato objektum, amit kezelni kell.
     */
    @Override
    public void visitOktato(Oktato o) {
        setIcon(new ImageIcon(new ImageIcon("Grafikus/src/main/resources/oktato.png").getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT)));
    }
}