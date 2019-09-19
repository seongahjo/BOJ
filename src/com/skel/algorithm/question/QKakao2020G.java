package com.skel.algorithm.question;

import java.util.PriorityQueue;

public class QKakao2020G implements Question {

    class Point implements Comparable<Point>{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(Point p) {
            this.x = p.x;
            this.y = p.y;

        }

        public boolean move(int dir) {
            x += mv[dir][0];
            y += mv[dir][1];
            if (!isMoveable()) return false;
            return true;
        }

        public boolean isEnd() {
            return x == N-1 && y == N-1;
        }

        public boolean isMoveable() {
            if (x >= N || y >= N || x < 0 || y < 0 || map[x][y] == 1) return false;
            return true;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(x,o.x);
        }
    }

    int N;

    int mv[][] = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};


    boolean isRepeat[][][][] = new boolean[101][101][101][101];

    class Union {
        Point p1;
        Point p2;

        public Union(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        public boolean isRepeat() {
            Point pp1 = p1.compareTo(p2) >= 0 ? p1 : p2;
            Point pp2 = p1.compareTo(p2) >= 0 ? p2 : p1;
            if (isRepeat[pp1.x][pp1.y][pp2.x][pp2.y]) return true;
            return false;
        }

        public Union turn(int change, int dir) {
            // 8가지?
            // 1 4
            // 2 4
            Point pp1;
            Point pp2;
            if (change < 1) {
                pp1 = new Point(p1);
                pp2 = new Point(p2);
            } else {
                pp1 = new Point(p2);
                pp2 = new Point(p1);
            }
            boolean linear = pp1.x == pp2.x;
            // pp2 가 무조건 움직임
            if (linear) {
                //pp2가 pp1.y가 같아짐
                int nextX = dir == 0 ? pp1.x + 1 : pp1.x - 1;
                // 0 이면 위로
                // 1 이면 아래로
                if (new Point(nextX, pp2.y).isMoveable()) {
                    pp2.y = pp1.y;
                    pp2.x = nextX;
                } else {
                    return null;
                }
            } else {
                //  세로 x가 움직임
                int nextY = dir == 0 ? pp1.y + 1 : pp1.y - 1;
                if (new Point(pp2.x, nextY).isMoveable()) {
                    pp2.x = pp1.x;
                    pp2.y = nextY;
                } else {
                    return null;
                }

            }
            return new Union(pp1, pp2);
        }

        public boolean isMoveable() {
            return p1.isMoveable() && p2.isMoveable();
        }

        public void check() {
            Point pp1 = p1.compareTo(p2) >= 0 ? p1 : p2;
            Point pp2 = p1.compareTo(p2) >= 0 ? p2 : p1;
            isRepeat[pp1.x][pp1.y][pp2.x][pp2.y] = true;
        }


        public boolean isEnd() {
            return p1.isEnd() || p2.isEnd();
        }

        public Union move(int dir) {
            Point pp1 = new Point(p1);
            Point pp2 = new Point(p2);
            pp1.move(dir);
            pp2.move(dir);

            Union newUnion =  new Union(pp1, pp2);
            if(!newUnion.isMoveable()) return null;
            return newUnion;
        }
    }

    class Robot implements Comparable<Robot> {
        Union u;
        int cnt = 0;


        public boolean isRepeat() {
            return u.isRepeat();
        }

        public Robot() {
            u = new Union(new Point(0, 0), new Point(0, 1));
        }

        public boolean isMoveable() {
            return u.isMoveable();
        }

        public Robot(Union u, int cnt) {
            this.u = u;
            this.cnt = cnt;
        }


        public Robot turn(int change, int dir) {
            Union uNext = u.turn(change, dir);
            if (uNext == null) return null;
            return new Robot(uNext, this.cnt + 1);
        }

        public void check() {
            u.check();
        }

        public Robot move(int dir) {
            Union nextUnion =u.move(dir);
            if(nextUnion == null) return null;
            return new Robot(nextUnion, this.cnt + 1);
        }

        public boolean isEnd() {
            return u.isEnd();
        }

        @Override
        public int compareTo(Robot o) {
            return Integer.compare(cnt, o.cnt);
        }
    }

    int map[][];


    public int solution(int[][] board) {
        map = board;
        N = board.length;
        Robot robot = new Robot();
        PriorityQueue<Robot> pq = new PriorityQueue<>();

        pq.offer(robot);
        int answer = 0;
        robot.check();
        while (!pq.isEmpty()) {
            Robot now = pq.poll();
            if (now.isEnd()) {
                return now.cnt;
            }

            for (int i = 0; i < 4; i++) {
                Robot nextRobot = now.move(i);
                if (nextRobot == null || !nextRobot.isMoveable() || nextRobot.isRepeat()) continue;
                nextRobot.check();
                pq.offer(nextRobot);
            }
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    Robot nextRobot = now.turn(i, j);
                    if (nextRobot == null || !nextRobot.isMoveable() || nextRobot.isRepeat()) continue;
                    nextRobot.check();
                    pq.offer(nextRobot);
                }
            }
        }
        return answer;
    }


    @Override
    public void run() {
        System.out.println(solution(new int[][]{{0, 0, 0, 1, 1}, {0, 0, 0, 1, 0}, {0, 1, 0, 1, 1}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}}));
    }
}
