package pass.grafikus;

import pass.model.labyrinth.Labirintus;
import pass.model.labyrinth.Szoba;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        super("Logarlec");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setResizable(false);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        add(new SzobaPanel(Labirintus.getInstance().getSzobak().get(0), getSize()), BorderLayout.CENTER);
        add(new JTextArea("Ez egy teszt szöveg"), BorderLayout.SOUTH);
        pack();
    }
}
