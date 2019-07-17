package com.skel.algorithm.question;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Q15686 implements Question {
    static int N, M;
    static int map[][];
    static boolean chickenCheck[] = new boolean[13];
    static boolean homeCheck[] = new boolean[2500];

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static ArrayList<Point> homes = new ArrayList<>();
    static ArrayList<Point> chickens = new ArrayList<>();
    static ArrayList<Distance> distances = new ArrayList<>();

    static class Distance implements Comparable<Distance> {
        int home;
        int chicken;
        int dist;

        public Distance(int home, int chicken, int dist) {
            this.home = home;
            this.chicken = chicken;
            this.dist = dist;
        }

        @Override
        public int compareTo(Distance o) {
            return Integer.compare(dist, o.dist);
        }
    }

    static int chickenLength;

    static int chickenCost() {
        int cost = 0;
        homeCheck = new boolean[2500];
        PriorityQueue<Distance> dpq = new PriorityQueue<>(distances);
        while (!dpq.isEmpty()) {
            Distance d = dpq.poll();
            if (!chickenCheck[d.chicken] || homeCheck[d.home]) continue;
            cost += d.dist;
            homeCheck[d.home]=true;
        }
        return cost;
    }

    static int result = 987654321;

    static void chickenRun(int n, int pos) {
        if (n >= M) return;
        chickenCheck[pos] = true;
        result = Math.min(result, chickenCost());
        for (int i = pos + 1; i < chickenLength; i++) {
            chickenRun(n + 1, i);
        }
        chickenCheck[pos] = false;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 1) {
                    homes.add(new Point(i, j));
                } else if (map[i][j] == 2) {
                    chickens.add(new Point(i, j));
                }
            }
        }

        chickenLength = chickens.size();

        for (int i = 0; i < homes.size(); i++) {
            Point home = homes.get(i);
            for (int j = 0; j < chickenLength; j++) {
                Point chicken = chickens.get(j);
                distances.add(new Distance(i, j, Math.abs(home.x - chicken.x) + Math.abs(home.y - chicken.y)));
            }
        }

        for (int i = 0; i < chickenLength; i++)
            chickenRun(0, i);
        System.out.println(result);

    }
}
