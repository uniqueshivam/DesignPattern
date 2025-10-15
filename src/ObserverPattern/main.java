package ObserverPattern;

public class main {
    public static void main(String[] args) {
        MessagingQueueObserver payments  = new PaymentServiceObserver();
        MessagingQueueObserver parkingSession = new ParkingSessionServiceObserver();
        MQTTBroker mqtt = new MQTTBroker();
        mqtt.addObserver(payments);
        mqtt.addObserver(parkingSession);
        mqtt.message = "This is Shivam";
        mqtt.notifyObserver();

        ActiveMqBroker activeMqBroker = new ActiveMqBroker();
        activeMqBroker.addObserver(payments);
        activeMqBroker.message = "This is from active mq";
        activeMqBroker.notifyObserver();

    }
}
