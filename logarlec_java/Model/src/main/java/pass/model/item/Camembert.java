package pass.model.item;

import pass.model.CustomLogger;
import pass.model.human.TargyVisitor;
import pass.model.labyrinth.Szoba;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Camembert implements Targy {
    // Csak szkeletonhoz-------------
    private String nev;
    public Camembert(String nev) {
        this.nev = nev;
    }

    @Override
    public String toString() {
        return nev + " :Camembert";
    }

    // -------------------------------
    private Szoba szoba;
    private boolean poisonous = true;


    public void hasznal() {
        if (poisonous) {
        szoba.setPoisonous(3);
        poisonous = false;
        }
    }

    @Override
    public void accept(TargyVisitor visitor) {
        // Nincs passziv hatasa
    }

    @Override
    public void szobaValtasrolErtesit(Szoba newSzoba) {
        this.szoba = newSzoba;
        CustomLogger.info(this + " a " + szoba + "-ba került");
    }


    @Override
    public void tick() {

    }
}
