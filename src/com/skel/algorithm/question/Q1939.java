package com.skel.algorithm.question;

import java.util.*;

public class Q1939 implements Question {
	static int N, M, start, end;

	static class Bridge implements Comparable<Bridge> {
		int to;
		int cost;

		public Bridge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Bridge o) {
			return Integer.compare(o.cost, cost);
		}
	}

	static List<List<Bridge>> list = new ArrayList<>();

	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		for(int i=0; i<=N;i++)
			list.add(new ArrayList<>());
		for (int i = 0; i < M; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int cost = sc.nextInt();
			list.get(from).add(new Bridge(to, cost));
			list.get(to).add(new Bridge(from, cost));
		}
		for (int i = 1; i <= N; i++) {
			Collections.sort(list.get(i));
		}
		start = sc.nextInt();
		end = sc.nextInt();
		boolean[] check = new boolean[100001];
		PriorityQueue<Bridge> q = new PriorityQueue<>();
		q.offer(new Bridge(start, 1987654321));
		while (!q.isEmpty()) {
			Bridge now = q.poll();
			check[now.to] = true;
			if (now.to == end) {
				System.out.println(now.cost);
				return;
			}

			for (Bridge b : list.get(now.to)) {
				if (check[b.to]) continue;
				int min = Math.min(now.cost, b.cost);
				q.offer(new Bridge(b.to, min));
			}
		}
	}
}
