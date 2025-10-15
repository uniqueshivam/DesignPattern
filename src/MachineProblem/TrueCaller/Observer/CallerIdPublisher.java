package MachineProblem.TrueCaller.Observer;

import java.util.ArrayList;
import java.util.List;

public class CallerIdPublisher implements ObserverPublisher{
    List<Observer> observerList = new ArrayList<>();
    private static String callerIdMessage;
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
        observerList.stream().forEach(observer -> observer.update(callerIdMessage));
    }

    public void updateForCallerIdInformation(String message){
        callerIdMessage = message;
        notifyAllObservers();
    }
}
