package com.skel.algorithm.question;

import java.util.PriorityQueue;

public class QPmaest implements Question {

    class Point implements Comparable<Point> {
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

    int check[][] = new int[102][102];
    int mv[][] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int solution(int[][] maps) {
        int answer = 0;
        int n = maps.length;
        int m = maps[0].length;

        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.offer(new Point(1, 1, 1));
        while (!pq.isEmpty()) {
            Point now = pq.poll();
            if (now.x == n && now.y == m) {
                answer = now.cnt;
                break;
            }

            check[now.x][now.y] = now.cnt;
            for (int i = 0; i < 4; i++) {
                int rx = now.x + mv[i][0];
                int ry = now.y + mv[i][1];
                if (rx < 1 || ry< 1 || rx > n || ry > m|| maps[rx-1][ry-1]==0 ||(check[rx][ry] != 0 && check[rx][ry] <= now.cnt + 1)) continue;
                check[rx][ry]=now.cnt+1;
                pq.offer(new Point(rx,ry,now.cnt+1));
            }
        }
        answer = answer == 0 ? -1 : answer;
        return answer;
    }

    // 201개

    @Override
    public void run() {
        System.out.println(solution(new int[][]{{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}}));
    }
}
