package com.skel.algorithm.question;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class QKakao2020C implements Question {


    public int[][] rotate(int[][] original) {
        int N = original.length;
        int matrix[][] = new int[N][N];
        for (int i = 0; i < N; i++)
            matrix[i] = Arrays.copyOf(original[i], N);
        for (int i = 0; i < N / 2; i++) {
            for (int j = i; j < N - i - 1; j++) {
                int k = matrix[i][j];
                matrix[i][j] = matrix[N - 1 - j][i];
                matrix[N - 1 - j][i] = matrix[N - 1 - i][N - 1 - j];
                matrix[N - 1 - i][N - 1 - j] = matrix[j][N - 1 - i];
                matrix[j][N - 1 - i] = k;
            }
        }
        return matrix;
    }


    public boolean isSame(int[][] key, int[][] lock, int nowX, int nowY, int mvx, int mvy) {

        int n = lock.length;
        boolean[][] w = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                w[i][j] = lock[i][j] == 0;
            }
        }
        // 이러면 위치가 보존이 됨.
        //
        int m = key.length;
        //  M 을 기준으로 M보다 작으면 아래로 M보다 크면 위로
        // up ~ down
        // up이 1 이면 0 ~ down-1
        // 2면 0 ~ (down -2) [ 원래 2]
        // left ~ right

        // realIndex = fakeIndex + up
        // fakeIndex는 인덱스 , realIndex는 위치(값)
        int moveX = mvx - m;
        int moveY = mvy - m;
        // 새로운 인덱스 = 원래 인덱스 + up
        for (int ki = 0; ki < m; ki++) {
            for (int kj = 0; kj < m; kj++) {
                int realX = ki + moveX;
                int realY = kj + moveY;
                if(realX < 0 || realX >=m || realY <0 || realY>=m) continue;
                if (key[realX][realY] == lock[nowX + ki][nowY + kj]) return false;
                if (lock[nowX + ki][nowY + kj] == 0) w[nowX + ki][nowY + kj] = false;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (w[i][j]) return false;
            }
        }

        return true;
    }

    class Point {
        int x;
        int y;


        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    boolean check[][] = new boolean[40][40];


    int mv[][] = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public boolean solution(int[][] key, int[][] lock) {
        int M = key.length;
        // 3번 회전 후 움직이고
        int N = lock.length;
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(M, M));
        int rotateMap[][][] = new int[4][M][M];
        rotateMap[0] = key;
        for (int i = 1; i < 4; i++) {
            rotateMap[i] = rotate(rotateMap[i - 1]);
        }
        while (!q.isEmpty()) {
            Point now = q.poll();
            for (int i = 0; i < 4; i++) {
                int rx = now.x + mv[i][0];
                int ry = now.y + mv[i][1];
                // M 을 기준
                if (rx < 0 || rx >= 2 * M || ry < 0 || ry >= 2 * M || check[rx][ry]) continue;
                check[rx][ry] = true;
                q.offer(new Point(rx, ry));
            }
            // 오른쪽으로 갔을 때 왼쪽의 공백을 나타내면 될듯?
            // 항상 3x3을 보장하기
            for (int i = 0; i < 4; i++) {
                for (int li = 0; li + M <= N; li++) {
                    for (int lj = 0; lj + M <= N; lj++) {
                        if (isSame(rotateMap[i], lock, li, lj, now.x, now.y))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    //
    @Override
    public void run() {
        System.out.println(solution(new int[][]{{0, 1, 0}, {1, 0, 0}, {0, 0, 0}}, new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}));
    }
}
