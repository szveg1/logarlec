package pass.model.item;

import pass.model.Idozitett;
import pass.model.human.Oktato;
import pass.model.human.Ember;
import pass.model.human.TargyVisitor;
import pass.model.labyrinth.Szoba;
/* A Tárgy osztály felelős a tárgyak használatát biztosító interface megvalósításáért */
public interface Targy extends Idozitett {
    default void hasznal(Oktato oktato) {

    }
    default void hasznal(){

    }

    /**
     *
     * @param ember - A tárgy bírtoklója
     */
    default void setTulajdonos(Ember ember) {

    }

    /**
     *
     * @param newEmber - Az új tulajdonosa
     */
    default void emberValtasrolErtesit(Ember newEmber) {

    }

    /**
     *
     * @param tranz -A beállítani kívánt párja
     */
    default void setPar(Tranzisztor tranz) {

    }

    /**
     *
     * @param visitor - A visitor, amit fogad
     */
    void accept(TargyVisitor visitor);

    /**
     *
     * @param newSzoba - Az új szoba, ahova átkerült
     */
    void szobaValtasrolErtesit(Szoba newSzoba);
}
