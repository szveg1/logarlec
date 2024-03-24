package pass.model.item;

import pass.model.Idozitett;
import pass.model.human.Oktato;
import pass.model.human.TargyVisitor;
import pass.model.labyrinth.Szoba;

public interface Targy extends Idozitett {
    default void hasznal(Oktato oktato) {

    }
    default void hasznal(){

    }
    void accept(TargyVisitor visitor);
    void szobaValtasrolErtesit(Szoba newSzoba);
}
