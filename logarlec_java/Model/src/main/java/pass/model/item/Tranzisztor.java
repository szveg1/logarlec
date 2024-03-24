package pass.model.item;

import pass.model.human.Oktato;
import pass.model.human.TargyVisitor;
import pass.model.labyrinth.Szoba;
import pass.model.human.Hallgato;
import pass.model.human.Ember;

public class Tranzisztor implements Targy {
    // Csak szkeletonhoz-------------
    private String nev;
    public Tranzisztor(String nev) {
        this.nev = nev;
    }

    @Override
    public String toString() {
        return nev + " :Tranzisztor";
    }

    // -------------------------------
    private Szoba jelenlegiSzoba;
    private Tranzisztor par = null;

    private Ember tulaj;

    @Override
    public void hasznal() {
        

    }
    @Override
    public void setPar(Tranzisztor t) {
        this.par = t;
        t.setPar2(this);
    }

    public void setPar2(Tranzisztor t) {
        this.par = t;
    }

    @Override
    public void accept(TargyVisitor visitor) {
        // Nincs passziv effektje
    }

    @Override
    public void szobaValtasrolErtesit(Szoba newSzoba) {
        jelenlegiSzoba = newSzoba;
    }

    public Szoba getSzoba() {
        return jelenlegiSzoba;
    }

    @Override
    public void tick() {

    }
}
