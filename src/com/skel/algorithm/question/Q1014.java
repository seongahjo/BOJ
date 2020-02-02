package com.skel.algorithm.question;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q1014 implements Question {
	static int nextPoss(int nowPos, int M) {
		return ((nowPos << 1) & ((1 << (M + 2)) - 1));
	}


	static boolean isAdd(int state, int now, int M) {
		int nowY = now % M;
		List<Integer> existList = new ArrayList<>();
		if (nowY != 0 && ((state & 2) == 2)) return false;
		for (int j = (1 << (nowY + 1)), cnt = 1; j <= (1 << (M + 2)); j = j << 1, cnt++) {
			if (M - cnt < 0) break;
			if ((state & j) == j)
				existList.add(M - cnt);
		}
		if (existList.contains(nowY - 1) || existList.contains(nowY + 1)) return false;
		return true;
	}

	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		int C = sc.nextInt();
		while (C-- > 0) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int c = M + 2;
			int possCount = 1 << c;
			int DP[][] = new int[121][possCount];
			char map[][] = new char[N][M];
			List<Integer> not = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				String s = sc.next();
				for (int j = 0; j < M; j++) {
					map[i][j] = s.charAt(j);
					if (map[i][j] == 'x')
						not.add(i * M + j);
				}
			}
			DP[0][0] = 0;
			DP[0][1] = map[0][0] == 'x' ? 0 : 1;
			int max = 0;
			for (int i = 1; i < N * M; i++) {
				int limit = i < c ? (1 << i) : possCount;
				for (int h = 0; h < limit; h++) {
					int next = nextPoss(h, M);
					DP[i][next] = Math.max(DP[i - 1][h], DP[i][next]);
					if (not.contains(i)) {
						continue;
					}
					if (isAdd(next, i, M)) {
						DP[i][next + 1] = Math.max(DP[i][next + 1], DP[i - 1][h] + 1);
					}
				}
			}

			for (int i = 0; i < possCount; i++) {
				max = Math.max(max, DP[N * M - 1][i]);
			}
			System.out.println(max);
		}
	}
}
