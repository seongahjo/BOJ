package com.skel.algorithm.question;

import java.util.Scanner;

public class Q11058 implements Question {
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long DP[] = new long[101];
        DP[1]=1;
        DP[2]=2;
        for (int i = 3; i <= N; i++) {
            DP[i] = DP[i - 1] + 1;
            for (int j = 3; j <= i-1; j++) {
                DP[i]=Math.max(DP[i],DP[i - j] * (j - 1));
            }
        }
        System.out.println(DP[N]);
    }
}
