package pass.model.item;

import pass.model.human.Oktato;
import pass.model.human.TargyVisitor;
import pass.model.labyrinth.Szoba;

public class Rongy implements Targy {
    // Csak szkeletonhoz-------------
    private String nev;
    public Rongy(String nev){
        this.nev = nev;
    }

    @Override
    public String toString() {
        return nev + " :Rongy";
    }

    // -------------------------------

    Szoba jelenlegiSzoba;

    private int meddigNedves = 3;

    @Override
    public void hasznal(Oktato oktato) {
        jelenlegiSzoba.getEmberek().forEach(e -> e.rongyotElszenved(this));
    }

    @Override
    public void accept(TargyVisitor visitor) {

    }

    @Override
    public void szobaValtasrolErtesit(Szoba newSzoba) {
        jelenlegiSzoba = newSzoba;
    }

    public boolean hasznalhatoE() {
        return meddigNedves > 0;
    }

    public int getMeddigNedves() {
        return meddigNedves;
    }

    @Override
    public void tick() {

    }
}
