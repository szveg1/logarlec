package pass.grafikus;

import pass.controller.Controller;
import pass.model.human.Ember;
import pass.model.item.Targy;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A TargyInventoryLabel osztály egy címkét valósít meg, amely egy tárgyat jelenít meg az eszköztárban.
 */
public class TargyInventoryLabel extends TargyLabel {

    /**
     * Konstruktor, amely létrehoz egy TargyInventoryLabel objektumot.
     *
     * @param targy A tárgy, amit a címke megjelenít.
     */
    public TargyInventoryLabel(Targy targy) {
        super(targy);
        addMouseListener(new MouseAdapter() {
            /**
             * Definiálja, hogy mi történik, amikor a címkére kattintanak.
             * Bal kattintás esetén a tárgyat használja a soros játékos.
             * Jobb kattintás esetén a tárgyat eldobja a soros játékos.
             *
             * @param e Az esemény, amely a kattintást jelzi.
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                Ember em = Controller.getSorosJatekos();
                if (e.getButton() == MouseEvent.BUTTON1) {
                    Controller.Hasznal(targy, em);
                    em.notifyObservers();
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    Controller.TargyEldob(targy, em);
                    em.notifyObservers();
                }
            }
        });
    }


    /**
     * A függvény nem csinál semmit
     */
    @Override
    public void update() {
    }


}
