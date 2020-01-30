package com.skel.algorithm.question;

import java.util.*;

public class Q13302 implements Question {
	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		List<Integer> not = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			not.add(sc.nextInt());
		}
		// 일수 쿠폰
		int DP[][] = new int[101][101];
		for (int i = 0; i < 101; i++) {
			Arrays.fill(DP[i], 987654321);
		}
		int val[] = {10000, 25000, 37000};
		// 쿠폰 3개 이상...
		//DP[i-1][j], DP[i-3][j-1] DP[i-5][j-2]
		// DP[i-1][j-3]
		DP[0][0] = 0;
		// X일에 J개 쿠폰을 가졌을때 지불한 최저 비용
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= i; j++) {
				if (not.contains(i)) {
					DP[i][j] = Math.min(DP[i][j], DP[i - 1][j]);
				}
				DP[i][j] = Math.min(DP[i][j], DP[i - 1][j] + val[0]);
				if (!(i - 3 < 0 || j - 1 < 0))
					DP[i][j] = Math.min(DP[i][j], DP[i - 3][j - 1] + val[1]);
				if (!(i - 5 < 0 || j - 2 < 0))
					DP[i][j] = Math.min(DP[i][j], DP[i - 5][j - 2] + val[2]);
				if (j + 3 > 100) continue;
				DP[i][j] = Math.min(DP[i][j], DP[i - 1][j + 3]);
			}
		}

		int min = 987654321;
		for (int i = 0; i < N; i++) {
			min = Math.min(min, DP[N][i]);
		}
		System.out.println(min);
	}
}
