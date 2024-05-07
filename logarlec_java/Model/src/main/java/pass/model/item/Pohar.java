package pass.model.item;

import pass.model.CustomLogger;
import pass.model.TargyVisitor;
import pass.model.TargyVisitorGrafikus;

/**
 * A Pohár osztály felelős azért, hogy tárolja és kezelje
 * a söröspohár tulajdonságait és funkcióit a labirintusban,
 * mint például használható-e a tárgy védelem nyújtásra az
 * oktatókkal szemben.
 */
public class Pohar extends Targy {
    private int vedIdo = 3;

    /**
     * A függvény elnevezi az objektumot
     *
     * @param nev - Az objektum neve
     */
    public Pohar(String nev) {
        this.nev = nev;
    }

    /**
     * TODO!!!
     *
     * @param visitor
     */
    public void accept(TargyVisitorGrafikus visitor) {
        visitor.visit(this);
    }

    /**
     * A függvény fogadja a visitet
     *
     * @param visitor - A visitor, amit fogad
     */
    @Override
    public void accept(TargyVisitor visitor) {
        CustomLogger.info(visitor + "-t " + this + " fogadta.");
        visitor.visit(this);
    }

    /**
     * TODO!!!
     */
    @Override
    public void hasznal() {
        tulajdonos.targyatEldob(tulajdonos.getItems().get(0));
    }


    /**
     * TODO!!!
     */
    @Override
    public void tick() {
        vedIdo--;
    }

    /**
     * @return Visszaadja hogy a pohár használható-e
     */
    public boolean hasznalhatoE() {
        return vedIdo > 0;
    }

    /**
     * A függvény kiírja az objektum nevét
     *
     * @return String, Szkeleton kiiratashoz
     */
    @Override
    public String toString() {
        return nev + " :Pohar";
    }
}
