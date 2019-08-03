package com.skel.algorithm.question;

import java.util.PriorityQueue;

public class QKakao2018F implements Question {
    static int answer = 0;
    static char[][] newBoard;
    static int mv[][] = {{1, 0}, {0, 1}, {1, 1}};

    static class Point implements Comparable<Point> {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(x, o.x);
        }
    }

    static boolean isBreak = false;
    static PriorityQueue<Point> candidate = new PriorityQueue<>();

    public static void check(int x, int y, int m, int n) {
        char now = newBoard[y][x];
        if(now == ' ') return;
        for (int i = 0; i < 3; i++) {
            int rx = x + mv[i][0];
            int ry = y + mv[i][1];
            if (rx >= m || ry >= n || now != newBoard[ry][rx]) return;
        }
        isBreak=true;
        // 여긴 체크 완료된 곳
        candidate.offer(new Point(x, y));
        for (int i = 0; i < 3; i++)
            candidate.offer(new Point(x + mv[i][0], y + mv[i][1]));

    }

    static void change(char[] board, int from, int to) {
        char tmp = board[from];
        board[from] = board[to];
        board[to] = tmp;
    }

    // 30 x 30
    public int solution(int m, int n, String[] board) {
        newBoard = new char[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                newBoard[j][i] = board[i].charAt(j);
            }
        }
        // 블록의 모든 경우의 수를 체크
        do {
            isBreak = false;
            boolean isChange [][] =new boolean[n][m];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    check(i, j, m, n);
                }
            }

            while (!candidate.isEmpty()) {
                // 위에서 아래로 내리기
                Point p = candidate.poll();
                if(isChange[p.y][p.x]) continue;
                isChange[p.y][p.x] = true;
                newBoard[p.y][p.x] = ' ';
                for (int i = p.x; i >= 1; i--) {
                    change(newBoard[p.y], i, i - 1);
                }
                answer++;
                //바꾸기 x축 기준으로 하자.
            }
        } while (isBreak);

        return answer;

    }


    // 다시 설계
    // x축하고 y축 바꾸기
    @Override
    public void run() {
        System.out.println(solution(6, 6, new String[]{"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"}));
    }
}
