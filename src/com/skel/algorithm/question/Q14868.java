package com.skel.algorithm.question;

import java.util.*;

public class Q14868 implements Question {
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

	@Override
	public void run() {
		int mv[][] = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int map[][] = new int[2001][2001];
		for (int i = 0; i < 2001; i++)
			Arrays.fill(map[i], -1);
		PriorityQueue<Point> pq = new PriorityQueue<>();
		Point start = null;
		int K = sc.nextInt();
		int resultCnt = 0;
		for (int i = 0; i < K; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			if (start == null) start = new Point(x, y, 0);
			map[x][y] = 0;
			resultCnt++;
			pq.offer(new Point(x, y, 0));
		}
		int l = 0;
		int r = 0;

		while (!pq.isEmpty()) {
			Point p = pq.poll();
			for (int i = 0; i < 4; i++) {
				int rx = p.x + mv[i][0];
				int ry = p.y + mv[i][1];
				if (rx <= 0 || rx > N || ry <= 0 || ry > N || map[rx][ry] != -1) continue;
				map[rx][ry] = p.cnt + 1;
				r = Math.max(p.cnt + 1, r);
				pq.offer(new Point(rx, ry, p.cnt + 1));
			}
		}
		int result = 987654321;
		while (l <= r) {
			boolean isComplete = false;
			int mid = (l + r) >> 1; // 현재 턴
			Queue<Point> q = new LinkedList<>();
			boolean[][] check = new boolean[2001][2001];
			int cnt = 0;
			q.offer(new Point(start.x, start.y, 0));
			check[start.x][start.y] = true;
			while (!q.isEmpty()) {
				Point now = q.poll();
				if ((map[now.x][now.y] == 0) && ((++cnt) == resultCnt)) {
					isComplete = true;
					break;
				}
				for (int i = 0; i < 4; i++) {
					int rx = now.x + mv[i][0];
					int ry = now.y + mv[i][1];
					if (rx <= 0 || rx > N || ry <= 0 || ry > N || check[rx][ry] || mid < map[rx][ry]) continue;
					check[rx][ry] = true;
					q.offer(new Point(rx, ry, now.cnt + 1));
				}
			}
			if (isComplete) {
				result = Math.min(result, mid);
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		System.out.println(result);
	}
}
