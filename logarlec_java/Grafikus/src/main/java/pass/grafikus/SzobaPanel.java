package pass.grafikus;

import pass.controller.Controller;
import pass.model.graphichelper.DrawObserver;
import pass.model.human.Ember;
import pass.model.item.Targy;
import pass.model.labyrinth.Ajto;
import pass.model.labyrinth.Labirintus;
import pass.model.labyrinth.Szoba;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SzobaPanel extends JPanel implements DrawObserver {
    private int MARGO_SZAZALEK = 5;
    private int vizszintesMargo;
    private int fuggolegesMargo;
    private int szobaSzelesseg;
    private int szobaMagassag;
    private List<JButton> ajtoGombok;
    private List<TargyLabel> targyFoldonLabels;
    private List<EmberLabel> emberLabels;
    private JPanel jobbpanel;
    private JPanel balpanel;

    @Override
    public void update() {
        System.out.println("SzobaPanel update");
        Szoba szoba = Controller.getSorosJatekos().getJelenlegiSzoba();
        Dimension size = new Dimension(1280, 720);
        removeAll();
        this.setLayout(null);
        this.setSize(size);
        this.setPreferredSize(size);
        List<Ajto> modelAjtok = szoba.getAjtok();
        int ajtoSzam = modelAjtok.size();

        vizszintesMargo = getWidth() * MARGO_SZAZALEK / 100;
        fuggolegesMargo = getHeight() * MARGO_SZAZALEK / 100;

        szobaSzelesseg = getWidth() - 2 * vizszintesMargo;
        szobaMagassag = getHeight() - 2 * fuggolegesMargo;


        jobbpanel = new JPanel();
        jobbpanel.setBounds(vizszintesMargo + szobaSzelesseg / 2, fuggolegesMargo + 10, szobaSzelesseg / 2 - 10, szobaMagassag - 20);
        jobbpanel.setBackground(new Color(0, 0, 0, 0));
        balpanel = new JPanel();
        balpanel.setBounds(vizszintesMargo + 10, fuggolegesMargo + 10, szobaSzelesseg / 2 - 10, szobaMagassag - 20);
        balpanel.setBackground(new Color(0, 0, 0, 0));

        this.add(jobbpanel);
        this.add(balpanel);

        ajtoGombok = new ArrayList<>();

        targyFoldonLabels = new ArrayList<>();

        emberLabels = new ArrayList<>();

        // Emberek megjelenítése
        for (Ember e : szoba.getEmberek()) {
            //e.addObserver(this);
            JPanel b = new JPanel();
            EmberLabel l = new EmberLabel(e);
            b.add(l);
            b.setBackground(new Color(0, 0, 0, 0));
            jobbpanel.add(b);
        }

        // Tárgyak megjelenítése
        for (Targy t : szoba.getItems()) {
            //t.addObserver(this);
            TargyLabel tl = new TargyFoldonLabel(t);
            targyFoldonLabels.add(tl);
            balpanel.add(tl);
        }

        int[] oldalonHanyAjto = new int[4];
        int[] ajtoSzegmensMeret = new int[4];

        for (int i = 0; i < ajtoSzam; i++) {
            oldalonHanyAjto[i % 4]++;
        }

        for (int i = 0; i < 4; i++) {
            if (oldalonHanyAjto[i] > 0) {
                ajtoSzegmensMeret[i] = (i % 2 == 0 ? szobaMagassag : szobaSzelesseg) / (oldalonHanyAjto[i] * 2 + 1);
            }
        }


        for (Oldal oldal : Oldal.values()) {
            int oldalIndex = oldal.ordinal();
            for (int i = 0; i < oldalonHanyAjto[oldalIndex]; i++) {
                Ajto modelAjto = modelAjtok.get(i * 4 + oldalIndex);
                DoorButton ajtoGomb = new DoorButton(modelAjto);
                int x0, y0, szelesseg, magassag;

                final int yKezdo = fuggolegesMargo + (i * 2 + 1) * ajtoSzegmensMeret[oldalIndex];
                final int xKezdo = vizszintesMargo + (i * 2 + 1) * ajtoSzegmensMeret[oldalIndex];

                switch (oldal) {
                    case BAL:
                        x0 = vizszintesMargo;
                        y0 = yKezdo;
                        szelesseg = 10;
                        magassag = ajtoSzegmensMeret[oldalIndex];
                        break;
                    case FELSO:
                        x0 = xKezdo;
                        y0 = fuggolegesMargo;
                        szelesseg = ajtoSzegmensMeret[oldalIndex];
                        magassag = 10;
                        break;
                    case JOBB:
                        x0 = getWidth() - vizszintesMargo - 10;
                        y0 = yKezdo;
                        szelesseg = 10;
                        magassag = ajtoSzegmensMeret[oldalIndex];
                        break;
                    case ALSO:
                        x0 = xKezdo;
                        y0 = getHeight() - fuggolegesMargo - 10;
                        szelesseg = ajtoSzegmensMeret[oldalIndex];
                        magassag = 10;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + oldal);
                }
                ajtoGomb.setBounds(x0, y0, szelesseg, magassag);
                this.add(ajtoGomb);
                ajtoGombok.add(ajtoGomb);
            }
        }
        revalidate();
        repaint();
    }

    private enum Oldal {
        BAL, FELSO, JOBB, ALSO
    }

    public SzobaPanel() {
        super();
        for (Ember e : Labirintus.getInstance().getSzobak().get(0).getEmberek())
            e.addObserver(this);
        update();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //sima
        g.setColor(Color.GRAY);
        //mérgező
        //Color myColor = new Color(0, 120, 0);
        //g.setColor(myColor);
        //g.setColor(Color.GREEN);
        //ragacsos
        //Color myColor = new Color(255, 255, 205);
        //g.setColor(myColor);
        g.fillRect(vizszintesMargo, fuggolegesMargo, szobaSzelesseg, szobaMagassag);

        g.setColor(Color.BLACK);
        g.fillRect(vizszintesMargo, fuggolegesMargo, 10, szobaMagassag);
        g.fillRect(vizszintesMargo, fuggolegesMargo, szobaSzelesseg, 10);
        g.fillRect(getWidth() - vizszintesMargo - 10, fuggolegesMargo, 10, szobaMagassag);
        g.fillRect(vizszintesMargo, getHeight() - fuggolegesMargo - 10, szobaSzelesseg, 10);

    }



}
