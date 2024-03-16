package pass.logarlec.item;

import pass.logarlec.human.TargyVisitor;
import pass.logarlec.labyrinth.Szoba;

public class Tranzisztor implements Targy {
    // HIANYOS!!!!

    @Override
    public void hasznal() {

    }

    @Override
    public void accept(TargyVisitor visitor) {
        // Nincs passziv effektje
    }

    @Override
    public void szobaValtasrolErtesit(Szoba newSzoba) {

    }
}
