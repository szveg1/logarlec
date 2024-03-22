package pass.logarlec.item;

import pass.logarlec.human.Oktato;
import pass.logarlec.human.TargyVisitor;
import pass.logarlec.labyrinth.Szoba;

public class Camembert implements Targy {
    private Szoba szoba;

    @Override
    public void hasznal(Oktato oktato) {
        szoba.setPoisonous(true, 3);
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
