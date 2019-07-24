package com.skel.algorithm.question;


import java.util.*;
import java.util.stream.IntStream;

public class QKakao2019B implements Question {

    public int solution(String[][] relation) {

        int answer = 0;
        return answer;
    }


    public static void name(String[][] relation) {

        List<Integer> list = new LinkedList<>();
        int row = relation.length;
        int col = relation[0].length;


        FuncPrioity pri = (x) -> {
            for (int i = col - 1; i >= 0; i--) {
                int val = 1 << i;
                if ((x & i) == i)
                return i;
            }
            return 0;
        };

        /*****************
         *  후보키 찾기
         *
         *  튜플을 모두 비교한다. (like 서로 번갈아 악수하는 경우)
         *
         *****************/
        FuncCandidate rda = (r, c, comb) -> {

            for (int i = 0; i < r - 1; i++) {
                for (int j = i + 1; j < r; j++) {
                    boolean isSame = true;

                    for (int k = 0; k < c; k++) {

                        if ((comb & 1 << k) == 0) continue; // 해당 attribute가 슈퍼키에 포함되있음.
                        if (!relation[i][k].equals(relation[j][k])) {
                            isSame = false;
                            break;
                        }
                    }

                    if (isSame) return false; // 같으므로 후보키 아님
                }
            }
            return true;
        };


        IntStream
                .range(1, 1 << col)
                .forEach(i -> {

                    if (rda.isCandidate(row, col, i)) {
                        list.add(i);
                    }
                });

        list.sort((x, y) -> {
            int prix = pri.prio(x);
            int priy = pri.prio(y);
            return Integer.compare(prix, priy);
        });

        int ans = 0;
        while (!list.isEmpty()) {

            int first = list.remove(0);
            ans++;

            list.removeIf(i -> (first & i) == first);
        } // 유일성을 위해서 조합이 만들어질경우 제거한다.
        System.out.println(ans);
    }

    // 비트 연산자....

    //경우의 수 어차피
    // 8개 요소가 있으면
    // 0 ~ 2^8-1이 모든 경우의수

    // 비트 연산자..

    @FunctionalInterface
    interface FuncCandidate {
        boolean isCandidate(int row, int col, int combi);
    }

    @FunctionalInterface
    interface FuncPrioity {
        int prio(int x);
    }


    @Override
    public void run() {
        solution(new String[][]{{"100", "ryan", "music", "2"}, {"200", "apeach", "math", "2"}, {"300", "tube", "computer", "3"}, {"400", "con", "computer", "4"}, {"500", "muzi", "music", "3"}, {"600", "apeach", "music", "2"}});

    }
}
