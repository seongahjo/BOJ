package com.skel.algorithm.question;

import java.util.Scanner;

public class Q14891 implements Question {
    static int status[][] = new int[4][8];
    static int K;
    static int value[][] = {{0, 1}, {0, 2}, {0, 4}, {0, 8}};

    static void turn(int num, int dir, boolean left, boolean right) {
        int rdir = dir == 1 ? -1 : 1;
        int leftNum = num - 1;
        int rightNum = num + 1;
        if (leftNum > -1  && status[num][6] != status[leftNum][2] && !right) {
            turn(leftNum, rdir, true, false);
        }
        if (rightNum < 4 && status[num][2] != status[rightNum][6] && !left) {
            turn(rightNum, rdir, false, true);
        }

        int now = 0;
        int tmp = status[num][0];
        for (int i = 0; i < 7; i++) {
            int next = now + rdir;
            if (next == 8) next = 0;
            else if (next == -1) next = 7;
            status[num][now] = status[num][next];
            now = next;
        }
        status[num][now]=tmp;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 4; i++) {
            String s = sc.next();
            for (int j = 0; j < 8; j++) {
                status[i][j] = Integer.parseInt(s.substring(j, j + 1));
            }
        }
        int result = 0;
        K = sc.nextInt();
        for (int i = 0; i < K; i++) {
            int num = sc.nextInt();
            int dir = sc.nextInt();
            turn(num - 1, dir, false, false);
        }

        for (int i = 0; i < 4; i++) {
            result += value[i][status[i][0]];
        }
        System.out.println(result);
    }
}
