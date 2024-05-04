package pass.grafikus;

import pass.controller.Controller;
import pass.model.labyrinth.Labirintus;
import pass.model.labyrinth.Szoba;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GameFrame extends JFrame {
    public GameFrame() {
        super("Logarlec");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setResizable(false);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPopupMenu popupMenu = getPopupMenu();
        Action escapeAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (popupMenu.isVisible()) {
                    popupMenu.setVisible(false);
                } else {
                    popupMenu.show(GameFrame.this, 8, 31);
                }
            }
        };

        ((JComponent) GameFrame.this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESCAPE");
        ((JComponent) GameFrame.this.getContentPane()).getActionMap().put("ESCAPE", escapeAction);

        GameFrame.this.setFocusable(true);



        add(new SzobaPanel(Labirintus.getInstance().getSzobak().get(0), getSize()), BorderLayout.CENTER);
        add(new JTextArea("Ez egy teszt szöveg"), BorderLayout.SOUTH);
        pack();
    }

    private JPopupMenu getPopupMenu() {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem saveItem = new JMenuItem("Save Game and Exit");
        saveItem.addActionListener(e -> {

            String fileName = JOptionPane.showInputDialog(GameFrame.this,
                    "Enter the file name:", "Save As", JOptionPane.PLAIN_MESSAGE);

            if (fileName != null && !fileName.trim().isEmpty()) {
                Controller.Save(fileName);
                Controller.reset();
                Main.setDisplayedFrame(new MenuFrame());
            } else {
                JOptionPane.showMessageDialog(GameFrame.this,
                        "No file name entered.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });


        JMenuItem exitItem = new JMenuItem("Exit Game (without saving)");
        exitItem.addActionListener(e -> {
            Controller.reset();
            Main.setDisplayedFrame(new MenuFrame());
        });

        popupMenu.add(saveItem);
        popupMenu.add(exitItem);
        return popupMenu;
    }
}
