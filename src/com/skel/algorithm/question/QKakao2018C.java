package com.skel.algorithm.question;

import java.util.*;

public class QKakao2018C implements Question {

    static Queue<String> cache = new LinkedList<>();

    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        for (String city : cities) {
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<city.length();i++){
                sb.append(Character.toLowerCase(city.charAt(i)));
            }
            city=sb.toString();
            if (!cache.contains(city)) {
                answer += 5;
                if(cacheSize==0) continue;
                if (cache.size() < cacheSize) {
                    cache.offer(city);
                    continue;
                }
                cache.poll();
                cache.offer(city);
            } else {
                answer += 1;
                // 갱신
                cache.remove(city);
                cache.offer(city);
            }
        }

        return answer;
    }

    @Override
    public void run() {
        System.out.println(solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"}));
    }
}
