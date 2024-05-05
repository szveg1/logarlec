package pass.grafikus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TargyFoldon extends TargyButton{
    public TargyFoldon() {
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Controller.TargyFelvesz();
            }
        });
    }
}
