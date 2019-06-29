package com.skel.algorithm.question;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Q9376 implements Question {
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

    static int move( Point start, Point end){
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.offer(start);
        int cnt = 987654321;
        Point dest = end;
        while (!pq.isEmpty()) {
            Point now = pq.poll();
            if(map[now.x][now.y]=='#'){
                go[now.x][now.y]=true;
            }
            if (now.x == dest.x && now.y == dest.y) {
                cnt = now.cnt + (map[start.x][start.y]=='#' ? 1 : 0);
                return cnt;
            }
            for (int i = 0; i < 4; i++) {
                int rx = now.x + mv[i][0];
                int ry = now.y + mv[i][1];
                if (rx < 0 || rx >= h || ry < 0 || ry >= w || map[rx][ry] == '*' || chk[rx][ry]) continue;
                chk[rx][ry] = true;
                int nextCnt = now.cnt;
                if (map[rx][ry] == '#' && !go[rx][ry])
                    nextCnt++;
                pq.offer(new Point(rx, ry, nextCnt));
            }
        }
        return cnt;
    }

    static int move(Point start, List<Point> end) {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.offer(start);
        chk = new boolean[101][101];
        int firstCnt = move(start,end.get(0));
        chk = new boolean[101][101];
        int secondCnt = move(start,end.get(1));

        return firstCnt==987654321 || secondCnt == 987654321 ? 987654321 : firstCnt+secondCnt;
    }

    static int h, w;
    static boolean chk[][];
    static boolean go[][];
    static int mv[][] = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
    static char map[][];

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            h = sc.nextInt();
            w = sc.nextInt();
            map = new char[101][101];
            chk = new boolean[101][101];
            go = new boolean[101][101];

            List<Point> startList = new ArrayList<>();
            List<Point> endList = new ArrayList<>();
            for (int i = 0; i < h; i++) {
                String s = sc.next();
                for (int j = 0; j < w; j++) {
                    map[i][j] = s.charAt(j);
                    if ((i == 0 || i == h - 1 || j == 0 || j == w - 1) && (map[i][j] == '#' || map[i][j] == '.'))
                        startList.add(new Point(i, j, 0));
                    if (map[i][j] == '$')
                        endList.add(new Point(i, j, 0));
                }
            }
            int result = 987654321;
            for(Point p : startList){
                go = new boolean[101][101];
                result=Math.min(result,move(p,endList));
            }
            System.out.println(result);

        }
    }
}
