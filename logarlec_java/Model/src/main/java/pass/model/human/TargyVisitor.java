package pass.model.human;

import pass.model.item.*;

public interface TargyVisitor {
    void visit(Logarlec logarlec);
    void visit(TVSZ tvsz);
    void visit(Pohar pohar);
    void visit(Rongy rongy);
    void visit(Maszk maszk);

}