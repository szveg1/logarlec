package pass.model.human;

import pass.model.Idozitett;
import pass.model.item.Rongy;
import pass.model.labyrinth.Szoba;
import pass.model.item.Targy;
import pass.model.item.Maszk;

import java.util.*;
import java.util.logging.*;

import static pass.model.Main.logger;

public abstract class Ember implements TargyVisitor, Idozitett {
    protected final List<Targy> inventory = new ArrayList<>();
    private Szoba jelenlegiSzoba = null;
    private boolean gazEllenVedett = false;
    private boolean ajult = false;

    // Csak a szkeletonhoz-------------
    protected String nev;
    public Ember(String nev){
        this.nev = nev;
    }
    public String toString(){
        return nev + " :Ember";
    }
    //---------------------------------
    public void targyatFelvesz(Targy targy) {
        if (ajult){
            logger.log(Level.WARNING, this + " ájult, nem tud felvenni targyat");
            return;
        }
        if(inventoryTeleE()) {
            logger.log(Level.WARNING,this + "-nek tele az inventoryja, nem tud felvenni targyat");
            return;
        }
        if(jelenlegiSzoba != null) {
            jelenlegiSzoba.removeItem(targy);
        }
        inventory.add(targy);
        logger.info(this + " felvette a " + targy + "-at");
        targy.szobaValtasrolErtesit(jelenlegiSzoba);
    }


    /**
     *
     * @param targy
     */
    public void targyatEldob(Targy targy) {
        if (ajult){
            logger.log(Level.WARNING,this + " ájult, nem tud eldobni targyat");
            return;
        }
        if(inventory.isEmpty()) {
            logger.log(Level.WARNING,this + "-nek üres az inventoryja, nem tud eldobni targyat");
            return;
        }
        inventory.remove(targy);
        logger.info(this + " eldobta a " + targy + "-at");
        jelenlegiSzoba.addItem(targy);
    }

    /**
     *
     * @param maszk
     */
    @Override
    public void visit(Maszk maszk) {
        logger.info(this + " meglátogatta a " + maszk + "-ot");
        logger.info("védett lett tőle: " + (maszk.getVedIdo() > 0));
        setGazEllenVedett(maszk.getVedIdo() > 0);
    }


    public void ajulas() {
        ajult = true;
        logger.info(this + " elájult");
        for(Targy targy : inventory) {
            targyatEldob(targy);
        }
    }

    public void masikSzobabaLep(Szoba ujSzoba) {
        if (ajult) {
            logger.log(Level.WARNING,this + " ájult, nem tud szobát váltani");
            return;
        }
        ujSzoba.emberBetesz(this);
        jelenlegiSzoba = ujSzoba;
        for (Targy targy : inventory) {
            targy.szobaValtasrolErtesit(ujSzoba);
        }
    }

    public void kilepSzobajabol() {
        if(jelenlegiSzoba == null) return;
        logger.info(this + " elhagyta a " + jelenlegiSzoba +"-t");
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

    public void addItem(Targy t) {
        inventory.add(t);
    }

    public boolean getAjult() {
        return ajult;
    }

    abstract boolean inventoryTeleE();

    public abstract void rongyotElszenved(Rongy rongy);

    public abstract void tick();
}
