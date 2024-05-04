package pass.grafikus;

import pass.model.labyrinth.Szoba;

import javax.swing.*;
import java.awt.*;

public class SzobaPanel extends JPanel {
    public SzobaPanel(Szoba szoba, Dimension size) {
        super();
        this.setLayout(null);
        this.setSize(size);
        this.setPreferredSize(size);
        int doorCount = szoba.getAjtok().size();

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
            JButton door = new JButton("Door " + doorIndex);
            door.setBounds(horizontalMargin, verticalMargin + (i * 2 + 1) * segmentWidthLeft, 10, segmentWidthLeft);
            doorIndex++;
            this.add(door);
        }

        for (int i = 0; i < doorsOnTop; i++) {
            JButton door = new JButton("Door " + doorIndex);
            door.setBounds(horizontalMargin + (i * 2 + 1) * segmentWidthTop, verticalMargin, segmentWidthTop, 10);
            doorIndex++;
            this.add(door);
        }

        for (int i = 0; i < doorsOnRight; i++) {
            JButton door = new JButton("Door " + doorIndex);
            door.setBounds(getWidth() - horizontalMargin - 10, verticalMargin + (i * 2 + 1) * segmentWidthRight, 10, segmentWidthRight);
            doorIndex++;
            this.add(door);
        }

        for (int i = 0; i < doorsOnBottom; i++) {
            JButton door = new JButton("Door " + doorIndex);
            door.setBounds(horizontalMargin + (i * 2 + 1) * segmentWidthBottom, getHeight() - verticalMargin - 10, segmentWidthBottom, 10);
            doorIndex++;
            this.add(door);
        }

        // Add walls
        for (int i = 0; i <= doorsOnLeft; i++) {
            JPanel gap = new JPanel();
            gap.setBackground(Color.RED); // Set the color of the gap
            gap.setBounds(horizontalMargin, verticalMargin + i * 2 * segmentWidthLeft, 10, segmentWidthLeft);
            this.add(gap);
        }

        for (int i = 0; i <= doorsOnTop; i++) {
            JPanel gap = new JPanel();
            gap.setBackground(Color.RED); // Set the color of the gap
            gap.setBounds(horizontalMargin + i * 2 * segmentWidthTop, verticalMargin, segmentWidthTop, 10);
            this.add(gap);
        }

        for (int i = 0; i <= doorsOnRight; i++) {
            JPanel gap = new JPanel();
            gap.setBackground(Color.RED); // Set the color of the gap
            gap.setBounds(getWidth() - horizontalMargin - 10, verticalMargin + i * 2 * segmentWidthRight, 10, segmentWidthRight);
            this.add(gap);
        }

        for (int i = 0; i <= doorsOnBottom; i++) {
            JPanel gap = new JPanel();
            gap.setBackground(Color.RED); // Set the color of the gap
            gap.setBounds(horizontalMargin + i * 2 * segmentWidthBottom, getHeight() - verticalMargin - 10, segmentWidthBottom, 10);
            this.add(gap);
        }

        if(doorsOnLeft == 0){
            JPanel gap = new JPanel();
            gap.setBackground(Color.RED); // Set the color of the gap
            gap.setBounds(horizontalMargin, verticalMargin, 10, roomHeight);
            this.add(gap);
        }
        if(doorsOnTop == 0){
            JPanel gap = new JPanel();
            gap.setBackground(Color.RED); // Set the color of the gap
            gap.setBounds(horizontalMargin, verticalMargin, roomWidth, 10);
            this.add(gap);
        }
        if(doorsOnRight == 0){
            JPanel gap = new JPanel();
            gap.setBackground(Color.RED); // Set the color of the gap
            gap.setBounds(getWidth() - horizontalMargin - 10, verticalMargin, 10, roomHeight);
            this.add(gap);
        }
        if(doorsOnBottom == 0){
            JPanel gap = new JPanel();
            gap.setBackground(Color.RED); // Set the color of the gap
            gap.setBounds(horizontalMargin, getHeight() - verticalMargin - 10, roomWidth, 10);
            this.add(gap);
        }


        System.out.println(doorCount);

    }

}
