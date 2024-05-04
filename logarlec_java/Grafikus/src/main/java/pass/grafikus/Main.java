package pass.grafikus;

import javax.swing.*;

import pass.model.CustomLogger;
import pass.model.labyrinth.Labirintus;
import pass.model.labyrinth.Szoba;

import java.awt.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static JFrame frame;

    public static void main(String[] args) {
        CustomLogger.suppress();
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