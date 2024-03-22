package pass.logarlec.item;

import pass.logarlec.human.Oktato;
import pass.logarlec.human.TargyVisitor;
import pass.logarlec.labyrinth.Szoba;

public class Tranzisztor implements Targy {
    Szoba jelenlegiSzoba;

    @Override
    public void hasznal(Oktato oktato) {

    }

    @Override
    public void accept(TargyVisitor visitor) {
        // Nincs passziv effektje
    }

    @Override
    public void szobaValtasrolErtesit(Szoba newSzoba) {
        jelenlegiSzoba = newSzoba;
    }

    @Override
    public void tick() {

    }
}
