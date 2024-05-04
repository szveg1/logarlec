package pass.grafikus;

import pass.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    public MenuPanel(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final JPanel playPanel = makePlayPanel();
        final JPanel loadPanel = makeLoadPanel();

        add(playPanel);
        add(loadPanel);
    }

    private JPanel makeLoadPanel() {
        JPanel loadPanel = new JPanel();
        loadPanel.setLayout(new FlowLayout());
        JButton loadButton = new JButton("Load");
        loadPanel.add(loadButton);

        loadButton.addActionListener(e -> {
            loadDialog();
            Main.setDisplayedFrame(new GameFrame());
        });

        return loadPanel;
    }

    private void loadDialog() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Load Game");

        int userSelection = fileChooser.showOpenDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            Controller.Load(fileChooser.getSelectedFile().getAbsolutePath());
            Main.setDisplayedFrame(new GameFrame());
        }
    }

    private static JPanel makePlayPanel() {
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
        return playPanel;
    }
}
