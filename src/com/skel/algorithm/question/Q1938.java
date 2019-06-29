package com.skel.algorithm.question;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Q1938 implements Question {
    static int N;
    static char map[][] = new char[51][51];
    static int mv[][] = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
    static boolean check[][][] = new boolean[51][51][2];

    static class Point {
        int x;
        int y;
        int dir;
        int cnt;


        public Point(int x, int y, int dir, int cnt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 1; i <= N; i++) {
            String s = sc.next();
            for (int j = 1; j <= N; j++) {
                map[i][j] = s.charAt(j - 1);
            }
        }


        Point B = null;
        Point E = null;
        // dir 0 세로, 1 가로
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i >= 2 && i <= N - 1) {
                    if (map[i - 1][j] == 'B' && map[i][j] == 'B' && map[i + 1][j] == 'B') {
                        B = new Point(i, j, 0, 0);
                        //세로
                    }
                    if (map[i - 1][j] == 'E' && map[i][j] == 'E' && map[i + 1][j] == 'E') {
                        E = new Point(i, j, 0, 0);
                    }
                }
                if (j >= 2 && j <= N - 1) {
                    if (map[i][j - 1] == 'B' && map[i][j] == 'B' && map[i][j + 1] == 'B') {
                        B = new Point(i, j, 1, 0);
                        // 가로
                    }
                    if (map[i][j - 1] == 'E' && map[i][j] == 'E' && map[i][j + 1] == 'E') {
                        E = new Point(i, j, 1, 0);
                    }
                }
            }
        }

        Queue<Point> q = new LinkedList<>();
        q.offer(B);
        check[B.x][B.y][B.dir] = true;
        while (!q.isEmpty()) {
            Point now = q.poll();
            if (now.x == E.x && now.y == E.y && now.dir == E.dir) {
                System.out.println(now.cnt);
                return;
            }

            for (int i = 0; i < 4; i++) {
                // 0 은 세로
                int rx = now.x + mv[i][0];
                int ry = now.y + mv[i][1];
                if (now.dir == 0 && !(rx - 1 >= 1 && rx + 1 <= N && ry >= 1 && ry <= N && map[rx][ry] != '1' && map[rx - 1][ry] != '1' && map[rx + 1][ry] != '1'))
                    continue;
                else if (now.dir == 1 && !(ry - 1 >= 1 && ry + 1 <= N && rx >= 1 && rx <= N && map[rx][ry] != '1' && map[rx][ry - 1] != '1' && map[rx][ry + 1] != '1'))
                    continue;
                if (check[rx][ry][now.dir]) continue;
                check[rx][ry][now.dir] = true;
                q.offer(new Point(rx, ry, now.dir, now.cnt + 1));
            }

            boolean found = false;
            if (now.x >= 2 && now.x <= N - 1 && now.y >= 2 && now.y <= N - 1) {
                for (int i = -1; i <= 1; i++) {
                    int rx = now.x + i;
                    for (int j = -1; j <= 1; j++) {
                        int ry = now.y + j;
                        if (map[rx][ry] == '1') found = true;
                    }
                }

                int inverseDir = now.dir == 1 ? 0 : 1;
                if (!found && !check[now.x][now.y][inverseDir]) {
                    check[now.x][now.y][inverseDir] = true;
                    q.offer(new Point(now.x, now.y, inverseDir, now.cnt + 1));
                }

            }
        }

        System.out.println(0);

    }
}
