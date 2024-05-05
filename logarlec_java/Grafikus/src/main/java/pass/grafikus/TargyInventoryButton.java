package pass.grafikus;

import pass.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TargyInventoryButton extends TargyButton {

    public TargyInventoryButton() {
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Controller.Hasznal();
            }
        });
    }
}
