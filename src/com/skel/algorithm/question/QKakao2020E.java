package com.skel.algorithm.question;

import java.util.Arrays;
import java.util.PriorityQueue;

public class QKakao2020E implements Question {

    boolean column[][];
    boolean row[][];
    int gN;


    public boolean check(int x, int y, int kind) {
        if (checkColumn(x, y)) return false; //column
        if (kind == 0) return column[x][y];
        else return row[x][y];
    }

    public boolean checkColumn(int x, int y) {
        return (x < 0 || x > gN || y < 0 || y > gN);
    }

    public void build(int x, int y, int kind) {
        if (kind == 0) {
            // 기둥
            if (x == 0) {
                column[x][y] = true;
                return;
            }
            if (check(x, y, 1) || check(x, y - 1, 1)) {
                    column[x][y] = true;
                return;
            }
            if (check(x - 1, y, 0)) {
                    column[x][y] = true;
                return;
            }
        } else {
            // 보
            if (check(x, y + 1, 1) && check(x, y - 1, 1)) {
                    row[x][y] = true;
                return;
            }
            if (check(x - 1, y, 0) || check(x - 1, y + 1, 0)) {
                    row[x][y] = true;
                return;
            }
        }
    }

    public void delete(int x, int y, int kind) {
        if (kind == 0) {
            column[x][y] = false;
        } else
            row[x][y] = false;
        boolean isBad = false;
        for (int i = 1; i <= gN; i++) {
            for (int j = 0; j <= gN; j++) {
                if (column[i][j]) {
                    if (check(i - 1, j, 0)) continue;
                    if (check(i, j, 1) || check(i, j - 1, 1)) continue;

                    isBad = true;
                    break;
                }
                if (row[i][j]) {
                    if (check(i - 1, j, 0) || check(i - 1, j + 1, 0)) continue;
                    if (check(i, j + 1, 1) && check(i, j - 1, 1)) continue;

                    isBad=true;
                    break;
                }
            }
            if (isBad) break;
        }
        if (isBad) {
            if (kind == 0)
                column[x][y] = true;
            else
                row[x][y] = true;
        }
    }

    class Point implements Comparable<Point> {
        int x;
        int y;
        int kind;

        public Point(int x, int y, int kind) {
            this.x = x;
            this.y = y;
            this.kind = kind;
        }

        @Override
        public int compareTo(Point o) {
            if (o.x == x && y == o.y) return Integer.compare(kind, o.kind);
            if (x == o.x) return Integer.compare(y, o.y);
            return Integer.compare(x, o.x);
        }
    }
 // 1~N
    // ~N
    public int[][] solution(int n, int[][] build_frame) {
        gN = n;
        column = new boolean[n+1][n + 1]; // 위아래
        row = new boolean[n + 1][n+1]; // 왼오른
        for (int[] f : build_frame) {
            int x = f[1];
            int y = f[0];
            int kind = f[2]; // 0 기둥 1 보
            int build = f[3];
            if (build == 1) {
                build(x, y, kind);
            } else {
                delete(x, y, kind);
            }
        }
        PriorityQueue<Point> pq = new PriorityQueue<>();
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (column[i][j])
                    pq.offer(new Point(j, i, 0));
                if (row[i][j])
                    pq.offer(new Point(j, i, 1));
            }
        }
        int[][] answer = new int[pq.size()][3];
        int index = 0;
        while (!pq.isEmpty()) {
            Point now = pq.poll();
            answer[index][0] = now.x;
            answer[index][1] = now.y;
            answer[index][2] = now.kind;
            index++;
//            System.out.printf("%d %d %d \n", now.x, now.y, now.kind);
        }

        return answer;
    }

    @Override
    public void run() {
        System.out.println(Arrays.toString(solution(5, new int[][]{{0, 0, 0, 1}, {2, 0, 0, 1}, {4, 0, 0, 1}, {0, 1, 1, 1}, {1, 1, 1, 1},
                {2, 1, 1, 1}, {3, 1, 1, 1}, {2, 0, 0, 0}, {1, 1, 1, 0}, {2, 2, 0, 1}})));
    }
}
