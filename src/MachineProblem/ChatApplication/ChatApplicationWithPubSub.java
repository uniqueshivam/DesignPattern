package MachineProblem.ChatApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class ChatApplicationWithPubSub {
    public static void main(String[] args) {

        Publisher publisher = new Publisher();
        ChatHandler chatHandler = new ChatHandler(publisher);

        ReadChatHandler readChatHandler1 = new ReadChatHandler("user:Alice");
        ReadChatHandler readChatHandler2 = new ReadChatHandler("user:BOB");
        ReadChatHandler readChatHandler3 = new ReadChatHandler("user:Shivam");

        publisher.subscriber(readChatHandler1.getTopic(),readChatHandler1);
        publisher.subscriber(readChatHandler2.getTopic(),readChatHandler2);
        publisher.subscriber(readChatHandler3.getTopic(),readChatHandler3);

        String groupTopic = "group:Friends";

        publisher.subscriber(groupTopic,readChatHandler1);
        publisher.subscriber(groupTopic,readChatHandler2);
        publisher.subscriber(groupTopic,readChatHandler3);

        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(readChatHandler1);
        executor.submit(readChatHandler2);
        executor.submit(readChatHandler3);

        chatHandler.sendOneToOneMessage(readChatHandler1.getTopic(),new Chat("BOB","Hi Alice"));
        chatHandler.sendOneToOneMessage(readChatHandler2.getTopic(),new Chat("Alice","Hi bob"));

        chatHandler.senMessageInGroup(new Chat("Carol", "Hello friends!"),groupTopic);

        try{
            Thread.sleep(2000);
        } catch (Exception ex) {
            System.out.println("Error");
        }

        executor.shutdownNow();
//        executor.awaitTermination(5,TimeUnit.SECONDS);







    }

    static class ChatHandler {
        private final Publisher publisher;

        public ChatHandler(Publisher publisher) {
            this.publisher = publisher;
        }
        public void sendOneToOneMessage(String receiverTopic,Chat chat) {
            publisher.publishMessage(chat,receiverTopic);
        }

        public void senMessageInGroup(Chat chat,String groupTopic) {
            publisher.publishMessage(chat,groupTopic);
        }
    }


    static interface Subscriber {
        String getTopic();
        void onMessage(Chat chat);
    }

    static class Chat {
        private final String sender;
        private final String message;

        public Chat(String sender, String message) {
            this.sender = sender;
            this.message = message;
        }

        public String getSender() {
            return sender;
        }

        public String getMessage() {
            return message;
        }
    }

    static class Publisher {
        Map<String, List<Subscriber>> subscriberListMap = new ConcurrentHashMap<>();

        public void subscriber(String topicToSubscribeOn, Subscriber subscriber) {
            subscriberListMap.computeIfAbsent(topicToSubscribeOn,k-> Collections.synchronizedList(new ArrayList<>())).add(subscriber);

        }

        public void unSubscribe(String topicFromWhereToUnSubscribe, Subscriber subscriber) {
            subscriberListMap.getOrDefault(topicFromWhereToUnSubscribe, new ArrayList<>()).remove(subscriber);
        }

        public void publishMessage(Chat chat,String topic) {
            List<Subscriber> subscriberList = subscriberListMap.get(topic);
            for(Subscriber s : subscriberList) {
                s.onMessage(chat);
            }
        }

    }

    static class ReadChatHandler implements  Runnable,Subscriber {
        private volatile boolean running = true;
        private final String ownerTopic;
        private final BlockingQueue<Chat> consumerBlockingQueue = new LinkedBlockingQueue<>();

        public ReadChatHandler(String ownerTopic) {
            this.ownerTopic = ownerTopic;
        }

        @Override
        public String getTopic() {
            return ownerTopic;
        }

        @Override
        public void onMessage(Chat chat) {
            consumerBlockingQueue.add(chat);
        }

        @Override
        public void run() {
            try {
                while(running) {
                    Chat chat = consumerBlockingQueue.take();
                    System.out.println("[" + ownerTopic + "] got message from " +
                            chat.getSender() + ": " + chat.getMessage());
                }
            } catch (Exception ex) {
                Thread.currentThread().interrupt();
                running = false;
                System.out.println("error occurred");
            }
        }
    }
}
