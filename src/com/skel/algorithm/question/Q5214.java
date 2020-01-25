package com.skel.algorithm.question;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Q5214 implements Question {

	static int N;
	static int K;
	static int M;


	static List<List<Integer>> right2left = new ArrayList<>();
	static List<List<Integer>> left2right = new ArrayList<>();

	static class Point implements Comparable<Point> {
		int now;
		int cnt;

		public Point(int now, int cnt) {
			this.now = now;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(cnt, o.cnt);
		}
	}


	static PriorityQueue<Point> pq = new PriorityQueue<>();

	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		M = sc.nextInt();
		for (int i = 0; i <= N; i++)
			left2right.add(new ArrayList<>());

		for (int i = 0; i <= M; i++)
			right2left.add(new ArrayList<>());


		for (int i = 0; i < M; i++) {
			for (int j = 0; j < K; j++) {
				int input = sc.nextInt();
				left2right.get(input).add(i);
				right2left.get(i).add(input);
			}
		}
		boolean check[] = new boolean[100001];
		pq.offer(new Point(1, 1));
		check[1] = true;
		while (!pq.isEmpty()) {
			Point now = pq.poll();
			if (now.now == N) {
				System.out.println(now.cnt);
				return;
			}

			List<Integer> right = left2right.get(now.now);
			for (int group : right) {
				List<Integer> left = right2left.get(group);
				for (int val : left) {
					if (check[val]) continue;
					pq.offer(new Point(val, now.cnt + 1));
					check[val] = true;
				}
			}
		}
		System.out.println(-1);
	}
}
