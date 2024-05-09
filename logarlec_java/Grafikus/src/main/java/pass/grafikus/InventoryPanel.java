package pass.grafikus;

import pass.controller.Controller;
import pass.model.graphichelper.DrawObserver;
import pass.model.human.Ember;
import pass.model.item.Targy;

import javax.swing.*;
import java.awt.*;

public class InventoryPanel extends JPanel implements DrawObserver {
    private JLabel[] inventoryItems = new JLabel[5];

    public InventoryPanel() {
        super();
        for (int i = 0; i < 5; i++) {
            inventoryItems[i] = new JLabel();
            inventoryItems[i].setPreferredSize(new Dimension(100, 100));
            inventoryItems[i].setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 3));
            inventoryItems[i].setBackground(Color.GRAY);
            add(inventoryItems[i]);
        }
    }

    @Override
    public void update() {
        Ember jelenlegiJatekos = Controller.getSorosJatekos();
        this.removeAll();
        for (int i = 0; i < 5; i++) {
            if (i < jelenlegiJatekos.getItems().size()) {
                Targy targy = jelenlegiJatekos.getItems().get(i);
                inventoryItems[i] = new TargyInventoryLabel(targy);
            } else {
                inventoryItems[i] = new JLabel();
                inventoryItems[i].setPreferredSize(new Dimension(100, 100));
                inventoryItems[i].setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 3));
                inventoryItems[i].setBackground(Color.GRAY);
            }
            add(inventoryItems[i]);
        }
        this.revalidate();
        this.repaint();
    }
}
