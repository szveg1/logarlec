package pass.grafikus;

import pass.controller.Controller;
import pass.model.graphichelper.DrawObserver;
import pass.model.human.Ember;
import pass.model.item.Targy;

import javax.swing.*;
import java.awt.*;

public class InventoryPanel extends JPanel implements DrawObserver {
    private JPanel[] inventoryItems = new JPanel[5];

    public InventoryPanel() {
        super();
        for (int i = 0; i < 5; i++) {
            inventoryItems[i] = new JPanel();
            inventoryItems[i].setPreferredSize(new Dimension(100, 100));
            inventoryItems[i].setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 3));
            inventoryItems[i].setBackground(Color.GRAY);
            add(inventoryItems[i]);
        }
    }

    @Override
    public void update() {
        System.out.println("InventoryPanel update");
        Ember jelenlegiJatekos = Controller.getSorosJatekos();
        for(JPanel panel : inventoryItems) {
            removeAll();
        }
        for (int i = 0; i < 5; i++) {
            inventoryItems[i].removeAll();
            if (i < jelenlegiJatekos.getItems().size()) {
                Targy targy = jelenlegiJatekos.getItems().get(i);
                inventoryItems[i].add(new TargyInventoryLabel(targy));

                /*if(targy.hasznalhatoE()){
                    inventoryItems[i].setBackground(new Color(134, 216, 131, 255));
                }
                else if(!targy.hasznalhatoE()){
                    inventoryItems[i].setBackground(new Color(200, 0, 0, 255));
                }*/
                inventoryItems[i].setBackground(new Color(134, 216, 131, 255));
            } else {
                inventoryItems[i].add(new JLabel());
                inventoryItems[i].setBackground(Color.GRAY);
            }
            inventoryItems[i].setPreferredSize(new Dimension(100, 100));
            inventoryItems[i].setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 3));
            add(inventoryItems[i]);
        }
        this.revalidate();
        this.repaint();
    }
}
