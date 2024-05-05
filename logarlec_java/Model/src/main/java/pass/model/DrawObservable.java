package pass.model;

public abstract class DrawObservable {
    protected DrawObserver observer;
    public abstract void changeObserver(DrawObserver drawObserver);
    public abstract void notifyObserver();
}
