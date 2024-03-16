package pass.logarlec.human;

import pass.logarlec.item.*;

public interface TargyVisitor {
    void visitLogarlec(Logarlec logarlec);
    void visitTVSZ(TVSZ tvsz);
    void visitPohar(Pohar pohar);
    void visitRongy(Rongy rongy);
    void visitMaszk(Maszk maszk);

}