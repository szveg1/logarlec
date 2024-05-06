package pass.model.item;

import pass.model.CustomLogger;
import pass.model.TargyVisitor;
import pass.model.TargyVisitorGrafikus;
import pass.model.graphichelper.DrawObserver;
import pass.model.human.Oktato;

/* Az osztály felelős a nedves táblatörlő rongy kezeléséért a játék során. A tárgyat fel
lehet venni szobákban, ahol található és el lehet őket használni védekezésre oktatók
ellen. */
public class Rongy extends Targy {
    private int meddigNedves = 3;

    /**
     * Konstruktor
     *
     * @param nev - A tárgy neve, Szkeletonhoz
     */
    public Rongy(String nev) {
        this.nev = nev;
    }

    /**
     * TODO!!!
     *
     * @param visitor
     */
    @Override
    public void accept(TargyVisitorGrafikus visitor) {
        visitor.visit(this);
    }

    /**
     * @param visitor - A visitor, amit fogad
     */
    @Override
    public void accept(TargyVisitor visitor) {
        CustomLogger.info(visitor + "-t " + this + " fogadta.");
        visitor.visit(this);
    }


    /**
     * A függvény minden a szobában tartózkodó emberre meghívja a ronygelszenved függvényt
     *
     * @param oktato - Az oktató, aki ellen használják
     */
    @Override
    public void hasznal(Oktato oktato) {
        jelenlegiSzoba.getEmberek().forEach(e -> e.rongyotElszenved(this));
    }

    /**
     * TODO!!!
     */
    @Override
    public void notifyObservers() {
        for (DrawObserver observer : observers) {
            observer.update(this);
        }
    }

    /**
     * A függvény csökkenti a hátralévő nedvességi időt
     */
    @Override
    public void tick() {
        meddigNedves--;
    }

    /**
     * A függvény visszaadja hogy nedve-e a orngy
     *
     * @return Visszaadja hogy a rongy még nedves-e
     */
    public boolean hasznalhatoE() {
        return meddigNedves > 0;
    }

    /**
     * Getter függvény ami visszaadja hogy meddig nedves még a rongy
     *
     * @return Visszaadja hogy meddig nedves a rongy
     */
    public int getMeddigNedves() {
        return meddigNedves;
    }

    /**
     * A függvény kiírja az objektum nevét
     *
     * @return String, Szkeleton kiiratashoz
     */
    @Override
    public String toString() {
        return nev + " :Rongy";
    }
}
