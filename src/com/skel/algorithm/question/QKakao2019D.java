package com.skel.algorithm.question;

import java.util.LinkedList;
import java.util.Queue;

public class QKakao2019D implements Question {


    public int checkBoard[][][] = {
            {{0, 0, 1}, {1, 1, 1}},
            {{1, 0, 0}, {1, 1, 1}},
            {{1, 0}, {1, 0}, {1, 1}},
            {{0, 1}, {0, 1}, {1, 1}},
            {{0, 1, 0}, {1, 1, 1}}
    };

    public boolean isBoard(int[][] board, int x, int y, int[][] shape) {
        int l = shape.length;
        int num = -1;
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                int rx = i + x;
                int ry = j + y;
                if (0 > rx || rx >= N || 0 > ry || ry >= N) return false;
                if ((shape[i][j] == 0 && board[rx][ry] != 0)) return false;
                if (shape[i][j] == 1 ) {
                    if(board[rx][ry] == 0 ) return false;
                    if (num == -1) {
                        num = board[rx][ry];
                        continue;
                    } else if ((num != board[rx][ry]) && num != -1)
                        return false;
                }
            }
        }
        return true;
    }

    public int N;

    public int check(int[][] board, int x, int y) {
        for (int i = 0; i < checkBoard.length; i++) {
            if (isBoard(board, x, y, checkBoard[i])) {
                return i;
            }
        }
        return -1;
    }

    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public boolean isFound = false;
    public int answer = 0;

    public int solution(int[][] board) {
        N = board.length;
        do {
            isFound = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int val = check(board, i, j);


                    if (val != -1) {
                        int nowCheckboard[][] = checkBoard[val];
                        int l = nowCheckboard.length;
                        Queue<Point> q = new LinkedList<>();
                        for (int h = 0; h < l; h++) {
                            for (int k = 0; k < nowCheckboard[h].length; k++) {
                                if (nowCheckboard[h][k] == 0) {
                                    q.offer(new Point(i + h, j + k));
                                }
                            }
                        }
                        boolean isFalse = false;
                        while (!q.isEmpty()) {
                            Point now = q.poll();
                            int nowX = now.x;
                            int nowY = now.y;

                            for (int x = nowX; x >= 0; x--) {
                                if (board[x][nowY] != 0) {
                                    isFalse = true;
                                }
                            }
                            if (isFalse) break;
                        }

                        if (!isFalse) {
                            for (int h = 0; h < l; h++)
                                for (int k = 0; k < nowCheckboard[h].length; k++)
                                    board[i + h][j + k] = 0;
                            answer++;
                            isFound = true;
                        }

                    } // val 끝


                }
            }
        } while (isFound);

        return answer;
    }

// 1. 떨어트릴 후보를 찾기
    // 1-1 모양 찾기
// 2. 떨어트리기 (반복)

    // 위에서 떨어짐
    // 네모를 판별
    // 가능한게
    // 1-3, 1-4
    // 2-2, 2-3
    // 3-1

    @Override
    public void run() {


        System.out.println(solution(
                new int[][]{
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                        {0, 0, 0, 0, 0, 4, 4, 0, 0, 0},
                        {0, 0, 0, 0, 3, 0, 4, 0, 0, 0},
                        {0, 0, 0, 2, 3, 0, 0, 0, 5, 5},
                        {1, 2, 2, 2, 3, 3, 0, 0, 0, 5},
                        {1, 1, 1, 0, 0, 0, 0, 0, 0, 5}
                }));

    }
}
