package com.skel.algorithm.question;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Q17833 implements Question {
	static int N, R, D, M;

	static class Point implements Comparable<Point> {
		int now;
		long cnt;

		public Point(int now, long cnt) {
			this.now = now;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Point o) {
			return Long.compare(cnt, o.cnt);
		}
	}


	static long DP[] = new long[2001];
	static int go[][] = new int[2001][2001];

	// 엣지 개수에 따라 우선순위 큐를 쓸지???


	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();
		D = sc.nextInt();
		M = sc.nextInt();


		for (int i = 0; i < M; i++) {
			int H = sc.nextInt();
			int T = sc.nextInt();
			int in1 = sc.nextInt();
			int in2 = sc.nextInt();
			int limit = N - H;
			// 동일한 경로에 대한 비용 최소 값....
			// 리스트 -> 배열...

			for (int l = 0; l <= limit; l++) {
				go[in1 + l][in2 + l] = go[in1+l][in2+l] == 0 ? T : Math.min(go[in1 + l][in2 + l],T);//
				go[in2 + l][in1 + l] = go[in2+l][in1+l] == 0 ? T : Math.min(go[in2 + l][in1 + l],T);
			}
		}

		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.offer(new Point(R, 0L));
		while (!pq.isEmpty()) {
			Point now = pq.poll();
			DP[now.now] = now.cnt;
			if (now.now == D) {
				System.out.println(DP[now.now]);
				return;
			}
			for (int i = 1; i <= N; i++) {
				if (go[now.now][i] == 0  || DP[i] != 0) continue;
				pq.offer(new Point(i, now.cnt + go[now.now][i]));
			}
		}
		System.out.println(-1);
	}
}
