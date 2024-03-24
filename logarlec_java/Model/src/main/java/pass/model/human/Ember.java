package pass.model.human;

import pass.model.CustomRecordFormatter;
import pass.model.Idozitett;
import pass.model.Main;
import pass.model.item.Rongy;
import pass.model.labyrinth.Szoba;
import pass.model.item.Targy;
import pass.model.item.Maszk;

import java.util.*;
import java.util.logging.*;


public abstract class Ember implements TargyVisitor, Idozitett {
    private static final Logger emberLogger = Logger.getLogger(Ember.class.getSimpleName());
    static{
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new CustomRecordFormatter());
        emberLogger.setUseParentHandlers(false);
        emberLogger.addHandler(handler);
    }
    // Csak a szkeletonhoz-------------
    protected String nev;
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
            emberLogger.log(Level.WARNING, this + " ájult, nem tud felvenni targyat");
            return;
        }
        if(inventoryTeleE()) {
            emberLogger.log(Level.WARNING,this + "-nek tele az inventoryja, nem tud felvenni targyat");
            return;
        }
        if(jelenlegiSzoba != null) {
            jelenlegiSzoba.removeItem(targy);
        }
        inventory.add(targy);
        emberLogger.info(this + " felvette a " + targy + "-t");
    }


    /**
     *
     * @param targy
     */
    public void targyatEldob(Targy targy) {
        if (ajult){
            emberLogger.log(Level.WARNING,this + " ájult, nem tud eldobni targyat");
            return;
        }
        if(inventory.isEmpty()) {
            emberLogger.log(Level.WARNING,this + "-nek üres az inventoryja, nem tud eldobni targyat");
            return;
        }
        inventory.remove(targy);
        emberLogger.info(this + " eldobta a " + targy + "-t");
        jelenlegiSzoba.addItem(targy);
    }

    /**
     *
     * @param maszk
     */
    @Override
    public void visit(Maszk maszk) {
        emberLogger.info(this + " meglátogatta a " + maszk + "-ot");
        emberLogger.info("védett lett tőle: " + (maszk.getVedIdo() > 0));
        setGazEllenVedett(maszk.getVedIdo() > 0);
    }


    public void ajulas() {
        ajult = true;
        emberLogger.info(this + " elájult");
        for(Targy targy : inventory) {
            targyatEldob(targy);
        }
    }

    public void masikSzobabaLep(Szoba ujSzoba) {
        if (ajult) {
            emberLogger.log(Level.WARNING,this + " ájult, nem tud szobát váltani");
            return;
        }
        ujSzoba.emberBetesz(this);
        jelenlegiSzoba = ujSzoba;
        emberLogger.info(this + " belépett a " + ujSzoba + "-ba");
        for (Targy targy : inventory) {
            targy.szobaValtasrolErtesit(ujSzoba);
        }
    }

    public void kilepSzobajabol() {
        if(jelenlegiSzoba == null) return;
        emberLogger.info(this + " elhagyta a " + jelenlegiSzoba +"-t");
        jelenlegiSzoba.emberKivesz(this);

    }

    public boolean GazEllenVedettE() {
        return gazEllenVedett;
    }

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

    public abstract void rongyotElszenved(Rongy rongy);

    public void tick(){
        for(Targy t : inventory){
            t.accept(this);
            t.tick();
        }
    }
}
