package pass.logarlec.item;

import pass.logarlec.Idozitett;
import pass.logarlec.human.Oktato;
import pass.logarlec.human.TargyVisitor;
import pass.logarlec.labyrinth.Szoba;

public interface Targy extends Idozitett {
    void hasznal(Oktato oktato);
    void accept(TargyVisitor visitor);
    void szobaValtasrolErtesit(Szoba newSzoba);
}
