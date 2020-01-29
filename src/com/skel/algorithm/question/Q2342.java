package com.skel.algorithm.question;

import java.util.Arrays;
import java.util.Scanner;

public class Q2342 implements Question {
	@Override
	public void run() {
		int value[][] = {{0, 2, 2, 2, 2},
				{0, 1, 3, 4, 3},
				{0, 3, 1, 3, 4},
				{0, 4, 3, 1, 3},
				{0, 3, 4, 3, 1}
		};
		Scanner sc = new Scanner(System.in);
		int input = -1;
		int DP[][][] = new int[100001][5][5];
		for (int i = 0; i <= 100000; i++) {
			for (int j = 0; j <= 4; j++) {
				Arrays.fill(DP[i][j], 987654321);
			}
		}
		DP[0][0][0] = 0;
		int turn = 1;
		while (true) {
			input = sc.nextInt();
			if (input == 0) break;
			for (int i = 0; i <= 4; i++) {
				for (int j = 0; j <= 4; j++) {
					// j가 고정
					if (DP[turn - 1][i][j] == 987654321 || j == input) continue;
					// i 가 이전 값
					// j가 고정된 값
					DP[turn][input][j] = Math.min(DP[turn - 1][i][j] + value[i][input], DP[turn][input][j]);
					if (DP[turn - 1][j][i] == 987654321) continue;
					DP[turn][j][input] = Math.min(DP[turn][j][input], DP[turn - 1][j][i] + value[i][input]);
				}
			}
			turn++;
		}
		int min = 987654321;
		for (int i = 0; i <= 4; i++) {
			for (int j = 0; j <= 4; j++) {
				min = Math.min(DP[turn - 1][i][j], min);
			}
		}
		System.out.println(min);
	}


}
