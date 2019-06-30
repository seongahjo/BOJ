package com.skel.algorithm.question;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Q15685 implements Question {
    static class Point {
        int startx;
        int starty;
        int endx;
        int endy;
        int dir;

        public Point(int startx, int starty, int endx, int endy, int dir) {
            this.startx = startx;
            this.starty = starty;
            this.endx = endx;
            this.endy = endy;
            this.dir = dir;
        }
    }

    static int mv[][] = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

    static int changeDir(int dir) {
        return dir == 3 ? 0 : dir + 1;
    }

    static boolean map[][] = new boolean[103][103];
    static int ans = 0;

    static ArrayDeque<Point> go(ArrayDeque<Point> before) {
        ArrayDeque<Point> stack = new ArrayDeque<>();
        stack.addAll(before);
        Point last = stack.getLast();
        int nowX, nowY;
        nowX = last.endx;
        nowY = last.endy;

        int size = before.size();
        for (int i = 0; i < size; i++) {
            Point left = before.pollLast();
            int nextDir = changeDir(left.dir);
            int nextX = nowX + mv[nextDir][0];
            int nextY = nowY + mv[nextDir][1];
            Point next = new Point(nowX, nowY, nextX, nextY, nextDir);
            stack.addLast(next);
            map[left.startx][left.starty] = true;
            map[left.endx][left.endy] = true;
            map[next.startx][next.starty] = true;
            map[next.endx][next.endy] = true;
            nowX = nextX;
            nowY = nextY;
        }
        return stack;
    }

    static ArrayDeque<Point> next(int startX, int startY, int dir, int g) {
        Point now = new Point(startX, startY, startX + mv[dir][0], startY + mv[dir][1], dir);
        ArrayDeque<Point> deque = new ArrayDeque<>();
        deque.add(now);
        map[now.startx][now.starty] = true;
        map[now.endx][now.endy] = true;
        for (int i = 0; i < g; i++) {
            deque = go(deque);
        }
        return deque;
    }

    static void check(int startX, int startY) {

        if (map[startX][startY] && map[startX + 1][startY] && map[startX][startY + 1] && map[startX + 1][startY + 1])
            ans++;
        if (map[startX][startY] && map[startX - 1][startY] && map[startX][startY + 1] && map[startX - 1][startY + 1])
            ans++;
        if (map[startX][startY] && map[startX + 1][startY] && map[startX][startY - 1] && map[startX + 1][startY - 1])
            ans++;
        if (map[startX][startY] && map[startX - 1][startY] && map[startX][startY - 1] && map[startX - 1][startY - 1])
            ans++;

    }

    static int N;

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int d = sc.nextInt();
            int g = sc.nextInt();
            ArrayDeque<Point> dq = next(y + 1, x + 1, d, g);
        }

        for (int i = 1; i <= 101; i++) {
            for (int j = 1; j <= 101; j++) {
                check(i, j);
            }
        }
        System.out.println(ans / 4);
    }
}

