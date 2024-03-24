package pass.model.item;

import pass.model.CustomLogger;
import pass.model.human.Oktato;
import pass.model.human.TargyVisitor;
import pass.model.labyrinth.Szoba;
/* Az osztály felelős a nedves táblatörlő rongy kezeléséért a játék során. A tárgyat fel
lehet venni szobákban, ahol található és el lehet őket használni védekezésre oktatók
ellen. */
public class Rongy implements Targy {
    // Csak szkeletonhoz-------------
    private String nev;

    /**
     *  A függvény elnevezi az objektumot
     * @param nev - A tárgy neve, Szkeletonhoz
     */
    public Rongy(String nev){
        this.nev = nev;
    }

    /**
     *
     * @return String, Szkeleton kiiratashoz
     */
    @Override
    public String toString() {
        return nev + " :Rongy";
    }

    // -------------------------------

    Szoba jelenlegiSzoba;

    private int meddigNedves = 3;

    /**
     * A függvény minden a szobában tartózkodó emberre meghívja a ronygelszenved függvényt
     * @param oktato - Az oktató, aki ellen használják
     */
    @Override
    public void hasznal(Oktato oktato) {
        jelenlegiSzoba.getEmberek().forEach(e -> e.rongyotElszenved(this));

    }

    /**
     *
     * @param visitor - A visitor, amit fogad
     */
    @Override
    public void accept(TargyVisitor visitor) {
        CustomLogger.info(visitor + "-t " + this + " fogadta.");
        visitor.visit(this);
    }

    /**
     * A függyvény átállítja hogy a rongy
     * tartózkodási helyét a megadott szobára
     * @param newSzoba - Az új szoba, ahova átkerül
     */
    @Override
    public void szobaValtasrolErtesit(Szoba newSzoba) {
        jelenlegiSzoba = newSzoba;
    }

    /**
     * A függvény visszaadja hogy nedve-e a orngy
     * @return Visszaadja hogy a rongy még nedves-e
     */
    public boolean hasznalhatoE() {
        return meddigNedves > 0;
    }

    /**
     * Getter függvény ami visszaadja hogy meddig nedves még a rongy
     * @return Visszaadja hogy meddig nedves a rongy
     */
    public int getMeddigNedves() {
        return meddigNedves;
    }

    /**
     * A függvény csökkenti a hátralévő nedvességi időt
     */
    @Override
    public void tick() {
        meddigNedves--;
    }
}
