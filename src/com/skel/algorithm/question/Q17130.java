package com.skel.algorithm.question;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Q17130 implements Question {

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static char map[][] = new char[1001][1001];
	static int mv[][] = {{1, 1}, {0, 1}, {-1, 1}};
	static int dp[][] = new int[1001][1001];

	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		Point start = null;
		int maxM = 0;
		for (int i = 0; i < N; i++) {
			String s = sc.next();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'R') {
					start = new Point(i, j);
				}
				if (map[i][j] == 'O')
					maxM = Math.max(j, maxM);
			}
		}
		Queue<Point> q = new LinkedList<>();
		q.offer(start);

		int max = -1;
		for (int i = 0; i < 1001; i++) {
			Arrays.fill(dp[i], -1);
		}
		dp[start.x][start.y] = 0;
		while (!q.isEmpty()) {
			Point now = q.poll();
			for (int i = 0; i < 3; i++) {
				int rx = now.x + mv[i][0];
				int ry = now.y + mv[i][1];
				if (rx < 0 || rx >= N || ry < 0 || ry > maxM || map[rx][ry] == '#') continue;
				if (dp[rx][ry] != -1 && dp[now.x][now.y] + (map[rx][ry] == 'C' ? 1 : 0) <= dp[rx][ry]) continue;
				int cnt = dp[now.x][now.y];
				if (map[rx][ry] == 'O')
					max = Math.max(max, dp[now.x][now.y]);
				else if (map[rx][ry] == 'C')
					cnt++;
				dp[rx][ry] = cnt;
				q.offer(new Point(rx, ry));
			}
		}
		System.out.println(max);
	}
}
