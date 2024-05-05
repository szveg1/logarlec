package pass.model.human;

import pass.model.CustomLogger;
import pass.model.item.*;

public interface TargyVisitor {
    default void visit(Logarlec logarlec) {
        CustomLogger.info(this + " meglátogatta a " + logarlec + "-t.");
    }

    default void visit(TVSZ tvsz) {
        CustomLogger.info(this + " meglátogatta a " + tvsz + "-t.");
    }

    default void visit(Pohar pohar) {
        CustomLogger.info(this + " meglátogatta a " + pohar + "-t.");
    }

    default void visit(Rongy rongy) {
        CustomLogger.info(this + " meglátogatta a " + rongy + "-t.");
    }

    default void visit(Maszk maszk) {
        CustomLogger.info(this + " meglátogatta a " + maszk + "-t.");
    }

    default void visit(Legfrissito legfrissito) {
        CustomLogger.info(this + " meglátogatta a " + legfrissito + "-t.");
    }
}