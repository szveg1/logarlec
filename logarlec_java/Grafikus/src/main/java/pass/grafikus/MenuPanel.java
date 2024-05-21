package pass.grafikus;

import pass.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * A MenuPanel osztály egy panelt valósít meg, amelyben a játék menüje fut.
 */
public class MenuPanel extends JPanel {
    /**
     * Konstruktor, amely létrehoz egy MenuPanel objektumot.
     */
    public MenuPanel() {
        this.setPreferredSize(new Dimension(800, 600));

        this.setLayout(new BorderLayout());

        final JPanel playPanel = makePlayPanel();
        JButton loadButton = new JButton("Load");

        loadButton.addActionListener(e -> {
            loadDialog();
        });
        final JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));

        JLabel titleLabel = new JLabel("Logarléc");
        Font font = new Font("Dialog", Font.BOLD, 50);

        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(font);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loadButton);
        buttonPanel.add(exitButton);
        buttonPanel.add(playPanel);


        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Létrehoz egy panelt a játék indításához.
     *
     * @return A létrehozott panel.
     */
    private static JPanel makePlayPanel() {
        JPanel playPanel = new JPanel();
        playPanel.setLayout(new FlowLayout());
        JButton playButton = new JButton("Play");
        playPanel.add(playButton);

        JLabel disclabel = new JLabel("Játékosok száma:");
        playPanel.add(disclabel);

        SpinnerNumberModel playerNumberModel = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
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

    /**
     * Megnyit egy párbeszédpanelt a játék betöltéséhez.
     */
    private void loadDialog() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Load Game");
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

        int userSelection = fileChooser.showOpenDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            Controller.Load(selectedFile.getAbsolutePath());
            Main.setDisplayedFrame(new GameFrame());
        }
    }

    /**
     * Kirajzolja a panel komponenseit.
     *
     * @param g A grafikus kontextus, amelyre rajzolunk.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        URL url = null;
        try {
            url = new URL("https://i.ytimg.com/vi/5lMdGwmRcr0/maxresdefault.jpg");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        g.drawImage(new ImageIcon(url).getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}