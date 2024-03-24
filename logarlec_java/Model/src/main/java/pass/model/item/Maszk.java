package pass.model.item;

import pass.model.human.Oktato;
import pass.model.human.TargyVisitor;
import pass.model.labyrinth.Szoba;

public class Maszk implements Targy {
    // Csak szkeletonhoz-------------
    private String nev;
    public Maszk(int initialHealth, String nev) {
        this.nev = nev;
        this.vedIdo = initialHealth;
    }

    @Override
    public String toString() {
        return nev + " :Maszk";
    }

    // -------------------------------
    private int vedIdo = 3;
//    EZ MÉG KELLENI FOG!!!!!!!!
//    public Maszk(int initialHealth) {
//
//    }

    @Override
    public void hasznal() {
        // Nem lehet "hasznalni"
    }

    @Override
    public void accept(TargyVisitor visitor) {
        if (vedIdo > 0) {
            visitor.visit(this);
            vedIdo--;
        } else {
            // Tonkrement nem lehet mit kezdeni vele
            // Talan auto eldobas?
        }
    }

    @Override
    public void szobaValtasrolErtesit(Szoba newSzoba) {
        // Nem erdekes hol van
    }

    public int getVedIdo() {
        return vedIdo;
    }

    @Override
    public void tick() {

    }
}