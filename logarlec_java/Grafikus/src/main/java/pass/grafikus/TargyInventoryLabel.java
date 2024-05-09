package pass.grafikus;

import pass.controller.Controller;
import pass.model.item.Targy;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TargyInventoryLabel extends TargyLabel {

    public TargyInventoryLabel(Targy targy) {
        super(targy);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    Controller.Hasznal(targy, Controller.getSorosJatekos());
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    Controller.TargyEldob(targy, Controller.getSorosJatekos());
                }
            }
        });
    }

    @Override
    public void update() {

    }


}
