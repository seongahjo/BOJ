package com.skel.algorithm.question;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class QP49191 implements Question {
    // result A -> B A가 B를 이김 ㅎㅎ


    public int solution(int n, int[][] results) {
        boolean isWin[][] = new boolean[n + 1][n + 1];
        boolean isLose[][] = new boolean[n + 1][n + 1];
        int l = results.length;
        int answer = 0;
        for (int i = 0; i < l; i++) {
            isWin[results[i][0]][results[i][1]] = true;
            isLose[results[i][1]][results[i][0]] = true;
        }
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if(isWin[i][k] && isWin[k][j])
                    isWin[i][j] = isWin[i][k] && isWin[k][j];
                    if(isLose[i][k] && isLose[k][j])
                    isLose[i][j] = isLose[i][k] && isLose[k][j];
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            boolean isFound = false;
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                if (!isWin[i][j] && !isLose[i][j]) {
                    isFound = true;
                    break;
                }
            }
            if (!isFound) answer++;
        }



        return answer;
    }


    @Override
    public void run() {
        System.out.println(solution(5, new int[][]{{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}}));
        System.out.println(solution(6, new int[][]{{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}, {5, 6}}));
        System.out.println(solution(3, new int[][]{{1, 2}, {1, 3}}));
    }
}
