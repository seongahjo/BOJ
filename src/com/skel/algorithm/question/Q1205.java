package com.skel.algorithm.question;

import java.util.Scanner;

public class Q1205 implements Question {
    static int P, N, newPoint;
    static int arr[];

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 0 <= N <= P
        newPoint = sc.nextInt(); // 20억보다 작음
        P = sc.nextInt(); // 10 <= P <= 50
        arr = new int[N]; // 비 오름차순
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        int cnt = 1;
        for (int i = 0; cnt <= P && i < N; i++, cnt++) {
            //이미 안에 들어가있는거 체크
            if (arr[i] < newPoint) {
                // 이건 이미 들어가있는 값보다 크므로 해당 순위를 대체하는 것
                System.out.println(cnt);
                return;
            } else if (arr[i] == newPoint) {
                // 만약 값이 같으면?
                int j = i, c = 0;

                while (j < N && arr[j] == newPoint) {
                    j++;
                    c++;
                }
                // i ~ j까지 같은 값임
                // 끝은 i-j+1개
                if (cnt + c > P) {
                    System.out.println(-1);
                } else
                    System.out.println(cnt);
                return;
            }
        }
        if (N == P)
            System.out.println(-1);
            // 꽉참
        else
            System.out.println(N + 1);
    }

}
