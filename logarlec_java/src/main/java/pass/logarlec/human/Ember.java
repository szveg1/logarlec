package pass.logarlec.human;

import pass.logarlec.labyrinth.Szoba;
import pass.logarlec.item.Targy;
import pass.logarlec.item.Maszk;

import java.util.*;

public abstract class Ember implements TargyVisitor {
    protected final List<Targy> inventory = new ArrayList<>();
    private Szoba jelenlegiSzoba = null;
    private boolean gazEllenVedett = false;
    private boolean ajult = false;

    public void targyatFelvesz(Targy targy) {
        if(inventoryTeleE()) {
            System.out.println("Inventory is full, cannot add item.");
            return;
        }
        jelenlegiSzoba.removeItem(targy);
        inventory.add(targy);
        targy.szobaValtasrolErtesit(jelenlegiSzoba);
    }

    public void targyatEldob(Targy targy) {
        if(inventory.isEmpty()){
            System.out.println("Inventory is empty, cannot remove item.");
            return;
        }
        inventory.remove(targy);
        jelenlegiSzoba.addItem(targy);
    }


    private void vedelmetHasznal() {
        for (Targy targy : inventory) {
            targy.accept(this);
        }
    }


    @Override
    public void visitMaszk(Maszk maszk) {
        setGazEllenVedett(maszk.getVedIdo() > 0);
    }


    public void ajulas() {
        ajult = true;
    }


    public void update() {
        vedelmetHasznal();
    }


    public void masikSzobabaLep(Szoba newSzoba) {
        for (Targy targy : inventory) {
            targy.szobaValtasrolErtesit(newSzoba);
        }
        this.jelenlegiSzoba = newSzoba;
        vedelmetHasznal();
    }

    public boolean GazEllenVedettE() {
        return gazEllenVedett;
    }

    public void setGazEllenVedett(boolean isProtected) {
        this.gazEllenVedett = isProtected;
    }

    public boolean AjultE() {
        return ajult;
    }

    abstract boolean inventoryTeleE();
}
