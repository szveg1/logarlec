package pass.model.item;

import pass.model.human.Oktato;
import pass.model.labyrinth.Szoba;
import pass.model.human.TargyVisitor;

public class Logarlec implements Targy {
    // Csak szkeletonhoz-------------
    private String nev;
    public Logarlec(String nev) {
        this.nev = nev;
    }

    @Override
    public String toString() {
        return nev + " :Logarlec";
    }

    // -------------------------------

    @Override
    public void accept(TargyVisitor visitor) {
        // Szolni kell a jateknak hogy vege
        visitor.visit(this);
        Labirintus.jatekVege();

    }

    @Override
    public void szobaValtasrolErtesit(Szoba newSzoba) {
        // Nem erdekes hol van
    }

    @Override
    public void tick() {

    }
}
