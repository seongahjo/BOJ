package com.skel.algorithm.question;

import java.util.*;

public class Q3190 implements Question {
    static int N, K;
    static int map[][] = new int[101][101];
    // 0 빈
    // 1 사과
    // 2 L
    // 3 C

    static int mv[][] = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    // dir 0 L -> 1
    // dir 0 R -> 3
    static class Position {
        int x;
        int y;
        int dir;

        public Position(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return x == position.x &&
                    y == position.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }


    static Map<Integer, Integer> timeMap = new HashMap<>();

    // 0 오 , 1 위 2 왼 3 아래
    public static int nextDir(int nowDir, int item) {
        if (item == 2) {
            return nowDir + 1 == 4 ? 0 : nowDir + 1;
        }
        return nowDir == 0 ? 3 : nowDir - 1;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        for (int i = 0; i < K; i++)
            map[sc.nextInt()][sc.nextInt()] = 1;

        int num = sc.nextInt();
        for (int i = 0; i < num; i++)
            timeMap.put(sc.nextInt(), sc.next().charAt(0) == 'L' ? 2 : 3);

        Queue<Position> snake = new LinkedList<>();

        int time = 1;

        snake.add(new Position(1, 1, 0));

        while (true) {
            Queue<Position> nextSnake = new LinkedList<>();
            Position now = snake.peek();

            int rx = now.x + mv[now.dir][0];
            int ry = now.y + mv[now.dir][1];

            if (rx <= 0 || rx > N || ry <= 0 || ry > N || snake.contains(new Position(rx, ry, now.dir))) {
                System.out.println(time);
                return;
            }

            int nextDir = now.dir;
            Integer get = timeMap.get(time);

            if (get != null)
                nextDir = nextDir(now.dir, get);

            nextSnake.offer(new Position(rx, ry, nextDir));
            nextSnake.addAll(snake);

            if (map[rx][ry] == 1) {
                map[rx][ry] = 0;
            } else
                 ((LinkedList<Position>) nextSnake).removeLast();

            snake = nextSnake;
            time++;
        }
    }
}
