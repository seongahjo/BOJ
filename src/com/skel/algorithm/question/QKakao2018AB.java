package com.skel.algorithm.question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QKakao2018AB implements Question {


    Map<String, Integer> map = new HashMap<>();


    // <- 로 가면서 없으면 break;

    public int[] solution(String msg) {
        //맵 초기 추가
        for (int i = 'A'; i <= 'Z'; i++) {
            map.put(String.valueOf((char) i), map.size() + 1);
        }

        ArrayList<Integer> sb = new ArrayList<>();
        for (int i = 0; i < msg.length(); i++) {
            int start = i;
            int end = i + 1;
            while (msg.length() >= end) {
                String sub = msg.substring(start, end);
                if (map.get(sub) == null) {
                    // 맵에 없을경우
                    map.put(sub, map.size() + 1);
                    i--;
                    end--;
                    break;
                } else {
                    i++;
                    // 맵에 있을경우
                    end++;
                }
            }
            sb.add(map.get(msg.substring(start,end>msg.length()?msg.length() : end)));
        }


        int[] answer = sb.stream().mapToInt(i->i).toArray();
        return answer;
    }


    @Override
    public void run() {
        System.out.println(solution("KAKAO"));
    }
}
