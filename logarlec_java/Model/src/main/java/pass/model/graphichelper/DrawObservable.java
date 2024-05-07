package pass.model.graphichelper;

import java.util.ArrayList;

public abstract class DrawObservable implements IObservable {
    protected final ArrayList<DrawObserver> observers = new ArrayList<>();

    public abstract void notifyObservers();

    public void addObserver(DrawObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(DrawObserver observer) {
        observers.remove(observer);
    }


}
