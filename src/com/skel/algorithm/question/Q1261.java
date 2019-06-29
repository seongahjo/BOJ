package com.skel.algorithm.question;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Q1261 implements Question {


    static class Point implements Comparable<Point> {
        int x;
        int y;
        int cnt;

        Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(cnt, o.cnt);
        }
    }

    @Override
    public void run() {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        Scanner sc = new Scanner(System.in);
        int mv[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int M = sc.nextInt();
        int N = sc.nextInt();
        int arr[][] = new int[101][101];
        int DP[][] = new int[101][101];
        for (int i = 1; i <= N; i++)
            Arrays.fill(DP[i], -1);

        for (int i = 1; i <= N; i++) {
            String s = sc.next();
            for (int j = 1; j <= M; j++) {
                arr[i][j] = s.charAt(j - 1) - '0';
            }
        }
        pq.offer(new Point(1, 1, 0));
        while (!pq.isEmpty()) {
            Point now = pq.poll();
            if (now.x == N && now.y == M) {
                System.out.println(now.cnt);
                break;
            }
            for (int i = 0; i < 4; i++) {
                int rx = now.x + mv[i][0];
                int ry = now.y + mv[i][1];
                if (rx <= 0 || ry <= 0 || rx > N || ry > M) continue;
                if (DP[rx][ry] == -1) {
                    DP[rx][ry] = now.cnt + arr[rx][ry];
                    pq.offer(new Point(rx, ry, now.cnt + arr[rx][ry]));
                }
            }
        }
    }
}
