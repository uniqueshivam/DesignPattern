package MachineProblem.LRUCache;

import java.util.*;

public class LRUCacheInitialImplementation {
    Map<String,Integer> searchCountMap = new HashMap<>();
    Map<Integer, List<String>> countListMap = new HashMap<>();
    FixedSizeMap<String,Object> fixedSizeMap = new FixedSizeMap<>(5);

    public void putDataInCache(String key, Object value) {
        if(fixedSizeMap.size()>= fixedSizeMap.getMaxSize() && !fixedSizeMap.containsKey(key)) {
            clearCache();
        }
        fixedSizeMap.put(key, value);
        searchCountMap.put(key, 0);
        countListMap.computeIfAbsent(0, k -> new ArrayList<>()).add(key);
        System.out.println("Data putted in map");
        System.out.println(fixedSizeMap);
    }


    public Object getDataFromCache(String key) {
        Object data = fixedSizeMap.get(key);
        int count = searchCountMap.get(key);
        int indexToRemove = 0;
        List<String> tempList = countListMap.get(count);
        for(int i = 0;i<tempList.size();i++) {
            if(tempList.get(i).equals(key)) {
                indexToRemove = i;
            }
        }
        tempList.remove(indexToRemove);
        countListMap.put(count,tempList);
        count = count+1;
        searchCountMap.put(key,count);
        countListMap.computeIfAbsent(count, k -> new ArrayList<>()).add(key);
        return data;
    }

    private void clearCache() {
        System.out.println("Clearing the cache");
        int count  =0;
        while(count<=fixedSizeMap.getMaxSize()) {
            List<String> list = countListMap.get(count);
            if(!list.isEmpty()) {
                String key = list.get(0);
                fixedSizeMap.remove(key);
                list.remove(0);
                break;
            }
            count++;
        }
    }
}

