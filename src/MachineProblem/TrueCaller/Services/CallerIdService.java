package MachineProblem.TrueCaller.Services;

import MachineProblem.TrueCaller.Observer.Observer;
import MachineProblem.TrueCaller.Observer.ObserverPublisher;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class CallerIdService implements ObserverPublisher {
    @Setter
    private String callerIdMessage;
    List<Observer> observerList = new ArrayList<>();
    @Override
    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyAllObservers() {
        observerList.forEach(observer -> observer.update(callerIdMessage));
    }

}
