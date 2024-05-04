package pass.grafikus;

import javax.swing.*;
import java.awt.*;

public class DoorButton extends JButton {
    public DoorButton() {
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
    }
}