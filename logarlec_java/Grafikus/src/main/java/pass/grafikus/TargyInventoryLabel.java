package pass.grafikus;

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
                    //Controller.TargyHasznal();
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    //Controller.TargyEldob();
                }
            }
        });
    }

    @Override
    public void update() {

    }


}
