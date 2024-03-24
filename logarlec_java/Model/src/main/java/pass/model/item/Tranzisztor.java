package pass.model.item;

import pass.model.human.Oktato;
import pass.model.human.TargyVisitor;
import pass.model.labyrinth.Szoba;
import pass.model.human.Hallgato;
import pass.model.human.Ember;

public class Tranzisztor implements Targy {
    // Csak szkeletonhoz-------------
    private String nev;
    public Tranzisztor(String nev) {
        this.nev = nev;
    }

    @Override
    public String toString() {
        return nev + " :Tranzisztor";
    }

    // -------------------------------
    private Szoba jelenlegiSzoba;
    private Tranzisztor par = null;

    private Ember tulaj;

    @Override
    public void hasznal() {
        if (par == null) {
            for (int i = 0; i < tulaj.getItems().size(); i++) {
                if (i != tulaj.getItems().indexOf(this)) {
                    tulaj.getItems().get(i).setPar(this);
                }
            }
        }
        else {
            tulaj.masikSzobabaLep(par.getSzoba());
        }

    }
    @Override
    public void setPar(Tranzisztor t) {
        this.par = t;
        t.setPar2(this);
    }

    public void setPar2(Tranzisztor t) {
        this.par = t;
    }

    @Override
    public void accept(TargyVisitor visitor) {
        // Nincs passziv effektje
    }

    @Override
    public void szobaValtasrolErtesit(Szoba newSzoba) {
        jelenlegiSzoba = newSzoba;
    }

    @Override
    public void emberValtasrolErtesit(Ember newEmber) {
        tulaj = newEmber;
    }

    public Szoba getSzoba() {
        return jelenlegiSzoba;
    }

    @Override
    public void tick() {

    }
}
