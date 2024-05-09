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
                targy.notifyObservers();
                e.notifyObservers();
            }
        });
    }

    @Override
    public void update() {
        // TODO: remove this
        System.out.println("TargyFoldonLabel update");
        getParent().remove(this);
    }
}
