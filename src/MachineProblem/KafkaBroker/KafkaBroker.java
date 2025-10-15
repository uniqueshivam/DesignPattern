package MachineProblem.KafkaBroker;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;

public class KafkaBroker {
    public static void main(String[] args) throws InterruptedException {
        Map<String, BlockingQueue<Message>> kakfaMap = new ConcurrentHashMap<>();
        Producer producer = new Producer(kakfaMap);

        ExecutorService producerExecutor = Executors.newFixedThreadPool(6);
        for (int i = 0; i < 5; i++) {
            final int topicIndex = i;
            for (int j = 0; j < 3; j++) {
                final int messageIndex = j;
                producerExecutor.submit(() -> {
                    try {
                        Message message = new Message("This message number is " + messageIndex, Instant.now());
                        producer.publishMessage("topic/" + topicIndex, message);
                    } catch (Exception ex) {
                        System.out.println("Error occurred for the topic " + topicIndex + " " + ex.getMessage());
                    }

                });
            }
        }

        ExecutorService consumerExecutor = Executors.newFixedThreadPool(6);

        for(int i = 0;i<5;i++) {
            final int topicIndex = i;
            consumerExecutor.submit(()->{
               new Consumer("topic/"+topicIndex,kakfaMap.computeIfAbsent("topic/"+topicIndex,k->new LinkedBlockingQueue<>()));
            });
        }

        producerExecutor.shutdown();
        producerExecutor.awaitTermination(5,TimeUnit.SECONDS);

        consumerExecutor.shutdown();
        consumerExecutor.awaitTermination(5,TimeUnit.SECONDS);


    }

    static class Message {
        private final String message;
        private final Instant timeStamp;
        public static final Message POISON_PILL = new Message("POISON", Instant.EPOCH);

        public Message(String message, Instant timeStamp) {
            this.message = message;
            this.timeStamp = timeStamp;
        }

        public String getMessage() {
            return message;
        }

        public Instant getTimeStamp() {
            return timeStamp;
        }
    }

    static class Producer {
        private final Map<String, BlockingQueue<Message>> kakfaMap;

        public Producer(Map<String, BlockingQueue<Message>> kakfaMap) {
            this.kakfaMap = kakfaMap;
        }

        public void publishMessage(String topic, Message message) throws InterruptedException {
            kakfaMap.computeIfAbsent(topic,k->new LinkedBlockingQueue<>()).offer(message);
        }
    }

    static class Consumer implements Runnable {
        private final String topic;
        private final BlockingQueue<Message> consumerQueue;
        private volatile boolean running = true;

        public Consumer(String topic, BlockingQueue<Message> consumerQueue) {
            this.topic = topic;
            this.consumerQueue = consumerQueue;
        }

        @Override
        public void run() {
            while(running) {
                try {
                    Message message = consumerQueue.take();
                    if(message.getMessage() =="POISON") {
                        break;
                    }
                    System.out.println("Message on the topic "+topic+" "+message.getMessage()+" At"+" "+message.getTimeStamp());
                } catch (Exception ex) {
                    Thread.currentThread().interrupt();
                    running = false;
                    System.out.println("Error occurred in reading topic "+topic+" "+ex.getMessage());
                }

            }
        }

        public void shutDown() {
            running = false;
        }
    }


}
