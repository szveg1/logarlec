package pass.model.item;

import pass.model.human.Oktato;
import pass.model.human.TargyVisitor;
import pass.model.labyrinth.Szoba;
import pass.model.human.Hallgato;

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
    Szoba jelenlegiSzoba;
    Tranzisztor par;

    @Override
    public void hasznal() {
        

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
