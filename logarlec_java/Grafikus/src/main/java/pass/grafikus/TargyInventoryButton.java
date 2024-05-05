package pass.grafikus;

import pass.controller.Controller;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TargyInventoryButton extends TargyButton {

    public TargyInventoryButton() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    //Controller.TargyHasznal();
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    //Controller.TargyEldob();
                }
            }
        });
    }
}
