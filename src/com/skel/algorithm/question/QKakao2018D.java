package com.skel.algorithm.question;

import java.util.ArrayList;
import java.util.Collections;

public class QKakao2018D implements Question {
    public static int stringToint(String time) {
        String[] sTime = time.split(":");
        int hour = Integer.parseInt(sTime[0]);
        int minute = Integer.parseInt(sTime[1]);
        return hour * 60 + minute;
    }

    public static String intToString(int time) {
        int hour = time / 60;
        int minute = time % 60;
        return String.format("%02d:%02d", hour, minute);
    }


    //
    public int getSize(int v) {
        // from ~ to 사이 값 개수를 구함
        int count = 0;
        for (Integer value : list) {
            if (value <= v) {
                count++;
            }
        }
        return count;
    }

    static ArrayList<Integer> list = new ArrayList<>();

    public String solution(int n, int t, int m, String[] timetable) {
        int crewLength = timetable.length;

        for (int i = 0; i < timetable.length; i++) {
            int time = stringToint(timetable[i]);
            list.add(time);
        }

        Collections.sort(list);
        int startTime = stringToint("09:00");
        int lastTime = startTime;
        int crewIndex = 0;
        for (int i = 0; i < n; i++) {
            int seat = m;
            for(int j=crewIndex; j<crewLength; j++){
                if(list.get(crewIndex) <= startTime){
                    crewIndex++;
                    if(--seat==0) break;
                }
            }
            if(i == n-1){
                if(seat == 0){
                    lastTime = list.get(crewIndex)-1;
                }else{
                    lastTime = startTime;
                    break;
                }
            }
            if (startTime >= 60 * 18) break;
            startTime += t;
        }

        return intToString(lastTime);
    }

    @Override
    public void run() {
//        System.out.println(solution(2,10,2,new String[]{"09:10", "09:09", "08:00"}));
        System.out.println(solution(10, 60, 1, new String[]{"09:01", "08:59", "07:03", "06:04", "06:04", "08:59", "07:03", "06:04", "06:04", "08:59", "07:03", "06:04", "06:04", "08:59", "07:03", "06:04", "06:04"}));
    }
}
