package com.skel.algorithm.question;

import java.util.Scanner;

public class Q2302 implements Question {
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        int dp[] = new int[41];
        int n = sc.nextInt();
        int k = sc.nextInt();
        int fix[] = new int[k];
        for (int i = 0; i < k; i++)
            fix[i] = sc.nextInt();
        int fixCnt = 0;
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (fixCnt < k) {
                if (fix[fixCnt] == i) {
                    dp[i] = dp[i - 1];
                    continue;
                } else if (fix[fixCnt] + 1 == i) {
                    dp[i] = dp[i - 1];
                    fixCnt++;
                    continue;
                }
            }
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        System.out.println(dp[n]);
    }
}
