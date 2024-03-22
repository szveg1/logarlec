package pass.logarlec.item;

import pass.logarlec.human.Oktato;
import pass.logarlec.human.TargyVisitor;
import pass.logarlec.labyrinth.Szoba;

public class Maszk implements Targy {
    private int vedIdo = 3;

    public Maszk(int initialHealth) {
        this.vedIdo = initialHealth;
    }

    @Override
    public void hasznal(Oktato oktato) {
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