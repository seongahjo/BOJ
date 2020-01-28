package com.skel.algorithm.question;

import java.util.*;

public class Q1939_2 implements Question {
	static int N, M, start, end;

	static class Bridge {
		int to;
		int cost;

		public Bridge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}

	static List<List<Bridge>> list = new ArrayList<>();

	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		for (int i = 0; i <= N; i++)
			list.add(new ArrayList<>());
		for (int i = 0; i < M; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int cost = sc.nextInt();
			list.get(from).add(new Bridge(to, cost));
			list.get(to).add(new Bridge(from, cost));
		}
		start = sc.nextInt();
		end = sc.nextInt();

		int l = 1;
		int r = 1000000001;
		int result = 0;
		int mid = 0; // 내 중량
		// 지금 내 중량으로 건너갈 수 있나???
		while (l <= r) {
			boolean isSuccess = false;
			mid = (l + r) >> 1;
			Queue<Bridge> q = new LinkedList<>();
			q.offer(new Bridge(start,0));
			boolean[] check = new boolean[100001];
			while (!q.isEmpty()) {
				Bridge now = q.poll();
				if (now.to == end) {
					isSuccess = true;
					break;
				}
				for (Bridge b : list.get(now.to)) {
					if (b.cost < mid || check[b.to]) continue;
					check[b.to] = true;
					q.offer(new Bridge(b.to,b.cost));
				}
			}
			if (isSuccess) {
				result = Math.max(result, mid);
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		System.out.println(result);
	}
}
