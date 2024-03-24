package pass.model.item;

import pass.model.human.TargyVisitor;
import pass.model.labyrinth.Szoba;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Camembert implements Targy {
    private static Logger camembertLogger = Logger.getLogger(Camembert.class.getSimpleName());
    static{
        camembertLogger.setUseParentHandlers(false);
        camembertLogger.addHandler(pass.model.Main.handler);
    }
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

    @Override
    public void hasznal(Oktato oktato) {
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
        camembertLogger.log(Level.INFO,this + " a " + szoba + "-ba került", this);
    }


    @Override
    public void tick() {

    }
}
