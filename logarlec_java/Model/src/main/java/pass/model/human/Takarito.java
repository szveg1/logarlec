package pass.model.human;

import pass.model.CustomLogger;
import pass.model.labyrinth.Ajto;
import pass.model.labyrinth.Szoba;

public class Takarito extends Ember{

    public Takarito(String nev) {
        super(nev);
    }

    @Override
    public String toString() {
        return nev + " :Takarito";
    }
    @Override
    public boolean inventoryTeleE() {
        return true;
    }

    public void masikSzobabaLep(Szoba ujSzoba) {
        if(!ujSzoba.emberBetesz(this)) return;
        jelenlegiSzoba = ujSzoba;
        CustomLogger.info(this + " belépett a " + ujSzoba + "-ba");
        // Embereket kitessekeli
        for(Ember e : jelenlegiSzoba.getEmberek()){
            if(e != this && !e.getAjult()){
                for(Ajto a : jelenlegiSzoba.getAjtok()){
                    a.hasznal(e);
                    if(e.getJelenlegiSzoba() != jelenlegiSzoba) break;
                }
            }
        }
        // kiszelloztet
        if(jelenlegiSzoba.mergezoE()){
            jelenlegiSzoba.setMeregIdo(0);
        }
        jelenlegiSzoba.setTiszta(true);
    }

}
