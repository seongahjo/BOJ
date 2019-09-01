package com.skel.algorithm.question;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Q2017SummerC implements Question {
    class Point implements Comparable<Point> {
        int now;
        int time;

        public Point(int now, int time) {
            this.now = now;
            this.time = time;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(time, o.time);
        }
    }

    public int solution(int N, int[][] road, int K) {
        int cost[][] = new int[N][N];
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.offer(new Point(0, 0));
        boolean check[] = new boolean[N];
        for(int i=0; i<N;i++){
            Arrays.fill(cost[i],987654321);
        }
        for (int i = 0; i < road.length; i++) {
            cost[road[i][0] - 1][road[i][1] - 1] = Math.min(road[i][2], cost[road[i][0] - 1][road[i][1] - 1]);
            cost[road[i][1] - 1][road[i][0] - 1] = Math.min(road[i][2], cost[road[i][0] - 1][road[i][1] - 1]);
        }
        while (!pq.isEmpty()) {
            Point p = pq.poll();
            if (check[p.now]) continue;
            check[p.now] = true;
            for (int i = 0; i < N; i++) {
                if (i == p.now) continue;
                int time = p.time + cost[p.now][i];
                if (check[i] || time > K || cost[p.now][i] == 0) continue;
                pq.offer(new Point(i, time));
            }
        }
        int answer = 0;
        for (int i = 0; i < N; i++) {
            if (check[i]) answer++;
        }


        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.

        return answer;
    }

    @Override
    public void run() {
        System.out.println(solution(5, new int[][]{{1, 2, 1}, {2, 3, 3}, {5, 2, 2}, {1, 4, 2}, {5, 3, 1}, {5, 4, 2}}, 3));
    }
}
