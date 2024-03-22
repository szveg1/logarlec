package pass.logarlec.item;

import pass.logarlec.human.Oktato;
import pass.logarlec.labyrinth.Szoba;
import pass.logarlec.human.TargyVisitor;

public class TVSZ implements Targy {

    private Szoba jelenlegiSzoba;
    private int vedelmekSzama = 3;
    @Override
    public void hasznal(Oktato oktato) {
        if(vedelmekSzama > 0){
            vedelmekSzama--;
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
