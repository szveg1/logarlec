package pass.model.human;

import pass.model.CustomLogger;
import pass.model.labyrinth.Ajto;
import pass.model.labyrinth.Szoba;

import java.util.ArrayList;
import java.util.List;

public class Takarito extends Ember {
    public Takarito(String nev) {
        super(nev);
    }

    @Override
    public void accept(EmberVisitor visitor) {
        visitor.visitTakarito(this);
    }

    @Override
    public String toString() {
        return nev + " :Takarito";
    }

    @Override
    public boolean inventoryTeleE() {
        return true;
    }

    public boolean masikSzobabaLep(Szoba ujSzoba) {
        if (!ujSzoba.emberBetesz(this)) return false;
        jelenlegiSzoba = ujSzoba;
        CustomLogger.info(this + " belépett a " + ujSzoba + "-ba");
        // Embereket kitessekeli
        List<Ember> emberList = new ArrayList<>(jelenlegiSzoba.getEmberek());
        for (Ember e : emberList) {
            if (!e.equals(this) && !e.getAjult()) {
                List<Ajto> ajtoList = new ArrayList<>(jelenlegiSzoba.getAjtok());
                for (Ajto a : ajtoList) {
                    a.hasznal(e);
                    if (e.getJelenlegiSzoba() != jelenlegiSzoba) break;
                }
            }
        }
        // kiszelloztet
        if (jelenlegiSzoba.mergezoE()) {
            jelenlegiSzoba.setMeregIdo(0);
        }
        jelenlegiSzoba.setTiszta(true);
        return true;
    }

    @Override
    public void ajulas() {
        // nem csinal semmit
    }

    @Override
    public void controllerLeptet(Ajto a) {
        a.hasznal(this);
    }


}
