package pass.logarlec.item;

import pass.logarlec.human.TargyVisitor;
import pass.logarlec.labyrinth.Szoba;

public class Pohar implements Targy {
    // HIANYOS!!!!

    @Override
    public void hasznal() {
        // Nem lehet "hasznalni"
    }

    @Override
    public void accept(TargyVisitor visitor) {

    }

    @Override
    public void szobaValtasrolErtesit(Szoba newSzoba) {

    }
}
