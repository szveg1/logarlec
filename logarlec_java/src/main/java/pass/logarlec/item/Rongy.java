package pass.logarlec.item;

import pass.logarlec.human.Oktato;
import pass.logarlec.human.TargyVisitor;
import pass.logarlec.labyrinth.Szoba;

public class Rongy implements Targy {

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
