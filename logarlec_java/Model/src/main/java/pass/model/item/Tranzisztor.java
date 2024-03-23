package pass.model.item;

import pass.model.human.Oktato;
import pass.model.human.TargyVisitor;
import pass.model.labyrinth.Szoba;

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
