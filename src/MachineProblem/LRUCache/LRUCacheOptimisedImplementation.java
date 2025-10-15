package MachineProblem.LRUCache;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheOptimisedImplementation {
    private final int capacity;
    private final Map<String, DoubleLinkedNode> cacheMap;
    private DoubleLinkedNode head;
    private DoubleLinkedNode tail;


    public LRUCacheOptimisedImplementation(int capacity) {
        this.capacity = capacity;
        this.cacheMap = new HashMap<>();
        head = new DoubleLinkedNode(null, null);  // dummy head
        tail = new DoubleLinkedNode(null, null);  // dummy tail
        head.next = tail;
        tail.prev = head;
    }

    public Object getFromCache(String key) {
        if (!cacheMap.containsKey(key)) {
            return -1;
        }
        DoubleLinkedNode node = cacheMap.get(key);
        remove(node);
        addToFront(node);
        return node.val;
    }

    public void put(String key, Object value) {
        if (cacheMap.containsKey(key)) {
            DoubleLinkedNode node = cacheMap.get(key);
            node.val = value;
            remove(node);
            addToFront(node);
        } else {
            if (cacheMap.size() == capacity) {
                DoubleLinkedNode lru = tail.prev;
                remove(lru);
                cacheMap.remove(lru.key);
            }
            DoubleLinkedNode newNode = new DoubleLinkedNode(key, value);
            addToFront(newNode);
            cacheMap.put(key, newNode);
        }
    }

    private void remove(DoubleLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToFront(DoubleLinkedNode node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
}
