package com.skel.algorithm.question;

import java.util.*;

public class Q2019Naver2 implements Question {
    Map<Character, Integer> table = new HashMap<>(); // character , index

    public int[] solution(String[] maps) {
        int l = maps.length;
        int l2 = maps[0].length();
        for (int i = 0; i < l; i++) {
            String s = maps[i];
            for (int j = 0; j < l2; j++) {
                if (s.charAt(j) != '.' && table.get(s.charAt(j)) == null)
                    table.put(s.charAt(j), table.size());
            }
        }
        int alphaLength = table.size();
        boolean[][] check = new boolean[alphaLength][alphaLength];
        for (int i = 0; i < l; i++) {
            String s = maps[i];
            for (int j = 0; j < l2; j++) {
                char now = s.charAt(j);
                if (now == '.') continue;
                if (i + 1 < l) {
                    char comp = maps[i + 1].charAt(j);
                    toggle(check, now, comp);
                }
                if (j + 1 < l2) {
                    char comp = s.charAt(j + 1);
                    toggle(check, now, comp);
                }
            }
        }
        int[] answer = new int[2];
        int localMax = 0;
        int totalMax = 0;
        for (int i = 0; i < alphaLength; i++) {
            for (int j = i + 1; j < alphaLength; j++) {
                if (check[i][j]) {
                    totalMax++;
                }
            }
        }
        for (int i = 0; i < alphaLength; i++) {
            int local = 0;
            for (int j = 0; j < alphaLength; j++) {
                if (i == j) continue;
                if (check[i][j]) {
                    localMax = Math.max(localMax, ++local);
                }
            }
        }
        answer[0] = totalMax;
        answer[1] = localMax;

        return answer;
    }

    private void toggle(boolean[][] check, char now, char comp) {
        if (comp != '.') {
            int indexOne = table.get(now);
            int indexTwo = table.get(comp);
            check[indexOne][indexTwo] = true;
            check[indexTwo][indexOne] = true;
        }
    }

    @Override
    public void run() {
        solution(new String[]{"..........", "AAACC.....", ".AAA.....Z", "..AAAA..C.", "...BBBBB..", "....BBB...", "...ZBBB...", "ZZZZAAAC..", ".....CCCC.", "QQ......C.", ".........."});
    }
}
