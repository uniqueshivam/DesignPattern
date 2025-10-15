package MachineProblem.BlockingQueueImplemnetation;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private final Queue<T> queue = new LinkedList<>();
    private final int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    // Producer method
    public synchronized void put(T item) throws InterruptedException {
        // wait if full
        while (queue.size() == capacity) {
            wait(); // release lock, wait until notified
        }
        queue.add(item);
        notifyAll(); // notify waiting consumers
    }

    // Consumer method
    public synchronized T take() throws InterruptedException {
        // wait if empty
        while (queue.isEmpty()) {
            wait();
        }
        T item = queue.poll();
        notifyAll(); // notify waiting producers
        return item;
    }

    public synchronized int size() {
        return queue.size();
    }
}
