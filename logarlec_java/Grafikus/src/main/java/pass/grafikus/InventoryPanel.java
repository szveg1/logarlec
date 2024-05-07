package pass.grafikus;

import pass.model.graphichelper.DrawObserver;

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
            inventoryItems[i].setBackground(Color.WHITE);
            add(inventoryItems[i]);
        }
    }
    @Override
    public void update() {
        // TODO: implement
    }
}
