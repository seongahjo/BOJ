package com.skel.algorithm.question;

import java.util.Scanner;

public class Q11687 implements Question {
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        int numZero = sc.nextInt();
        int cnt = 0;
        for (int i = 5; cnt <= numZero; i += 5) {
            int num = i;
            while (num % 5 == 0) {
                cnt++;
                num /= 5;
            }
            if (cnt == numZero) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }
}
