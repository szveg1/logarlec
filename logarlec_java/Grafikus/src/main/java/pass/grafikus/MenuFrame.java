package pass.grafikus;

import javax.swing.*;

/**
 * A MenuFrame osztály egy keretet valósít meg, amelyben a játék menüje fut.
 */
public class MenuFrame extends JFrame {
    /**
     * Konstruktor, amely létrehoz egy MenuFrame objektumot.
     */
    public MenuFrame() {
        super("Logarlec"); // Beállítja a keret címét
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Beállítja a keret bezárásának műveletét
        setSize(1280, 720); // Beállítja a keret méretét
        setResizable(true); // Lehetővé teszi a keret átméretezését
        setLocationRelativeTo(null); // Középre helyezi a keretet
        add(new MenuPanel()); // Hozzáadja a menü panelt a kerethez
        pack(); // Optimalizálja a keret méretét a benne lévő komponensek alapján
    }
}