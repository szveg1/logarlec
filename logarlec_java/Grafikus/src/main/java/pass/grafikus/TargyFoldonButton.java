package pass.grafikus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TargyFoldonButton extends TargyButton{
    public TargyFoldonButton() {
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Controller.TargyFelvesz();
            }
        });
    }
}
