package ObserverPattern;

public class ParkingSessionServiceObserver implements MessagingQueueObserver{
    @Override
    public void update(String message) {
        System.out.println("Parking session service received the message "+message);
    }
}
