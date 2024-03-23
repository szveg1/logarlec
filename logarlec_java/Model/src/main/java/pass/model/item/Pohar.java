package pass.model.item;

import pass.model.human.Oktato;
import pass.model.human.TargyVisitor;
import pass.model.labyrinth.Szoba;

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
