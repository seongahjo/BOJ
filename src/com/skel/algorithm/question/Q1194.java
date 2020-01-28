package com.skel.algorithm.question;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Q1194 implements Question {
	static int N, M;
	static char map[][] = new char[51][51];

	static class Point implements Comparable<Point> {
		public int x;
		public int y;
		public int cnt;
		public int a = 0;
		public int b = 0;
		public int c = 0;
		public int d = 0;
		public int e = 0;
		public int f = 0;

		public Point(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

		public Point(int rx, int ry, Point next) {
			this.x = rx;
			this.y = ry;
			this.cnt = next.cnt + 1;
			this.a = next.a;
			this.b = next.b;
			this.c = next.c;
			this.d = next.d;
			this.e = next.e;
			this.f = next.f;
		}


		@Override
		public boolean equals(Object obj) {
			Point p = (Point) obj;
			return x == p.x && y == p.y;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(cnt, o.cnt);
		}
	}

	static int mv[][] = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	static boolean check[][][][][][][][] = new boolean[51][51][2][2][2][2][2][2];

	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		Point start = null;
		for (int i = 0; i < N; i++) {
			String s = sc.next();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == '0') {
					start = new Point(i, j, 0);
				}
			}
		}
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.offer(start);
		check[start.x][start.y][0][0][0][0][0][0] = true;
		while (!pq.isEmpty()) {
			Point now = pq.poll();
			if (map[now.x][now.y] == '1') {
				System.out.println(now.cnt);
				return;
			}
			for (int i = 0; i < 4; i++) {
				int rx = now.x + mv[i][0];
				int ry = now.y + mv[i][1];
				if (rx < 0 || rx >= N || ry < 0 || ry >= M || map[rx][ry] == '#') continue;
				if (check[rx][ry][now.a][now.b][now.c][now.d][now.e][now.f]) continue;
				if ((map[rx][ry] == 'A' && now.a != 1) || (map[rx][ry] == 'B' && now.b != 1) ||
						(map[rx][ry] == 'C' && now.c != 1) || (map[rx][ry] == 'D' && now.d != 1) ||
						(map[rx][ry] == 'E' && now.e != 1) || (map[rx][ry] == 'F' && now.f != 1)) continue;
				check[rx][ry][now.a][now.b][now.c][now.d][now.e][now.f] = true;
				Point next = new Point(rx, ry, now);
				if (map[rx][ry] == 'a') next.a = 1;
				if (map[rx][ry] == 'b') next.b = 1;
				if (map[rx][ry] == 'c') next.c = 1;
				if (map[rx][ry] == 'd') next.d = 1;
				if (map[rx][ry] == 'e') next.e = 1;
				if (map[rx][ry] == 'f') next.f = 1;
				pq.offer(next);
			}
		}
		System.out.println(-1);
	}
}
