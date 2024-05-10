package pass.grafikus;

import pass.controller.Controller;
import pass.model.human.Ember;
import pass.model.item.Targy;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TargyFoldonLabel extends TargyLabel {
    public TargyFoldonLabel(Targy targy) {
        super(targy);
        addMouseListener(new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param mouseEvent
             */
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Ember e = Controller.getSorosJatekos();
                Controller.TargyFelvesz(targy, e);
                //targy.notifyObservers();
                e.notifyObservers();
            }
        });
    }

    public TargyFoldonLabel(Targy targy, InventoryPanel inventoryPanel) {
        super(targy);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Ember e = Controller.getSorosJatekos();
                Controller.TargyFelvesz(targy, e);
                inventoryPanel.update(); // update the InventoryPanel
                System.out.println("Tárgy felvéve: " + targy);
            }
        });
    }
    @Override
    public void update() {
        // TODO: remove this
        System.out.println("TargyFoldonLabel update");
        //this.setIcon(null);
        revalidate();
        repaint();
    }
}
