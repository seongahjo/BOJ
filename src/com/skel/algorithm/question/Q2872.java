package com.skel.algorithm.question;

import java.util.Scanner;

public class Q2872 implements Question {
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int arr[] = new int[N + 1];
        int beforeArr[] = new int[N + 1];
        for (int i = 1; i <= N; i++)
            beforeArr[i] = i;



        // arr [몇 번] 위치
        for (int i = 1; i <= N; i++) {
            int input = sc.nextInt();
            arr[input] = i;
        }


        int cnt = 0;

        System.out.println(cnt);
        // 0번째 가장 크고
        // 1번째 그다음으로 크고 -1해야하고 아니면 +1
    }
}
