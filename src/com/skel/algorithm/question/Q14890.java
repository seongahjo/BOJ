package com.skel.algorithm.question;

import java.util.Scanner;

public class Q14890 implements Question {
    static int N, L;
    static int map[][] = new int[101][101];
    static int ans = 0;

    static void check(int row, int a[][]) {
        boolean c[] = new boolean[N + 1];
        int temp = a[row][1];
        for (int i = 1; i <= N; i++) {
            if (a[row][i] != temp) {
                if (Math.abs(a[row][i] - temp) > 1) return; // 차이가 1 이상
                if (a[row][i] < temp) {
                    int num = -1;
                    for (int j = i; j <= i + L - 1; j++) {
                        if (j > N || c[j]) return;
                        c[j] = true;
                        if (num == -1) {
                            num = a[row][j];
                            continue;
                        }
                        if (a[row][j] != num) return;
                    }
                    i = i + L - 1;
                    if (i > N) break;
                } // 작은 경우
                else {
                    int num = -1;
                    for (int j = i - 1; j >= i - L; j--) {
                        if (j < 0 || c[j]) return;
                        c[j] = true;
                        if (num == -1) {
                            num = a[row][j];
                            continue;
                        }
                        if (a[row][j] != num) return;
                    }
                }
                temp = a[row][i];
            }
        }
        ans++;
    }


    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        L = sc.nextInt();
        int verticalMap[][]=new int[101][101];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] = sc.nextInt();
                verticalMap[j][i]=map[i][j];
            }
        }
        // 좌우, 상하등 처리해야할 때 배열 2개 만들기
        for(int i=1;i<=N;i++){
            check(i,map);
            check(i,verticalMap);
        }
        System.out.println(ans);
        // 경사로를 놓은 후 체크

    }
}
