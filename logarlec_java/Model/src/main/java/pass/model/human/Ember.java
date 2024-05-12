package pass.model.human;

import pass.model.CustomLogger;
import pass.model.Idozitett;
import pass.model.TargyVisitor;
import pass.model.graphichelper.DrawObservable;
import pass.model.graphichelper.DrawObserver;
import pass.model.item.Legfrissito;
import pass.model.item.Maszk;
import pass.model.item.Rongy;
import pass.model.item.Targy;
import pass.model.labyrinth.Ajto;
import pass.model.labyrinth.Szoba;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Az Ember osztály azonosítja a karaktert, valamint tárolja az
 * általuk birtokolt tárgyakat. Képesek tárgyak felvételére, eldobására és használatára.
 */
public abstract class Ember extends DrawObservable implements TargyVisitor, Idozitett {
    protected final List<Targy> inventory = new ArrayList<>();
    protected String nev;
    protected Szoba jelenlegiSzoba = null;
    protected boolean lepett = false;
    protected int ajult = 0;
    private boolean gazEllenVedett = false;

    /**
     * Konstruktor
     *
     * @param nev - az objektum neve
     */
    public Ember(String nev) {
        this.nev = nev;
    }

    public void accept(EmberVisitor visitor) {
    }

    public void notifyObservers() {
        List<DrawObserver> copyObservers = new ArrayList<>(observers);
        for(DrawObserver observer : copyObservers) {
            observer.update();
        }
    }

    public boolean getLepett() {
        return true;
    }

    public void setLepett(boolean b) {
        this.lepett = b;
    }

    public void targyatFelvesz(Targy targy) {
        if (!jelenlegiSzoba.getItems().contains(targy))
            return;
        if (ajult > 0) {
            CustomLogger.log(Level.WARNING, this + " ájult, nem tud felvenni targyat");
            return;
        }
        if (inventoryTeleE()) {
            CustomLogger.log(Level.WARNING, this + "-nek tele az inventoryja, nem tud felvenni targyat");
            return;
        }
        if (jelenlegiSzoba.ragacsosE()) {
            CustomLogger.log(Level.WARNING, this + " ragacsos szobaban van, nem tud felvenni targyat");
            return;
        }
        CustomLogger.info(this + " felvette a " + targy + "-t");

        if (jelenlegiSzoba != null) {
            jelenlegiSzoba.removeItem(targy);
        }
        inventory.add(targy);
        targy.emberValtasrolErtesit(this);
    }


    /**
     * A függvény a megadott tárgyat eldobja és kiszedi
     * az ember inventory-jából, miközben eltávolítja
     * a tárgyat a szobából
     *
     * @param targy - az eldobandó tárgy
     */
    public void targyatEldob(Targy targy) {
        if (!inventory.contains(targy)) {
            return;
        }
        if (ajult > 0) {
            CustomLogger.log(Level.WARNING, this + " ájult, nem tud eldobni targyat");
            return;
        }

        inventory.remove(targy);
        CustomLogger.info(this + " eldobta a " + targy + "-t");
        targy.emberValtasrolErtesit(null);
        jelenlegiSzoba.addItem(targy);
    }

    /**
     * A függvény meglátogatja a medaott maszkot
     * és a gáz elleni védelmét beállítja az embernek
     *
     * @param maszk - a maszk amit meglátogat
     */
    @Override
    public void visit(Maszk maszk) {
        CustomLogger.info(this + " meglátogatta a " + maszk + "-ot");
        CustomLogger.info("védett lett " + maszk + "-tól: " + (maszk.getVedIdo() > 0));
        gazEllenVedett = maszk.getVedIdo() > 0;
        if (jelenlegiSzoba.mergezoE() && gazEllenVedett ) maszk.csokkentVedIdo();
    }

    public void ajulas() {
        List<Targy> copyOfInventory= new ArrayList<>(inventory);
        for (Targy t : copyOfInventory) {
            t.accept(this);
        }
        if (gazEllenVedett) return;
        List<Targy> copyOfInventory2 = new ArrayList<>(inventory);
        for (Targy targy : copyOfInventory2) {
            targyatEldob(targy);
        }
        ajult = 3;
        CustomLogger.info(this + " elájult");
    }

    /**
     * A függvény az embert átlépteti a megadott szobába,
     * amíg az ember nincs elájulva és
     * az új szobához hozzáadja az embert
     *
     * @param ujSzoba - ebbe a szobába lép át az ember
     */
    public boolean masikSzobabaLep(Szoba ujSzoba) {
        if (ajult > 0) {
            CustomLogger.log(Level.WARNING, this + " ájult, nem tud szobát váltani");
            return false;
        }
        if (!ujSzoba.emberBetesz(this)) return false;

        CustomLogger.info(this + " belépett a " + ujSzoba + "-ba");
        for (Targy targy : inventory) {
            targy.szobaValtasrolErtesit(ujSzoba);
        }
        setLepett(true);
        return true;
    }

    public void kilepSzobajabol() {
        if (jelenlegiSzoba == null) return;
        CustomLogger.info(this + " elhagyta a " + jelenlegiSzoba + "-t");
        jelenlegiSzoba.emberKivesz(this);
    }

    /**
     * A függvény az ember által kiválasztott
     * tárgy ahsználatát hívja meg
     *
     * @param targy - a használandó tárgy
     */
    public void targyatHasznal(Targy targy) {
        if (!inventory.contains(targy))
            return;
        CustomLogger.info(this + " használta a " + targy + "-t");
        targy.hasznal();
    }

    public void setSzoba(Szoba sz) {
        jelenlegiSzoba = sz;
    }

    public Szoba getJelenlegiSzoba() {
        return jelenlegiSzoba;
    }

    public List<Targy> getItems() {
        return inventory;
    }

    public boolean getAjult() {
        return ajult > 0;
    }

    public abstract boolean inventoryTeleE();

    /**
     * A függvény egy abstract függvény,
     * ami a rongy hatásait állítja be az emberen
     *
     * @param rongy - a rongy aminek a hatása alá kerül az ember
     */
    public void rongyotElszenved(Rongy rongy) {
        CustomLogger.info(this + " ellen rongyot használtak");
    }

    public void tick() {
        List<Targy> copyOfInventory= new ArrayList<>(inventory);
        for (Targy t : copyOfInventory) {
            t.tick();
        }
        if (ajult > 0) {
            ajult--;
        }

        setLepett(false);
    }

    public void controllerLeptet(Ajto a) {
    }

    public Hallgato tamadasElszenved(Oktato oktato) {
        return null;
    }

    public boolean getEletbenVan() {
        return true;
    }


    @Override
    public String toString() {
        return nev + " :Ember";
    }
}
