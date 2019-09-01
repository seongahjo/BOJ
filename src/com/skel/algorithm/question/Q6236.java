package com.skel.algorithm.question;

import java.util.Scanner;

public class Q6236 implements Question {
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int cost[] = new int[N];
        int right = 1987654321;
        int left = 0;
        for (int i = 0; i < N; i++) {
            cost[i] = sc.nextInt();
            left = Math.max(left,cost[i]);
        }
        // Parametric search같은데?
        // 회수가 M 이하 여도 OK라는거지


        int result = 987654321;
        while (left <= right) {
            int mid = (left + right) >> 1;
            int cnt = 1;
            int money = mid;
            for (int i = 0; i < N; i++) {
                if (cost[i] <= money)
                    money -= cost[i];
                else {
                    money = mid - cost[i];
                    cnt++;
                }
            }
            if (cnt <= M) {
                //  금액을 낮춰야댐
                right = mid - 1;
                result = Math.min(result, mid);

            } else {
                left = mid + 1;

            }
        }
        System.out.println(result);
    }
}
