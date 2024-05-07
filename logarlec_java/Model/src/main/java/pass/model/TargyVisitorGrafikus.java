package pass.model;

import pass.model.item.*;

public interface TargyVisitorGrafikus {
    void visit(Logarlec logarlec);

    void visit(TVSZ tvsz);

    void visit(Pohar pohar);

    void visit(Rongy rongy);

    void visit(Maszk maszk);

    void visit(Legfrissito legfrissito);

    void visit(HamisMaszk hamisMaszk);

    void visit(HamisLec hamisLec);

    void visit(HamisTVSZ hamisTVSZ);

    void visit(Camembert camembert);

    void visit(Tranzisztor tranzisztor);
}
