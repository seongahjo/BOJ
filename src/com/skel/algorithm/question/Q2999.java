package com.skel.algorithm.question;

import java.util.Scanner;

public class Q2999 implements Question {
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int l = s.length();
        int sqrt = (int) Math.sqrt(l);

        int max = 0;
        for (int i = 1; i <= sqrt; i++) {
            if (l % i == 0)
                max = Math.max(i, max);
        }


        char map[][] = new char[max][l / max];


        for (int j = 0; j < l / max; j++) {
            for (int i = 0; i < max; i++) {
                map[i][j] = s.charAt(i + j * max);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<max; i++){
            for(int j=0;j<l/max;j++){
                sb.append(map[i][j]);
            }
        }
        System.out.println(sb.toString());
    }
}
