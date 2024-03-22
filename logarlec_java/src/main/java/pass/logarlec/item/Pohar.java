package pass.logarlec.item;

import pass.logarlec.human.Oktato;
import pass.logarlec.human.TargyVisitor;
import pass.logarlec.labyrinth.Szoba;

public class Pohar implements Targy {
    private int vedIdo = 3;

    @Override
    public void hasznal(Oktato oktato) {
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
