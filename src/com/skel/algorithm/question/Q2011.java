package com.skel.algorithm.question;

import java.util.Scanner;

public class Q2011 implements Question {
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        long DP[] = new long[5001];
        int l = s.length();
        DP[0] = 1;
        DP[1] = 1;
        if(s.charAt(0)=='0'){
            System.out.println(0);
            return;
        }
        for (int i = 1; i < l; i++) {
            if (s.charAt(i) - '0' == 0) { // 0 이 나옴
                if ( s.charAt(i-1)-'0' != 0 && (s.charAt(i - 1) - '0') <= 2) {
                    DP[i] = DP[i - 1] % 1000000;
                } else {
                    System.out.println("0");
                    return;
                }
            } else {
                DP[i] = DP[i - 1] % 1000000;
                if ((s.charAt(i - 1) - '0' != 0) && (s.charAt(i) - '0') + (s.charAt(i - 1) - '0') * 10 <= 26) {
                    if (i == 1) DP[i]++;
                    else {
                        DP[i] += DP[i - 2];
                        DP[i] = DP[i] % 1000000;
                    }
                }
            }
        }
        if (s.equals("0")) {
            System.out.println(0);
        } else
            System.out.println(DP[l - 1] % 1000000);
    }
}
