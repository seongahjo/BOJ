package com.skel.algorithm.question;

import java.util.Scanner;

public class Q15924 implements Question {

	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		long DP[][] = new long[3001][3001];
		int N = sc.nextInt();
		int M = sc.nextInt();
		char map[][] = new char[3001][3001];

		for (int i = 1; i <= N; i++) {
			String s = sc.next();
			for (int j = 1; j <= M; j++) {
				map[i][j] = s.charAt(j - 1);
			}
		}

		DP[N][M] = 1;

		long sum = 1;
		for (int i = N; i > 0; i--) {
			for (int j = M; j > 0; j--) {
				if (i == N && j == M) continue;
				if (map[i][j] == 'B') {
					DP[i][j] = (DP[i + 1][j] + DP[i][j + 1]) % 1000000009;
				} else if (map[i][j] == 'E') {
					DP[i][j] = DP[i][j + 1];
				} else if (map[i][j] == 'S') {
					DP[i][j] = DP[i + 1][j];
				}
				sum = (sum + DP[i][j]) % 1000000009;
			}
		}

		System.out.println(sum % 1000000009);

	}
}
