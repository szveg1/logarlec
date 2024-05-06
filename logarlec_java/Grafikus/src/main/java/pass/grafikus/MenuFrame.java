package pass.grafikus;

import javax.swing.*;

public class MenuFrame extends JFrame {
    public MenuFrame() {
        super("Logarlec");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setResizable(true);
        setLocationRelativeTo(null);
        add(new MenuPanel());
        pack();
    }
}
