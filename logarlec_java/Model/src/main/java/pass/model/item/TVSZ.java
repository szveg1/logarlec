package pass.model.item;

import pass.model.human.Oktato;
import pass.model.labyrinth.Szoba;
import pass.model.human.TargyVisitor;

public class TVSZ implements Targy {
    // Csak szkeletonhoz-------------
    private String nev;
    public TVSZ(String nev) {
        this.nev = nev;
    }

    @Override
    public String toString() {
        return nev + " :TVSZ";
    }

    // -------------------------------

    private Szoba jelenlegiSzoba;
    private int vedelmekSzama = 3;
    @Override
    public void hasznal(Oktato oktato) {
        if(vedelmekSzama > 0){
            vedelmekSzama--;
        }else{
            return;
        }
        if (jelenlegiSzoba == null) {
            return;
        }
        jelenlegiSzoba.immunitastAd(oktato, oktato.getKitTamad());
    }

    @Override
    public void accept(TargyVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void szobaValtasrolErtesit(Szoba newSzoba) {
        jelenlegiSzoba = newSzoba;
    }

    public boolean hasznalhatoE() {
        return vedelmekSzama > 0;
    }

    @Override
    public void tick() {

    }
}
