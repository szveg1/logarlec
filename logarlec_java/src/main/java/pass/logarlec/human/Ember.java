package pass.logarlec.human;

import pass.logarlec.Idozitett;
import pass.logarlec.item.Rongy;
import pass.logarlec.labyrinth.Szoba;
import pass.logarlec.item.Targy;
import pass.logarlec.item.Maszk;

import java.util.*;

public abstract class Ember implements TargyVisitor, Idozitett {
    protected final List<Targy> inventory = new ArrayList<>();
    private Szoba jelenlegiSzoba = null;
    private boolean gazEllenVedett = false;
    private boolean ajult = false;

    public void targyatFelvesz(Targy targy) {
        if(inventoryTeleE()) {
            // Valami figyelmeztetes h tele az inventory
            return;
        }
        jelenlegiSzoba.removeItem(targy);
        inventory.add(targy);
        targy.szobaValtasrolErtesit(jelenlegiSzoba);
    }

    public void targyatEldob(Targy targy) {
        if(inventory.isEmpty()){
            // Valami figyelmeztetes h ures az inventory
            return;
        }
        inventory.remove(targy);
        jelenlegiSzoba.addItem(targy);
    }

    @Override
    public void visit(Maszk maszk) {
        setGazEllenVedett(maszk.getVedIdo() > 0);
    }


    public void ajulas() {
        ajult = true;
        for(Targy targy : inventory) {
            targyatEldob(targy);
        }
    }

    public void masikSzobabaLep(Szoba newSzoba) {
        newSzoba.emberBetesz(this);
        for (Targy targy : inventory) {
            targy.szobaValtasrolErtesit(newSzoba);
        }
    }

    public void kilepSzobajabol() {
        jelenlegiSzoba.emberKivesz(this);
        jelenlegiSzoba = null;
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


    abstract boolean inventoryTeleE();

    public abstract void rongyotElszenved(Rongy rongy);

    public abstract void tick();
}
