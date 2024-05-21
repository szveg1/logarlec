package pass.grafikus;

import pass.controller.Controller;
import pass.model.human.Ember;
import pass.model.item.Targy;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A TargyFoldonLabel osztály egy címkét valósít meg, amely egy tárgyat jelenít meg a földön.
 */
public class TargyFoldonLabel extends TargyLabel {
    /**
     * Konstruktor, amely létrehoz egy TargyFoldonLabel objektumot.
     *
     * @param targy A tárgy, amit a címke megjelenít.
     */
    public TargyFoldonLabel(Targy targy) {
        super(targy);
        addMouseListener(new MouseAdapter() {
            /**
             * Definiálja, hogy mi történik, amikor a címkére kattintanak.
             *
             * @param mouseEvent Az esemény, amely a kattintást jelzi.
             */
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Ember e = Controller.getSorosJatekos();
                Controller.TargyFelvesz(targy, e);
                e.notifyObservers();
            }
        });
    }

    /**
     * Frissíti a címkét.
     */
    @Override
    public void update() {
        System.out.println("TargyFoldonLabel update");
        revalidate();
        repaint();
    }
}