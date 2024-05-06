package pass.grafikus;

import pass.model.CustomLogger;

import javax.swing.*;

public class Main {

    private static JFrame frame;

    public static void main(String[] args) {
        CustomLogger.suppress();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        frame = new MenuFrame();
        frame.setVisible(true);
    }

    public static void setDisplayedFrame(JFrame f) {
        frame.dispose();
        frame = f;
        frame.setVisible(true);
    }
}