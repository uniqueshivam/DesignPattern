package MachineProblem;

import MachineProblem.LRUCache.LRUCacheInitialImplementation;

public class main {
    public static void main(String[] args) {
//        LRUCacheInitialImplementation lruCacheInitialImplementation = new LRUCacheInitialImplementation();
//        lruCacheInitialImplementation.putDataInCache("A","TEST");
//        lruCacheInitialImplementation.putDataInCache("B","TEST1");
//        lruCacheInitialImplementation.putDataInCache("C","TEST2");
//        lruCacheInitialImplementation.putDataInCache("D","TEST2");
//        lruCacheInitialImplementation.putDataInCache("E","TEST3");
//        System.out.println("fetching value from the map "+ lruCacheInitialImplementation.getDataFromCache("A"));
//        System.out.println("fetching value from the map "+ lruCacheInitialImplementation.getDataFromCache("B"));
//        System.out.println("fetching value from the map "+ lruCacheInitialImplementation.getDataFromCache("A"));
//        lruCacheInitialImplementation.putDataInCache("F","TEST4");

        URLShortener urlShortener = new URLShortener();
        System.out.println(urlShortener.getShortenUrl("https://getmyparking.com"));
        System.out.println(urlShortener.getLongUrl("http://tiny.url/1F3Ohq"));
    }
}
