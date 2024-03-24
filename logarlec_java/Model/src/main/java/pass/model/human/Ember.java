package pass.model.human;

import pass.model.CustomRecordFormatter;
import pass.model.Idozitett;
import pass.model.CustomLogger;
import pass.model.item.Rongy;
import pass.model.labyrinth.Szoba;
import pass.model.item.Targy;
import pass.model.item.Maszk;

import java.util.*;
import java.util.logging.*;


public abstract class Ember implements TargyVisitor, Idozitett {
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
            CustomLogger.log(Level.WARNING, this + " ájult, nem tud felvenni targyat");
            return;
        }
        if(inventoryTeleE()) {
            CustomLogger.log(Level.WARNING,this + "-nek tele az inventoryja, nem tud felvenni targyat");
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
     *
     * @param targy
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
     *
     * @param maszk
     */
    @Override
    public void visit(Maszk maszk) {
        CustomLogger.info(this + " meglátogatta a " + maszk + "-ot");
        CustomLogger.info("védett lett tőle: " + (maszk.getVedIdo() > 0));
        setGazEllenVedett(maszk.getVedIdo() > 0);
    }


    public void ajulas() {
        ajult = true;
        CustomLogger.info(this + " elájult");
        for(Targy targy : inventory) {
            targyatEldob(targy);
        }
    }

    public void masikSzobabaLep(Szoba ujSzoba) {
        if (ajult) {
            CustomLogger.log(Level.WARNING,this + " ájult, nem tud szobát váltani");
            return;
        }
        ujSzoba.emberBetesz(this);
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

    public void targyatHasznal(Targy targy){
        CustomLogger.info(this + " használta a " + targy + "-t");
        targy.hasznal();
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
