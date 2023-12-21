package com.lolduo.riotapiserver;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@org.springframework.stereotype.Service
public class Service {

    private static int apiCallCount = 0;
    private static long apiCallList = 0;
    private static int nowApiCall = 0;

    private static int errorCount = 0;

    public int getErrorCount() {
        return errorCount;
    }

    private static long[] timeList = new long[100];

    private static int i = 0;
    private final Lock mutex = new ReentrantLock();

//    public void checkTimer2() {
//        // acquire mutex
//        mutex.lock();
//
//        try {
//
//            // wait if necessary
//            if (System.currentTimeMillis() - apiCallList[nowApiCall] < 1500) {
//                try {
//                    Thread.sleep(1500 - (System.currentTimeMillis() - apiCallList[nowApiCall]));
//                } catch (InterruptedException ignored) {
//                }
//            }
//
//            // set timer
//            apiCallList[nowApiCall] = System.currentTimeMillis();
//            nowApiCall = (nowApiCall + 1) % 5;
//        } finally {
//            // release mutex
//            mutex.unlock();
//            apiCallCount++;
//        }
//    }
//
//    public int getApiCallCount() {
//        return apiCallCount;
//    }
//
//    private synchronized void checkTimer() {
//        long now = System.currentTimeMillis();
//        if(System.currentTimeMillis() - apiCallList[nowApiCall] < 1000) {
//            try {
//                Thread.sleep(1200 - (System.currentTimeMillis() - apiCallList[nowApiCall]));
//            } catch (InterruptedException ignored) {
//            }
//        }
//
//        if(nowApiCall%5 ==0){
//            System.out.println("----------------------------------");
//        }
//
//        apiCallList[nowApiCall] = System.currentTimeMillis();
//        nowApiCall = (nowApiCall + 1) % 5;
//        apiCallCount++;
//        System.out.println(apiCallCount + " Now : " + System.currentTimeMillis() + " " + Arrays.toString(apiCallList) + " " + (System.currentTimeMillis() - now));
//    }

//    public void test(){
//        checkTimer();
//    }

    public synchronized <T> T getApi(Class<T> responseType,String path){
        mutex.lock();

        try {

            // wait if necessary
            if (System.currentTimeMillis() - apiCallList < 250) {
                try {
                    Thread.sleep(250 - (System.currentTimeMillis() - apiCallList));
                } catch (InterruptedException ignored) {
                }
            }

            // set timer
            apiCallList = System.currentTimeMillis();
        } finally {
            // release mutex
            mutex.unlock();
            apiCallCount++;
            System.out.println(apiCallCount);
        }

        RestTemplate restTemplate = new RestTemplate();
        URI uri = getUri(path);
        HttpHeaders headers = new HttpHeaders();

        headers.set("X-Riot-Token", "");
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<String> data = null;
        try {
            data = restTemplate.exchange("http://localhost:3000/test", HttpMethod.GET, entity, String.class);
        } catch (Exception e) {
            System.out.println(apiCallCount + " " + e.getMessage());
            errorCount++;
        }

        return null;
    }

    public URI getUri(String path) {
        return URI.create("https://kr.api.riotgames.com"+path);
    }

    public void test(){
        timeList[i++] = System.currentTimeMillis();
    }
    public void readTest(){
        System.out.println("=======================start============================");
        for(int i = 0 ; i < 100;i++){
            if(i%5==0){
                System.out.println("");
            }            System.out.print(timeList[i] +" ");

        }
        System.out.println("=======================end============================");
        System.out.println(timeList.toString());
    }
}
