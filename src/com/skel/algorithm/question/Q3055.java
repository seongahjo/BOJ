package com.skel.algorithm.question;

import java.util.*;

public class Q3055 implements Question {

    static class Point implements Comparable<Point> {
        int x;
        int y;
        int cnt;

        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(cnt, o.cnt);
        }
    }


    static void move() {
        Queue<Point> list = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[0][i][j] == '*')
                    list.offer(new Point(i, j, 0)); // 여기에서 나는거같다...
            }
        }

        while (!list.isEmpty()) {
            Point now = list.poll();
            for (int i = 0; i < 4; i++) {
                int rx = now.x + mv[i][0];
                int ry = now.y + mv[i][1];
                if (now.cnt > 2499 || rx < 0 || rx >= R || ry < 0 || ry >= C || map[now.cnt + 1][rx][ry] == 'X'
                        || map[now.cnt + 1][rx][ry] == 'D' || map[now.cnt + 1][rx][ry] == '*') continue;
                map[now.cnt + 1][now.x][now.y] = '*';
                map[now.cnt + 1][rx][ry] = '*';
                list.add(new Point(rx, ry, now.cnt + 1));
            }
        }
    }

    static int mv[][] = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static char map[][][] = new char[2501][51][51]; // 0 ~ 50
    static int R, C;

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        boolean chk[][][] = new boolean[2501][51][51];
        PriorityQueue<Point> pq = new PriorityQueue<>();
        for (int i = 0; i < R; i++) {
            String s = sc.next();
            for (int j = 0; j < C; j++) {
                map[0][i][j] = s.charAt(j);
                if (map[0][i][j] == 'S')
                    pq.offer(new Point(i, j, 0));
            }
        }

        for (int i = 1; i < 2501; i++) {
            for (int j = 0; j < 51; j++) {
                for (int h = 0; h < 51; h++) {
                    map[i][j][h] = map[0][j][h];
                }
            }
        }

        move();

        while (!pq.isEmpty()) {
            Point now = pq.poll();
            if (map[now.cnt][now.x][now.y] == 'D') {
                System.out.println(now.cnt);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int rx = now.x + mv[i][0];
                int ry = now.y + mv[i][1];
                if (now.cnt > 2499 || rx >= R || rx < 0 || ry >= C || ry < 0
                        || map[now.cnt + 1][rx][ry] == '*'
                        || map[now.cnt + 1][rx][ry] == 'X'
                        || chk[now.cnt + 1][rx][ry]) continue;
                chk[now.cnt + 1][rx][ry] = true;
                pq.offer(new Point(rx, ry, now.cnt + 1));

            }

        }

        System.out.println("KAKTUS");
    }


}
