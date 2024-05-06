package pass.grafikus;

import pass.controller.Controller;
import pass.model.labyrinth.Labirintus;

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


        JPanel inventoryPanel = new JPanel(new GridLayout(1, 5)); // 1 row, 5 columns
        Dimension itemSize = new Dimension(100, 100);

        for (int i = 0; i < 5; i++) {
            JPanel cell = new JPanel();
            JLabel cellLabel = new JLabel();
            cell.setPreferredSize(itemSize);
            cell.setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 3)); // RGB for brown is (139,69,19), thickness is 3
            cell.setBackground(Color.WHITE);
/////////////////////////////////////////////////////////////////// ITT VALTOZTASD AZ INVETORY TULAJAT
            if (i < Labirintus.getInstance().getSzobak().get(0).getEmberek().get(0).getItems().size()) {
                String itemName = Labirintus.getInstance().getSzobak().get(0).getEmberek().get(0).getItems().get(i).getNev();

                cellLabel.setIcon(new ImageIcon(new ImageIcon("Grafikus/src/main/resources/" + itemName + ".png").getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT)));

                cell.setBackground(new Color(134, 216, 131, 255));
                cell.add(cellLabel);
            } else {
                cell.setBackground(Color.LIGHT_GRAY);
            }

            inventoryPanel.add(cell);
        }

        add(inventoryPanel, BorderLayout.SOUTH);


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
