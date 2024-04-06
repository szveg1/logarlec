package pass.model.human;

import pass.model.CustomRecordFormatter;
import pass.model.Idozitett;
import pass.model.CustomLogger;
import pass.model.item.*;
import pass.model.labyrinth.Szoba;

import java.util.*;
import java.util.logging.*;

/**
 * Az Ember osztály azonosítja a karaktert, valamint tárolja az
 * általuk birtokolt tárgyakat. Képesek tárgyak felvételére, eldobására és használatára.
 */
public abstract class Ember implements TargyVisitor, Idozitett {
    // Csak a szkeletonhoz-------------
    protected String nev;

    /**
     * A függvény elnevezi az objektumot.
     * @param nev - az objektum neve
     */
    public Ember(String nev){
        this.nev = nev;
    }
    public String toString(){
        return nev + " :Ember";
    }
    //---------------------------------
    protected final List<Targy> inventory = new ArrayList<>();
    private Szoba jelenlegiSzoba = null;
    private boolean gazEllenVedett = false;
    private boolean ajult = false;

    public void targyatFelvesz(Targy targy) {
        if (ajult){
            CustomLogger.log(Level.WARNING, this + " ájult, nem tud felvenni targyat");
            return;
        }
        if(inventoryTeleE()) {
            CustomLogger.log(Level.WARNING,this + "-nek tele az inventoryja, nem tud felvenni targyat");
            return;
        }
        if(jelenlegiSzoba.ragacsosE()){
            CustomLogger.log(Level.WARNING,this + " ragacsos szobaban van, nem tud felvenni targyat");
            return;
        }
        CustomLogger.info(this + " felvette a " + targy + "-t");

        if(jelenlegiSzoba != null) {
            jelenlegiSzoba.removeItem(targy);
        }
        inventory.add(targy);
        targy.emberValtasrolErtesit(this);
    }


    /**
     * A függvény a megadott tárgyat eldobja és kiszedi
     * az ember inventory-jából, miközben eltávolítja
     * a tárgyat a szobából
     * @param targy - az eldobandó tárgy
     */
    public void targyatEldob(Targy targy) {
        if (ajult){
            CustomLogger.log(Level.WARNING,this + " ájult, nem tud eldobni targyat");
            return;
        }
        if(inventory.isEmpty()) {
            CustomLogger.log(Level.WARNING,this + "-nek üres az inventoryja, nem tud eldobni targyat");
            return;
        }

        inventory.remove(targy);;
        CustomLogger.info(this + " eldobta a " + targy + "-t");
        targy.emberValtasrolErtesit(null);
        jelenlegiSzoba.addItem(targy);
    }

    /**
     * A függvény meglátogatja a medaott maszkot
     * és a gáz elleni védelmét beállítja az embernek
     * @param maszk - a maszk amit meglátogat
     */
    @Override
    public void visit(Maszk maszk) {
        CustomLogger.info(this + " meglátogatta a " + maszk + "-ot");
        CustomLogger.info("védett lett " + maszk + "-tól: " + (maszk.getVedIdo() > 0));
        setGazEllenVedett(maszk.getVedIdo() > 0);
    }

    public void visit(Legfrissito legfrissito){
        CustomLogger.info(this + " meglátogatta a " + legfrissito + "-t");
        this.targyatEldob(legfrissito);
    }

    /**
     * A függvény meglátogatja a megadott hamis Logarlécet,
     * ami az ember birtokában semmilyen hatással nincs
     * @param hamisLec - a meglátogatott hamis Logarléc
     */
    @Override
    public void visit(HamisLec hamisLec) {
        CustomLogger.info("a " + hamisLec + " birtoklása nincs hatással " + this + "-ra.");
    }

    /**
     * A függvény meglátogatja a megadott hamis TVSZ-t,
     * ami az ember birtokában semmilyen hatással nincs
     * @param hamisTVSZ - a meglátogatott pohár
     */
    @Override
    public void visit(HamisTVSZ hamisTVSZ) {
        CustomLogger.info("a " + hamisTVSZ + " birtoklása nincs hatással " + this + "-ra.");
    }

    /**
     * A függvény meglátogatja a megadott hamis maszkot,
     * ami az ember birtokában semmilyen hatással nincs
     * @param hamisMaszk - a meglátogatott pohár
     */
    @Override
    public void visit(HamisMaszk hamisMaszk) {
        CustomLogger.info("a " + hamisMaszk + " birtoklása nincs hatással " + this + "-ra.");
    }



    public void ajulas() {
        if(gazEllenVedett) return;
        ajult = true;
        CustomLogger.info(this + " elájult");
        for(Targy targy : inventory) {
            targyatEldob(targy);
        }
    }

    /**
     *  A függvény az embert átlépteti a megadott szobába,
     *  amíg az ember nincs elájulva és
     *  az új szobához hozzáadja az embert
     * @param ujSzoba - ebbe a szobába lép át az ember
     */
    public void masikSzobabaLep(Szoba ujSzoba) {
        if (ajult) {
            CustomLogger.log(Level.WARNING,this + " ájult, nem tud szobát váltani");
            return;
        }
        if(!ujSzoba.emberBetesz(this)) return;

        jelenlegiSzoba = ujSzoba;
        CustomLogger.info(this + " belépett a " + ujSzoba + "-ba");
        for (Targy targy : inventory) {
            targy.szobaValtasrolErtesit(ujSzoba);
        }
    }

    public void kilepSzobajabol() {
        if(jelenlegiSzoba == null) return;
        CustomLogger.info(this + " elhagyta a " + jelenlegiSzoba +"-t");
        jelenlegiSzoba.emberKivesz(this);
    }

    /**
     * A függvény az ember által kiválasztott
     * tárgy ahsználatát hívja meg
     * @param targy - a használandó tárgy
     */
    public void targyatHasznal(Targy targy){
        CustomLogger.info(this + " használta a " + targy + "-t");
        targy.hasznal();
    }

    public boolean GazEllenVedettE() {
        return gazEllenVedett;
    }

    /**
     * A függvény egy setter függvény,
     * amivel a gáz elleni védettséget lehet beállítani
     * @param isProtected - a gáz elleni védettséget kommunikálja
     */
    public void setGazEllenVedett(boolean isProtected) {
        this.gazEllenVedett = isProtected;
    }

    public Szoba getJelenlegiSzoba() {
        return jelenlegiSzoba;
    }

    public List<Targy> getItems() {
        return inventory;
    }

    public boolean getAjult() {
        return ajult;
    }

    abstract boolean inventoryTeleE();

    /**
     * A függvény egy abstract függvény,
     * ami a rongy hatásait állítja be az emberen
     * @param rongy - a rongy aminek a hatása alá kerül az ember
     */
    public void rongyotElszenved(Rongy rongy){
        CustomLogger.info(this + " ellen rongyot használtak");
    }

    public void tick(){
        for(Targy t : inventory){
            t.accept(this);
            t.tick();
        }
    }
}
