package pass.grafikus;

import pass.controller.Controller;
import pass.model.graphichelper.DrawObserver;
import pass.model.human.Ember;
import pass.model.item.Targy;

import javax.swing.*;
import java.awt.*;

/**
 * Az InventoryPanel osztály egy panelt valósít meg, amelyben az Ember objektumok tárgyait jelenítjük meg.
 */
public class InventoryPanel extends JPanel implements DrawObserver {
    private JPanel[] inventoryItems = new JPanel[5];

    /**
     * Konstruktor, amely létrehoz egy InventoryPanel objektumot.
     */
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

    /**
     * Frissíti a panelt, amikor a DrawObserver interfész update metódusát meghívják.
     */
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

                if(targy.hasznalhatoE()){
                    inventoryItems[i].setBackground(new Color(134, 216, 131, 255));
                }
                else if(!targy.hasznalhatoE()){
                    inventoryItems[i].setBackground(new Color(200, 0, 0, 255));
                }
                if(targy.vanEPar()){
                    inventoryItems[i].setBackground(Color.blue);
                }
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