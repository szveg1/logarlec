package pass.grafikus;

import pass.model.TargyVisitorGrafikus;
import pass.model.graphichelper.DrawObserver;
import pass.model.item.*;

import javax.swing.*;
import java.awt.*;

public abstract class TargyLabel extends JLabel implements TargyVisitorGrafikus, DrawObserver {
    protected Targy targy;

    public TargyLabel(Targy targy) {
        setPreferredSize(new Dimension(100, 100));
        setBackground(new Color(0, 0, 0, 0));
        this.targy = targy;
        targy.accept(this);
        targy.addObserver(this);
    }

    @Override
    public void visit(Logarlec logarlec) {
        setIcon(new ImageIcon(new ImageIcon("Grafikus/src/main/resources/logarlec.png").getImage().getScaledInstance(90, 90, 0)));
    }

    @Override
    public void visit(TVSZ tvsz) {
        setIcon(new ImageIcon(new ImageIcon("Grafikus/src/main/resources/tvsz.png").getImage().getScaledInstance(90, 90, 0)));
    }

    @Override
    public void visit(Pohar pohar) {
        setIcon(new ImageIcon(new ImageIcon("Grafikus/src/main/resources/pohar.png").getImage().getScaledInstance(90, 90, 0)));
    }

    @Override
    public void visit(Rongy rongy) {
        setIcon(new ImageIcon(new ImageIcon("Grafikus/src/main/resources/rongy.png").getImage().getScaledInstance(90, 90, 0)));
    }

    @Override
    public void visit(Maszk maszk) {
        setIcon(new ImageIcon(new ImageIcon("Grafikus/src/main/resources/maszk.png").getImage().getScaledInstance(90, 90, 0)));
    }

    @Override
    public void visit(Legfrissito legfrissito) {
        setIcon(new ImageIcon(new ImageIcon("Grafikus/src/main/resources/legfrissito.png").getImage().getScaledInstance(90, 90, 0)));
    }

    @Override
    public void visit(HamisMaszk hamisMaszk) {
        setIcon(new ImageIcon(new ImageIcon("Grafikus/src/main/resources/maszk.png").getImage().getScaledInstance(90, 90, 0)));
    }

    @Override
    public void visit(HamisLec hamisLec) {
        setIcon(new ImageIcon(new ImageIcon("Grafikus/src/main/resources/logarlec.png").getImage().getScaledInstance(90, 90, 0)));
    }

    @Override
    public void visit(HamisTVSZ hamisTVSZ) {
        setIcon(new ImageIcon(new ImageIcon("Grafikus/src/main/resources/tvsz.png").getImage().getScaledInstance(90, 90, 0)));
    }

    @Override
    public void visit(Camembert camembert) {
        setIcon(new ImageIcon(new ImageIcon("Grafikus/src/main/resources/camembert.png").getImage().getScaledInstance(90, 90, 0)));
    }

    @Override
    public void visit(Tranzisztor tranzisztor) {
        setIcon(new ImageIcon(new ImageIcon("Grafikus/src/main/resources/tranzisztor.png").getImage().getScaledInstance(90, 90, 0)));
    }

    @Override
    public abstract void update();

}
