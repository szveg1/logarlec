package pass.model.item;

import pass.model.human.Oktato;
import pass.model.human.TargyVisitor;
import pass.model.labyrinth.Szoba;

public class Pohar implements Targy {
    // Csak szkeletonhoz-------------
    private String nev;
    public Pohar(String nev) {
        this.nev = nev;
    }

    @Override
    public String toString() {
        return nev + " :Pohar";
    }

    // -------------------------------
    private int vedIdo = 3;

    @Override
    public void hasznal() {
        // Nem lehet használni.
    }

    @Override
    public void accept(TargyVisitor visitor) {

    }

    @Override
    public void szobaValtasrolErtesit(Szoba newSzoba) {

    }

    public boolean hasznalhatoE() {
        return vedIdo > 0;
    }

    @Override
    public void tick() {

    }
}
