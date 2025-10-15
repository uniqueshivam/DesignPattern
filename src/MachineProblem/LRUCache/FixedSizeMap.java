package MachineProblem.LRUCache;

import lombok.ToString;

import java.util.HashMap;

public class FixedSizeMap<K,V> extends HashMap<K,V> {
    private final int maxSize;

    public FixedSizeMap(int maxSize) {
        this.maxSize = maxSize;
    }

    public Integer getMaxSize() {
        return this.maxSize;
    }

    @Override
    public V put(K key, V value) {
        return super.put(key,value);
    }
}
