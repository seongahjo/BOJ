package com.skel.algorithm.question;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Q2638 implements Question {
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M;
    static int map[][];
    static int mv[][] = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    static boolean chk[][];
    static int newMap[][];
    static int cnt = 0;

    static boolean check(){
        for(int i=0; i<N;i++){
            for(int j=0; j<M;j++){
                if(map[i][j]==1) return false;
            }
        }
        return true;
    }

    static boolean run(Queue<Point> q) {
        if(check()) return true;
        chk = new boolean[101][101];
        newMap = new int[101][101];
        while (!q.isEmpty()) {
            Point now = q.poll();
            chk[now.x][now.y] = true;
            for (int i = 0; i < 4; i++) {
                int rx = now.x + mv[i][0];
                int ry = now.y + mv[i][1];
                if (rx >= N || rx < 0 || ry >= M || ry < 0) continue;
                if (map[now.x][now.y] == 0 && map[rx][ry] == 1) newMap[rx][ry]++;
                if (newMap[rx][ry] >= 2) {
                    map[rx][ry] = 0;
                    newMap[rx][ry] = 0;
                }
                if (chk[rx][ry]) continue;
                chk[rx][ry]=true;
                if (map[rx][ry] == 0)
                    q.offer(new Point(rx, ry));
            }
        }
        cnt++;
        return false;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[101][101];



        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0, 0));

        while(!run(q)){
            q.offer(new Point(0,0));
        }
        System.out.println(cnt);

    }
}
