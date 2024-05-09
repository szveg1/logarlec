package pass.model.graphichelper;

import java.util.ArrayList;

public abstract class DrawObservable implements IObservable {
    protected final ArrayList<DrawObserver> observers = new ArrayList<>();

    public abstract void notifyObservers();

    public void addObserver(DrawObserver observer) {
        observers.add(observer);
        // TODO: remove this line
        System.out.println("Observer added " + observer.getClass().getName());
    }

    public void removeObserver(DrawObserver observer) {
        observers.remove(observer);
    }


}
