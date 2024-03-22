package pass.logarlec.item;

import pass.logarlec.human.Oktato;
import pass.logarlec.labyrinth.Szoba;
import pass.logarlec.human.TargyVisitor;

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
