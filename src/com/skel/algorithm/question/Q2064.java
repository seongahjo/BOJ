package com.skel.algorithm.question;

import java.util.Scanner;

public class Q2064 implements Question {
    static int n;
    static int[][] address = new int[1000][4];

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            String input[] = sc.next().split("\\.");
            for (int j = 0; j < 4; j++)
                address[i][j] = Integer.parseInt(input[j]);
        }
        int arr[] = new int[4];
        int network[] = new int[4];
        for (int j = 0; j < 4; j++) {
            int value = 0;
            boolean goodBye = false;
            for (int num = (1 << 7); num >= 1; num = num >> 1) {
                boolean check = true;
                int val = address[0][j] & num;
                for (int i = 1; i < n; i++) {
                    if ((address[i][j] & num) != val) {
                        check = false;
                        break;
                    }
                }
                if (check)
                    value += num;
                else {
                    goodBye=true;
                    break;
                }

            }
            network[j] = value;

            if(goodBye)
                break;
        }

        for (int j = 0; j < 4; j++) {
            for (int num = 1; num < (1 << 8); num = num << 1) {
                int val = 256;
                for (int i = 0; i < n; i++) {
                    val = Math.min(val, address[i][j] & network[j]);
                }
                arr[j] = val;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(arr[i]);
            sb.append(".");
        }
        sb.delete(sb.length() - 1, sb.length());

        System.out.println(sb.toString());
        sb.delete(0, sb.length());
        for (int i = 0; i < 4; i++) {
            sb.append(network[i]);
            sb.append(".");
        }
        sb.delete(sb.length() - 1, sb.length());
        System.out.println(sb.toString());

    }
}
