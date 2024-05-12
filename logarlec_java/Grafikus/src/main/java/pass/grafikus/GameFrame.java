package pass.grafikus;

import pass.controller.Controller;
import pass.model.human.Ember;
import pass.model.labyrinth.Labirintus;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GameFrame extends JFrame {
    public static InventoryPanel inventoryPanel;
    public static JButton nextButton;
    public static void setInvisibleinventoryPanel(boolean b){
        inventoryPanel.setVisible(b);
    }
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

        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESCAPE");
        getRootPane().getActionMap().put("ESCAPE", escapeAction);

        add(new SzobaPanel(), BorderLayout.CENTER);

// TODO: InventoryPanel logikáját még implementálni kell ez alapján

//        JPanel inventoryPanel = new JPanel(new GridLayout(1, 5)); // 1 row, 5 columns
//        Dimension itemSize = new Dimension(100, 100);
//
//        for (int i = 0; i < 5; i++) {
//            JPanel cell = new JPanel();
//            JLabel cellLabel = new JLabel();
//            cell.setPreferredSize(itemSize);
//            cell.setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 3)); // RGB for brown is (139,69,19), thickness is 3
//            cell.setBackground(Color.WHITE);
///////////////////////////////////////////////////////////////////// ITT VALTOZTASD AZ INVETORY TULAJAT
//            if (i < Labirintus.getInstance().getSzobak().get(0).getEmberek().get(0).getItems().size()) {
//                String itemName = Labirintus.getInstance().getSzobak().get(0).getEmberek().get(0).getItems().get(i).getNev();
//
//                cellLabel.setIcon(new ImageIcon(new ImageIcon("Grafikus/src/main/resources/" + itemName + ".png").getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT)));
//
//                cell.setBackground(new Color(134, 216, 131, 255));
//                cell.add(cellLabel);
//            } else {
//                cell.setBackground(Color.LIGHT_GRAY);
//            }
//
//            inventoryPanel.add(cell);
//        }
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
        inventoryPanel = new InventoryPanel();
        southPanel.add(inventoryPanel);
        southPanel.add(Box.createHorizontalGlue());

        for(Ember e : Labirintus.getInstance().getSzobak().get(0).getEmberek()){
            e.addObserver(inventoryPanel);
        }
        add(southPanel, BorderLayout.SOUTH);

        nextButton = new JButton("Next Player");
        nextButton.setPreferredSize(new Dimension(250, 100));
        nextButton.setFont(new Font("Arial", Font.BOLD, 25));
        nextButton.setBackground(new Color(144, 238, 144));
        nextButton.setForeground(Color.BLACK);
        nextButton.setBorder(new LineBorder(Color.BLACK, 3));
        nextButton.addActionListener(e -> Controller.nextPlayer());

        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
        southPanel.add(inventoryPanel);

        southPanel.add(Box.createHorizontalGlue());

        southPanel.add(nextButton);
        southPanel.add(Box.createHorizontalGlue());

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
