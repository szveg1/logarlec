package pass.grafikus;

import pass.controller.Controller;
import pass.model.labyrinth.Ajto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoorButton extends JButton {
    public DoorButton(Ajto ajto) {
        setBackground(Color.WHITE);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.AjtoHasznalat(ajto);

            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
    }
}