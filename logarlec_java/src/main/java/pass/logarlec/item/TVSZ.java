package pass.logarlec.item;

import pass.logarlec.labyrinth.Szoba;
import pass.logarlec.human.TargyVisitor;

public class TVSZ implements Targy {
// HIANYOS!!!!
    private int vedelmekSzama = 3;
    @Override
    public void hasznal() {

    }

    @Override
    public void accept(TargyVisitor visitor) {
        vedelmekSzama--;
    }

    @Override
    public void szobaValtasrolErtesit(Szoba newSzoba) {

    }

    public boolean hasznalhatoE() {
        return vedelmekSzama > 0;
    }
}
