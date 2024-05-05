package pass.grafikus;

import pass.model.human.Ember;
import pass.model.item.Targy;
import pass.model.labyrinth.Szoba;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SzobaPanel extends JPanel {
    private List<JButton> doorButtons;
    private List<JButton> itemButtons;
    private List<EmberLabel> emberLabels;
    private JPanel jobbpanel;
    private JPanel balpanel;
    public SzobaPanel(Szoba szoba, Dimension size) {
        super();
        this.setLayout(null);
        this.setSize(size);
        this.setPreferredSize(size);
        int doorCount = szoba.getAjtok().size();

        int horizontalMargin = getWidth() * 5 / 100;
        int verticalMargin = getHeight() * 5 / 100;

        int roomWidth = getWidth() - 2 * horizontalMargin;
        int roomHeight = getHeight() - 2 * verticalMargin;


        jobbpanel = new JPanel();
        jobbpanel.setBounds(horizontalMargin + roomWidth/2, verticalMargin + 10, roomWidth/2 - 10, roomHeight-20);
        jobbpanel.setBackground(new Color(0,0,0,0));
        balpanel = new JPanel();
        balpanel.setBounds(horizontalMargin + 10, verticalMargin + 10, roomWidth/2 - 10, roomHeight-20);
        balpanel.setBackground(new Color(0,0,0,0));

        this.add(jobbpanel);
        this.add(balpanel);

        doorButtons = new ArrayList<>();

        itemButtons = new ArrayList<>();

        emberLabels = new ArrayList<>();

        for (Ember e : szoba.getEmberek()) {

            JPanel b = new JPanel();
            EmberLabel l = new EmberLabel(e);
            b.add(l);
            jobbpanel.add(b);
        }



        int doorsOnLeft = 0;
        int doorsOnTop = 0;
        int doorsOnRight = 0;
        int doorsOnBottom = 0;


        for (int i = 0; i < doorCount; i++) {
            if (i % 4 == 0) {
                doorsOnLeft++;
            } else if (i % 4 == 1) {
                doorsOnTop++;
            } else if (i % 4 == 2) {
                doorsOnRight++;
            } else {
                doorsOnBottom++;
            }
        }

        int segmentWidthLeft = 0;
        int segmentWidthTop = 0;
        int segmentWidthRight = 0;
        int segmentWidthBottom = 0;

        if (doorsOnLeft > 0)
            segmentWidthLeft = roomHeight / (doorsOnLeft * 2 + 1);

        if (doorsOnTop > 0)
            segmentWidthTop = roomWidth / (doorsOnTop * 2 + 1);

        if (doorsOnRight > 0)
            segmentWidthRight = roomHeight / (doorsOnRight * 2 + 1);

        if (doorsOnBottom > 0)
            segmentWidthBottom = roomWidth / (doorsOnBottom * 2 + 1);

        int doorIndex = 0;

        // Adjust the door position calculations to account for gaps
        for (int i = 0; i < doorsOnLeft; i++) {
            DoorButton door = new DoorButton();
            door.setBounds(horizontalMargin, verticalMargin + (i * 2 + 1) * segmentWidthLeft, 10, segmentWidthLeft);
            doorIndex++;
            this.add(door);
        }

        for (int i = 0; i < doorsOnTop; i++) {
            DoorButton door = new DoorButton();
            door.setBounds(horizontalMargin + (i * 2 + 1) * segmentWidthTop, verticalMargin, segmentWidthTop, 10);
            doorIndex++;
            this.add(door);
        }

        for (int i = 0; i < doorsOnRight; i++) {
            DoorButton door = new DoorButton();
            door.setBounds(getWidth() - horizontalMargin - 10, verticalMargin + (i * 2 + 1) * segmentWidthRight, 10, segmentWidthRight);
            doorIndex++;
            this.add(door);
        }

        for (int i = 0; i < doorsOnBottom; i++) {
            DoorButton door = new DoorButton();
            door.setBounds(horizontalMargin + (i * 2 + 1) * segmentWidthBottom, getHeight() - verticalMargin - 10, segmentWidthBottom, 10);
            doorIndex++;
            this.add(door);
        }




    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int horizontalMargin = getWidth() * 5 / 100;
        int verticalMargin = getHeight() * 5 / 100;

        int roomWidth = getWidth() - 2 * horizontalMargin;
        int roomHeight = getHeight() - 2 * verticalMargin;

        //sima
        g.setColor(Color.GRAY);
        //mérgező
        //Color myColor = new Color(0, 120, 0);
        //g.setColor(myColor);
        //g.setColor(Color.GREEN);
        //ragacsos
        //Color myColor = new Color(255, 255, 205);
        //g.setColor(myColor);
        g.fillRect(horizontalMargin, verticalMargin, roomWidth, roomHeight);

        g.setColor(Color.BLACK);
        g.fillRect(horizontalMargin, verticalMargin, 10, roomHeight);
        g.fillRect(horizontalMargin, verticalMargin, roomWidth, 10);
        g.fillRect(getWidth() - horizontalMargin - 10, verticalMargin, 10, roomHeight);
        g.fillRect(horizontalMargin, getHeight() - verticalMargin - 10, roomWidth, 10);

    }

}
