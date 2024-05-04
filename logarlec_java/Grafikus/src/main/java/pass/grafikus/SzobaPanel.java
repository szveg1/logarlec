package pass.grafikus;

import pass.model.labyrinth.Szoba;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;

public class SzobaPanel extends JPanel {
    private List<JButton> doorButtons;
    public SzobaPanel(Szoba szoba, Dimension size) {
        super();
        //setBackground(Color.gray);
        this.setLayout(null);
        this.setSize(size);
        this.setPreferredSize(size);
        int doorCount = szoba.getAjtok().size();

        doorButtons = new ArrayList<>();

        int doorsOnLeft = 0;
        int doorsOnTop = 0;
        int doorsOnRight = 0;
        int doorsOnBottom = 0;

        int horizontalMargin = getWidth() * 5 / 100;
        int verticalMargin = getHeight() * 5 / 100;

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

        int roomWidth = getWidth() - 2 * horizontalMargin;
        int roomHeight = getHeight() - 2 * verticalMargin;

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
            doorButtons.add(door);
            doorIndex++;
            this.add(door);
        }

        for (int i = 0; i < doorsOnRight; i++) {
            DoorButton door = new DoorButton();
            door.setBounds(getWidth() - horizontalMargin - 10, verticalMargin + (i * 2 + 1) * segmentWidthRight, 10, segmentWidthRight);
            doorButtons.add(door);
            doorIndex++;
            this.add(door);
        }

        for (int i = 0; i < doorsOnBottom; i++) {
            DoorButton door = new DoorButton();
            door.setBounds(horizontalMargin + (i * 2 + 1) * segmentWidthBottom, getHeight() - verticalMargin - 10, segmentWidthBottom, 10);
            doorButtons.add(door);
            doorIndex++;
            this.add(door);
        }
        if (doorCount > 30) {
            String[] doorNumbers = new String[doorCount + 1];
            doorNumbers[0] = "Ajtok";
            for (int i = 1; i <= doorCount; i++) {
                doorNumbers[i] = "Ajto " + (i - 1);
            }

            JComboBox<String> doorList = new JComboBox<>(doorNumbers);
            doorList.setBounds(0, 0, 200, 30); // Adjust size and position as needed
            doorList.setSelectedItem("Ajtok");
            doorList.addItemListener(e -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedItem = (String) e.getItem();
                    if (!selectedItem.equals("Ajtok")) {
                        int doorIndex2 = Integer.parseInt(selectedItem.split(" ")[1]);
                        doorButtons.get(doorIndex2).doClick();
                    }
                }
            });
            this.add(doorList);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int horizontalMargin = getWidth() * 5 / 100;
        int verticalMargin = getHeight() * 5 / 100;

        int roomWidth = getWidth() - 2 * horizontalMargin;
        int roomHeight = getHeight() - 2 * verticalMargin;

        g.setColor(Color.GRAY);
        g.fillRect(horizontalMargin, verticalMargin, roomWidth, roomHeight);

        g.setColor(Color.BLACK);
        g.fillRect(horizontalMargin, verticalMargin, 10, roomHeight);
        g.fillRect(horizontalMargin, verticalMargin, roomWidth, 10);
        g.fillRect(getWidth() - horizontalMargin - 10, verticalMargin, 10, roomHeight);
        g.fillRect(horizontalMargin, getHeight() - verticalMargin - 10, roomWidth, 10);

    }

}
