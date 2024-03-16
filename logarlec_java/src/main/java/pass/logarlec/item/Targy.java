package pass.logarlec.item;

import pass.logarlec.human.TargyVisitor;
import pass.logarlec.labyrinth.Szoba;

public interface Targy {
    void hasznal();
    void accept(TargyVisitor visitor);
    void szobaValtasrolErtesit(Szoba newSzoba);
}
