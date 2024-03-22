package pass.logarlec.human;

import pass.logarlec.item.*;

public interface TargyVisitor {
    void visit(Logarlec logarlec);
    void visit(TVSZ tvsz);
    void visit(Pohar pohar);
    void visit(Rongy rongy);
    void visit(Maszk maszk);

}