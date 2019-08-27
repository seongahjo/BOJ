package com.skel.algorithm.question;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class QKakao2018AD implements Question {
    String[][] melodyList = {{"C#", "D#", "F#", "G#", "A#"}, {"F", "C", "G", "D", "A", "E", "B"}};

    class Music implements Comparable<Music> {
        int index;
        String name;
        int start;
        int end;
        ArrayList<String> melody;


        public boolean isContain(String m) {
            ArrayList<String> newM = new ArrayList<>();
            parse(m, newM, m.length());
            for (int i = 0; i < melody.size(); i++) {
                if (melody.get(i).equals(newM.get(0))) {
                    int index = 0;
                    for (index = 0; index < newM.size(); index++) {
                        if (i + index >= melody.size()) return false;

                        if (!melody.get(i + index).equals(newM.get(index))) {
                            break;
                        }
                    }
                    if (index == newM.size()) return true;
                }
            }
            return false;
        }

        public Music(int index, String musicInfo) {
            melody = new ArrayList<>();
            this.index = index;
            StringTokenizer st = new StringTokenizer(musicInfo, ",");
            start = parseTime(st.nextToken());
            end = parseTime(st.nextToken());
            this.name = st.nextToken();
            String m = st.nextToken();
            // String은 분별객체...
            while (parse(m, melody, end - start)) ;
        }

        @Override
        public int compareTo(Music o) {
            int t = Integer.compare(o.end - o.start, end - start);
            if (t == 0) {
                return Integer.compare(index, o.index);
            }
            return t;
        }
    }


    public int parseTime(String time) {
        StringTokenizer st = new StringTokenizer(time, ":");
        int hour = Integer.parseInt(st.nextToken()) * 60;
        int minute = Integer.parseInt(st.nextToken());
        return hour + minute;

    }

    public boolean parse(String m, ArrayList<String> melody, int time) {
        for (int i = 0; i < m.length(); i++) {
            if (melody.size() > time)
                return false;
            boolean found = false;
            String[] shop = melodyList[0];
            String[] nonShop = melodyList[1];
            if (i < m.length() - 1) {
                String ns = m.substring(i, i + 2);
                for (String s : shop) {
                    if (ns.equals(s)) {
                        melody.add(ns);
                        found = true;
                        i++;
                        break;
                    }
                }
            }
            if (!found) {
                String ns = m.substring(i, i + 1);
                for (String s : nonShop) {
                    if (ns.equals(s)) {
                        melody.add(ns);
                        break;
                    }
                }
            }
        }
        return true;
    }

    public String solution(String m, String[] musicinfos) {
        ArrayList<Music> musics = new ArrayList<>();
        PriorityQueue<Music> possibility = new PriorityQueue<>();
        for (int i = 0; i < musicinfos.length; i++) {
            Music mm = new Music(i, musicinfos[i]);
            musics.add(mm);
            if (mm.isContain(m))
                possibility.add(mm);
        }


        String answer = possibility.isEmpty() ? "(None)" : possibility.poll().name;
        return answer;
    }

    @Override
    public void run() {
        System.out.println(solution("ABC", new String[]{"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"}));
    }
}
