package pass.grafikus;

import pass.model.TargyVisitor;
import pass.model.graphichelper.DrawObserver;
import pass.model.item.*;

import javax.swing.*;
import java.awt.*;

public abstract class TargyLabel extends JLabel implements TargyVisitor, DrawObserver {
    protected Targy targy;

    public TargyLabel(Targy targy) {
        setPreferredSize(new Dimension(90, 90));
        setBackground(new Color(0, 0, 0, 0));
        this.targy = targy;
        targy.accept(this);
    }

    public void setNumber(int n) {
        JLabel numberLabel = new JLabel(Integer.toString(n));
        numberLabel.setBounds(0, 0, 20, 20);
        numberLabel.setForeground(Color.WHITE);
        add(numberLabel);
    }

    @Override
    public void visit(Logarlec logarlec) {
        setIcon(new ImageIcon(new ImageIcon("Grafikus/src/main/resources/logarlec.png").getImage().getScaledInstance(90, 90, 0)));
    }

    @Override
    public void visit(TVSZ tvsz) {
        setIcon(new ImageIcon(new ImageIcon("Grafikus/src/main/resources/tvsz.png").getImage().getScaledInstance(90, 90, 0)));
        setNumber(tvsz.getVedelmekSzama());
    }

    @Override
    public void visit(Pohar pohar) {
        setIcon(new ImageIcon(new ImageIcon("Grafikus/src/main/resources/pohar.png").getImage().getScaledInstance(90, 90, 0)));
        setNumber(pohar.getVedIdo());
    }

    @Override
    public void visit(Rongy rongy) {
        setIcon(new ImageIcon(new ImageIcon("Grafikus/src/main/resources/rongy.png").getImage().getScaledInstance(90, 90, 0)));
        setNumber(rongy.getMeddigNedves());
    }

    @Override
    public void visit(Maszk maszk) {
        setIcon(new ImageIcon(new ImageIcon("Grafikus/src/main/resources/maszk.png").getImage().getScaledInstance(90, 90, 0)));
        setNumber(maszk.getVedIdo());
    }

    @Override
    public void visit(Legfrissito legfrissito) {
        setIcon(new ImageIcon(new ImageIcon("Grafikus/src/main/resources/legfrissito.png").getImage().getScaledInstance(90, 90, 0)));
        int n = legfrissito.hasznalhatoE() ? 1 : 0;
        setNumber(n);
    }

    @Override
    public void visit(HamisMaszk hamisMaszk) {
        setIcon(new ImageIcon(new ImageIcon("Grafikus/src/main/resources/maszk.png").getImage().getScaledInstance(90, 90, 0)));
        setNumber(3);
    }

    @Override
    public void visit(HamisLec hamisLec) {
        setIcon(new ImageIcon(new ImageIcon("Grafikus/src/main/resources/logarlec.png").getImage().getScaledInstance(90, 90, 0)));
    }

    @Override
    public void visit(HamisTVSZ hamisTVSZ) {
        setIcon(new ImageIcon(new ImageIcon("Grafikus/src/main/resources/tvsz.png").getImage().getScaledInstance(90, 90, 0)));
        setNumber(3);
    }

    @Override
    public void visit(Camembert camembert) {
        setIcon(new ImageIcon(new ImageIcon("Grafikus/src/main/resources/camembert.png").getImage().getScaledInstance(90, 90, 0)));
        int n = camembert.hasznalhatoE() ? 1 : 0;
        setNumber(n);
    }

    @Override
    public void visit(Tranzisztor tranzisztor) {
        setIcon(new ImageIcon(new ImageIcon("Grafikus/src/main/resources/tranzisztor.png").getImage().getScaledInstance(90, 90, 0)));
    }

    @Override
    public abstract void update();

}
