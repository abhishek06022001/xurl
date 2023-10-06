package com.crio.shorturl;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class XUrlImpl implements XUrl{
    private  HashMap<String, String> url ; 
    private   HashMap<String, Integer> count ; 
    private Random random = new Random();
    private String shortUrlPrefix = "http://short.url/";
    private String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public XUrlImpl(){
        this.url = new HashMap<>();
        this.count = new HashMap<>();
    }
    /*
     * You have to create a random string of length 9 characters.
     * The characters can only be one of [a-z], [A-Z] or [0-9]. Use
     * these rules and java.util.Random to achieve this.
     */
    @Override
    public String registerNewUrl(String longUrl) {
        count.put(longUrl, count.getOrDefault(longUrl, 0) + 1);

        if (url.containsKey(longUrl)) {
            return url.get(longUrl);
        }

        String shorturl;
        boolean isUnique;
        do {
            shorturl = shortUrlPrefix;
            for (int i = 0; i < 9; i++) {
                int need = random.nextInt(alphabets.length());
                shorturl = shorturl + alphabets.charAt(need);
            }
            isUnique = url.containsValue(shorturl); // Check if the shorturl is unique
        } while (isUnique);

        url.put(longUrl, shorturl);
      

        return shorturl;
    }

    @Override
    public String registerNewUrl(String longUrl, String shortUrl) {
        // TODO Auto-generated method stub
       /*  for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            String name = entry.getKey();
            int score = entry.getValue();
            System.out.println(name + ": " + score);
        } */
        count.put(longUrl, count.getOrDefault(longUrl, 0)+1);
        for(Map.Entry<String, String> entry : url.entrySet()){
            if(entry.getValue().equals(shortUrl)){
              return null;
            }
        }
        url.put(longUrl, shortUrl);
        return shortUrl;
    }

    @Override
    public String getUrl(String shortUrl) {
        // TODO Auto-generated method stub
        for(Map.Entry<String, String> entry : url.entrySet()){
            if(entry.getValue().equals(shortUrl)){
                
                return  entry.getKey();
            }
        }
        return null;
    }

    @Override
    public Integer getHitCount(String longUrl) {
        // TODO Auto-generated method stub
       if(count.containsKey(longUrl)){
        return count.get(longUrl);
       }
       return 0;
        
    }

    @Override
    public String delete(String longUrl) {
        // TODO Auto-generated method stub
        url.remove(longUrl);
        return null;
    }

}