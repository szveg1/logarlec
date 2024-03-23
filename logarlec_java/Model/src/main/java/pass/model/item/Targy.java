package pass.model.item;

import pass.model.Idozitett;
import pass.model.human.Oktato;
import pass.model.human.TargyVisitor;
import pass.model.labyrinth.Szoba;

public interface Targy extends Idozitett {
    void hasznal(Oktato oktato);
    void accept(TargyVisitor visitor);
    void szobaValtasrolErtesit(Szoba newSzoba);
}
