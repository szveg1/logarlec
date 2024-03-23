package pass.model.item;

import pass.model.human.Oktato;
import pass.model.labyrinth.Szoba;
import pass.model.human.TargyVisitor;

public class Logarlec implements Targy {

    @Override
    public void hasznal(Oktato oktato) {
        // Nem lehet "hasznalni"
    }

    @Override
    public void accept(TargyVisitor visitor) {
        // Szolni kell a jateknak hogy vege
    }

    @Override
    public void szobaValtasrolErtesit(Szoba newSzoba) {
        // Nem erdekes hol van
    }

    @Override
    public void tick() {

    }
}
