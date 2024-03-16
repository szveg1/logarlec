package pass.logarlec.item;

import pass.logarlec.human.TargyVisitor;
import pass.logarlec.labyrinth.Szoba;

public class Rongy implements Targy {

    // HIANYOS!!!!

    private int meddigNedves = 3;

    @Override
    public void hasznal() {

    }

    @Override
    public void accept(TargyVisitor visitor) {

    }

    @Override
    public void szobaValtasrolErtesit(Szoba newSzoba) {

    }

    public boolean hasznalhatoE() {
        return meddigNedves > 0;
    }
}
