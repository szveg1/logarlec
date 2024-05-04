package pass.grafikus;

import pass.controller.Controller;
import pass.controller.Fuggvenyek;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    public MenuPanel(){
        setLayout(new BorderLayout());

        JPanel playPanel = new JPanel();
        playPanel.setLayout(new FlowLayout());
        JButton playButton = new JButton("Play");
        playPanel.add(playButton);

        SpinnerNumberModel playerNumberModel = new SpinnerNumberModel(1,1,Integer.MAX_VALUE,1);
        JSpinner playerNumberSpinner = new JSpinner(playerNumberModel);
        playerNumberSpinner.setPreferredSize(new Dimension(50, 30));
        playPanel.add(playerNumberSpinner);

        playButton.addActionListener(e -> {
            int playerNumber = (int) playerNumberSpinner.getValue();
            Controller.Play(playerNumber);
            Main.setDisplayedFrame(new GameFrame());
        });

        add(playPanel, BorderLayout.CENTER);
    }
}
