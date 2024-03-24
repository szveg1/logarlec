package pass.model.human;

import pass.model.Idozitett;
import pass.model.item.Rongy;
import pass.model.labyrinth.Szoba;
import pass.model.item.Targy;
import pass.model.item.Maszk;

import java.util.*;

public abstract class Ember implements TargyVisitor, Idozitett {
    protected final List<Targy> inventory = new ArrayList<>();
    private Szoba jelenlegiSzoba = null;
    private boolean gazEllenVedett = false;
    private boolean ajult = false;

    public void targyatFelvesz(Targy targy) {
        if (ajult) return;
        if(inventoryTeleE()) {
            // Valami figyelmeztetes h tele az inventory
            return;
        }
        if(jelenlegiSzoba != null) {
            jelenlegiSzoba.removeItem(targy);
        }
        inventory.add(targy);
        targy.szobaValtasrolErtesit(jelenlegiSzoba);
    }

    public void targyatEldob(Targy targy) {
        if (ajult) return;
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
        if (ajult) return;
        newSzoba.emberBetesz(this);
        for (Targy targy : inventory) {
            targy.szobaValtasrolErtesit(newSzoba);
        }
    }

    public void kilepSzobajabol() {
        if(jelenlegiSzoba == null) return;
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

    public void setJelenlegiSzoba(Szoba sz) {
        jelenlegiSzoba = sz;
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
