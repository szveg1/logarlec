package pass.grafikus;

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
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO!
            }
        });
    }
}
