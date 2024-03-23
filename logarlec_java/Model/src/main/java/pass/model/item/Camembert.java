package pass.model.item;

import pass.model.human.Oktato;
import pass.model.human.TargyVisitor;
import pass.model.labyrinth.Szoba;

public class Camembert implements Targy {
    private Szoba szoba;
    private boolean poisonous = true;

    @Override
    public void hasznal(Oktato oktato) {
<<<<<<< HEAD
        if (poisonous) {
        szoba.setPoisonous(true, 3);
        poisonous = false;
        }
=======
        szoba.setPoisonous(3);
>>>>>>> 4f746c606a1555357c27c42119c3cda11b4e4f58
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
