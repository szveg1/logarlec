package pass.model.graphichelper;

public interface IObservable {
    void addObserver(DrawObserver observer);

    void removeObserver(DrawObserver observer);

    void notifyObservers();
}
