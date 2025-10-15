package MachineProblem.TrueCaller.Observer;

public interface ObserverPublisher {
    public void addObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyAllObservers();
}
