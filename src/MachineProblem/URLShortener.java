package MachineProblem;

import java.util.HashMap;
import java.util.Map;

public class URLShortener {
    private Map<String,String> urlMap = new HashMap<>();
    private Map<String,String> reverseMap = new HashMap<>();
    private final String domain = "http://tiny.url/";

    public String getShortenUrl(String longUrl) {
        if(reverseMap.containsKey(longUrl)) {
            return domain+reverseMap.get(longUrl);
        }

        String code = generateShortenCode(longUrl);
        urlMap.put(code,longUrl);
        reverseMap.put(longUrl,code);
        return domain+code;
    }

    public String getLongUrl(String shortUrl){
        String shortCode = shortUrl.split("/")[3];
        return urlMap.get(shortCode);
    }

    private String generateShortenCode(String longUrl) {
        int hash = longUrl.hashCode();
        return generateBase62Encode(hash);
    }

    private String generateBase62Encode(int hashCode) {
        String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        while(hashCode>0) {
            sb.append(chars.charAt(hashCode%62));
            hashCode = hashCode/62;
        }
        return sb.reverse().toString();
    }
}
