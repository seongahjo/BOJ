package com.skel.algorithm.question;

import java.util.Scanner;

public class Q1328 implements Question {

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int L = sc.nextInt();
        int R = sc.nextInt();

        long DP[][][] = new long[101][101][101];
        DP[1][1][1] = 1;

        for (int n = 2; n <= N; n++) {
            for (int l = 1; l <= L; l++) {
                for (int r = 1; r <= R; r++) {
                    if ((n == l && r == 1) || (n == r && l == 1)) {
                        DP[n][l][r] = 1;
                        continue;
                    }
                    DP[n][l][r] = (DP[n - 1][l - 1][r]%1000000007L +
                            DP[n - 1][l][r - 1]%1000000007L +
                            DP[n - 1][l][r] * (n - 2)%1000000007L)%1000000007L;
                }
            }
        }
        System.out.println(DP[N][L][R] % 1000000007L);
    }
}
