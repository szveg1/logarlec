package pass.model.item;

import pass.model.human.Oktato;
import pass.model.human.TargyVisitor;
import pass.model.labyrinth.Szoba;

public class Camembert implements Targy {
    private Szoba szoba;
    private boolean poisonous = true;

    @Override
    public void hasznal(Oktato oktato) {
        szoba.setPoisonous(3);
    }

    @Override
    public void accept(TargyVisitor visitor) {
        // Nincs passziv hatasa
    }

    @Override
    public void szobaValtasrolErtesit(Szoba newSzoba) {
        this.szoba = newSzoba;
    }


    @Override
    public void tick() {

    }
}
