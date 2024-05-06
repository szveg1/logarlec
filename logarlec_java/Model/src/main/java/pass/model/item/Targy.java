package pass.model.item;

import pass.model.CustomLogger;
import pass.model.Idozitett;
import pass.model.TargyVisitor;
import pass.model.TargyVisitorGrafikus;
import pass.model.graphichelper.DrawObservable;
import pass.model.human.Ember;
import pass.model.human.Oktato;
import pass.model.labyrinth.Szoba;

/**
 * A Tárgy osztály felelős a tárgyak használatát biztosító interface megvalósításáért
 */
public abstract class Targy extends DrawObservable implements Idozitett {
    protected String nev;
    protected Szoba jelenlegiSzoba;
    protected Ember tulajdonos;

    public String getNev() {
        return nev;
    }

    public abstract void accept(TargyVisitorGrafikus visitor);

    /**
     * @param visitor - A visitor, amit fogad
     */
    public void accept(TargyVisitor visitor) {
        CustomLogger.info(visitor + "-t " + this + " fogadta.");
    }

    public void hasznal(Oktato oktato) {

    }

    public void hasznal() {

    }

    /**
     * A függvény a tárgynak új tulajdonost állít be
     *
     * @param ujTulajdonos - Az új tulajdonosa
     */
    public void emberValtasrolErtesit(Ember ujTulajdonos) {
        tulajdonos = ujTulajdonos;
    }

    /**
     * @param tranz -A beállítani kívánt párja
     */
    public void setPar(Tranzisztor tranz) {

    }

    /**
     * A függvény a tárgy tartózkodási helyét frissíti
     *
     * @param ujSzoba - szobaváltás után ebbe a szobába kerül a tárgy
     */
    public void szobaValtasrolErtesit(Szoba ujSzoba) {
        jelenlegiSzoba = ujSzoba;
    }


}
