package pass.grafikus;

import pass.controller.Controller;
import pass.model.human.*;

import javax.swing.*;
import java.awt.*;

public class EmberLabel extends JLabel implements EmberVisitor {
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
    }

    @Override
    public void visitHallgato(Hallgato h) {
        setIcon(new ImageIcon(new ImageIcon("Grafikus/src/main/resources/hallgato.png").getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT)));
    }

    @Override
    public void visitTakarito(Takarito t) {
        setIcon(new ImageIcon(new ImageIcon("Grafikus/src/main/resources/takarito.png").getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT)));
    }

    @Override
    public void visitOktato(Oktato o) {
        setIcon(new ImageIcon(new ImageIcon("Grafikus/src/main/resources/oktato.png").getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT)));
    }
}
