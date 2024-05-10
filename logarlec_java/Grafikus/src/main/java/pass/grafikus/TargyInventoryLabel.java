package pass.grafikus;

import pass.controller.Controller;
import pass.model.human.Ember;
import pass.model.item.Targy;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TargyInventoryLabel extends TargyLabel {

    public TargyInventoryLabel(Targy targy) {
        super(targy);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Ember em = Controller.getSorosJatekos();
                if (e.getButton() == MouseEvent.BUTTON1) {
                    Controller.Hasznal(targy, em);
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    Controller.TargyEldob(targy, em);
                    targy.notifyObservers();
                    em.notifyObservers();

                }
            }
        });
    }

    @Override
    public void update() {
        targy = null;
        this.setIcon(null);
        revalidate();
        repaint();
    }


}
