package com.lolduo.riotapiserver;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.net.URI;

@org.springframework.stereotype.Service
public class Service {

    private static int apiCallCount = 0;
    private static long[] apiCallList = new long[5];
    private static int nowApiCall = 0;

   public int getApiCallCount() {
       return apiCallCount;
   }

   private synchronized void checkTimer() {
       long now = System.currentTimeMillis();
       if(System.currentTimeMillis() - apiCallList[nowApiCall] < 1000) {
           try {
               Thread.sleep(1000 - (System.currentTimeMillis() - apiCallList[nowApiCall]));
           } catch (InterruptedException ignored) {
           }
       }

       if(nowApiCall%5 ==0){
           System.out.println("----------------------------------");
       }

       apiCallList[nowApiCall] = System.currentTimeMillis();
       nowApiCall = (nowApiCall + 1) % 5;
       apiCallCount++;
   }

    public synchronized <T> T getApi(Class<T> responseType,String path){
        checkTimer();

        RestTemplate restTemplate = new RestTemplate();
        URI uri = getUri(path);
        HttpHeaders headers = new HttpHeaders();

        headers.set("X-Riot-Token", "RGAPI-3b103da8-7fc6-4593-ae8b-d3aea6701632");
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<T> data = null;
        try {
            data = restTemplate.exchange(uri, HttpMethod.GET, entity, responseType);
        } catch (Exception e) {
            System.out.println(apiCallCount + " " + e.getMessage());
        }
        if(data!=null)
            return data.getBody();

        return null;
        
    }

    public URI getUri(String path) {
        return URI.create("https://kr.api.riotgames.com"+path);
    }

}
