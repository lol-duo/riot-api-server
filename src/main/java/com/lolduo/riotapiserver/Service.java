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
    private static final long[] apiCallList = new long[5];
    private static int nowApiCall = 0;

   public int getApiCallCount() {
       return apiCallCount;
   }

   public void resetApiCallCount() {
       apiCallCount = 0;
   }

   private synchronized void checkTimer() {
       if(System.currentTimeMillis() - apiCallList[nowApiCall] < 1000) {
           try {
               Thread.sleep(1000 - (System.currentTimeMillis() - apiCallList[nowApiCall]));
           } catch (InterruptedException ignored) {
           }
       }

       apiCallList[nowApiCall] = System.currentTimeMillis();
       nowApiCall = (nowApiCall + 1) % 5;
       apiCallCount++;
   }

    public synchronized <T> T getApi(Class<T> responseType,URI uri){
        checkTimer();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.set("X-Riot-Token", "");
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

}
