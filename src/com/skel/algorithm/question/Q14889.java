package com.skel.algorithm.question;

import java.util.Scanner;

public class Q14889 implements Question {

    static int N;
    static int arr[][] = new int[20][20];

    static boolean check[] = new boolean[20];

    static int choose(int n, int pos) {
        if (n == N/2) {
            int left = 0, right = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                        if (check[i] && check[j]) {
                            left += arr[i][j];
                            left += arr[j][i];
                        } else if (!check[i] && !check[j]) {
                            right += arr[i][j];
                            right += arr[j][i];
                    }
                }
            }
            return Math.abs(left - right)/2;
        }
        int result = 987654321;
        check[pos] = true;
        for (int i = pos + 1; i < N; i++) {
            result = Math.min(result, choose(n + 1, i));
        }
        check[pos] = false;
        return result;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        int result = 987654321;
        for(int i=0; i<N/2;i++) {
            result=Math.min(result,choose(0, i));
        }
        System.out.println(result);
        // i j 가 같은 팀
        //능력치 차이 최소

    }
}
