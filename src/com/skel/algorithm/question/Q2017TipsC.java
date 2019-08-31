package com.skel.algorithm.question;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Q2017TipsC implements Question {

    // 100개
    // 단어조각 길이 1 ~ 5
    // 완성해야하는 문자열 길이 20,000 이하


    class Point implements Comparable<Point> {
        int from;
        int cnt;

        public Point(int from, int cnt) {
            this.from = from;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Point o) {
            if (cnt == o.cnt) return Integer.compare(o.from, from);
            return Integer.compare(cnt, o.cnt);
        }
    }

    PriorityQueue<Point> pq = new PriorityQueue<>();
    int answer = 987654321;

    Set<String> set = new HashSet<>();


    // 길이가 5니까

    String[][] arr = new String[20000][5];
    int leng[] = new int[30];
    boolean check [] =new boolean[20000];

    public int solution(String[] strs, String t) {
        for (int i = 0; i < strs.length; i++) {
            String now = strs[i];
            char nowC = now.charAt(0);
            leng[nowC - 'a'] = Math.max(now.length(), leng[nowC - 'a']);
        }
        set.addAll(Arrays.asList(strs));
        int l = t.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < 5; j++) {
                if (i + j + 1 > l) continue;
                sb.append(t.charAt(i + j));
                arr[i][j] = sb.toString();
            }
            sb.delete(0, 5);
        }

        pq.offer(new Point(0, 0));
        while (!pq.isEmpty()) {
            Point now = pq.poll();
            if(check[now.from]) continue;
            if (now.from == l) {
                answer = now.cnt;
                break;
            }
            check[now.from]=true;
            // from 으로 시작하는 set의 단어 길이
            int ll = t.charAt(now.from) - 'a';
            for (int i = leng[ll] - 1; i >= 0; i--) {
                String ns = arr[now.from][i];
                if (ns == null) continue;
                if (set.contains(ns)) {
                    pq.offer(new Point(now.from + i + 1, now.cnt + 1));
                }
            }
        }
        // 2000000^5
        answer = answer == 987654321 ? -1 : answer;
        return answer;
    }


    @Override
    public void run() {
        System.out.println(solution(new String[]{"app", "ap", "p", "l", "e", "ple", "pp"}, "apple"));
    }
}
