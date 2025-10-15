package MachineProblem.TrueCaller.Observer;

import java.util.ArrayList;
import java.util.List;

public class SpamDetectionPublisher implements ObserverPublisher{
    List<Observer> observerList = new ArrayList<>();
    private static String spamDetectionMessage;
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
        observerList.forEach(observer -> observer.update(spamDetectionMessage));
    }

    public void updateForSpamDetectionMessage(String message){
        spamDetectionMessage = message;
        notifyAllObservers();
    }
}
