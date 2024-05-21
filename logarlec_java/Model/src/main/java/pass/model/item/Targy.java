package pass.model.item;

import pass.model.CustomLogger;
import pass.model.Idozitett;
import pass.model.TargyVisitor;
import pass.model.graphichelper.DrawObservable;
import pass.model.graphichelper.DrawObserver;
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

    /**
     * @param visitor - A visitor, amit fogad
     */
    public void accept(TargyVisitor visitor) {
        CustomLogger.info(visitor + "-t " + this + " fogadta.");
    }

    public void hasznal(Oktato oktato) {

    }

    /**
     * A függvény igaz értéket ad vissza
     */
    public boolean hasznalhatoE() {
        return true;
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

    public boolean vanEPar() {
        return false;
    }

    /**
     * A függvény a tárgy tartózkodási helyét frissíti
     *
     * @param ujSzoba - szobaváltás után ebbe a szobába kerül a tárgy
     */
    public void szobaValtasrolErtesit(Szoba ujSzoba) {
        jelenlegiSzoba = ujSzoba;
    }

    public Ember getTulajdonos() {
        return tulajdonos;
    }

    public void notifyObservers() {
        for (DrawObserver observer : observers) {
            observer.update();
        }
    }
}