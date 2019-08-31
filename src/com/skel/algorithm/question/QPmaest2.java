package com.skel.algorithm.question;

import java.util.Arrays;

public class QPmaest2 implements Question {


    int arrNum = 0;
    int operNum = 0;


    public int solution(String arr[]) {
        int l = arr.length / 2 + 1;
        int DPmin[][] = new int[l + 1][l + 1];
        int DPmax[][] = new int[l + 1][l + 1];
        char oper[] = new char[l];
        int numArr[] = new int[l + 1];

        for (int i = 1; i <= arr.length; i++) {
            if (i % 2 != 0) {
                numArr[++arrNum] = Integer.parseInt(arr[i - 1]);
            } else {
                oper[++operNum] = arr[i - 1].charAt(0);
            }
        }


        for (int i = 1; i <= l; i++) {
            Arrays.fill(DPmax[i], -987654321);
            Arrays.fill(DPmin[i], 987654321);
        }
        for (int i = 1; i <= l; i++) {
            DPmax[i][i] = numArr[i];
            DPmin[i][i] = numArr[i];
        }


        for (int h = 1; h <= l; h++) {
            for (int i = 1; i + h <= l; i++) {
                for (int k = i; k <= i + h - 1; k++) {
                    if (oper[k] == '+') {
                        DPmax[i][i + h] = Math.max(DPmax[i][k] + DPmax[k + 1][i + h], DPmax[i][i + h]);
                        DPmin[i][i + h] = Math.min(DPmin[i][k] + DPmin[k + 1][i + h], DPmin[i][i + h]);
                    } else if (oper[k] == '-') {
                        DPmax[i][i + h] = Math.max(DPmax[i][k] - DPmin[k + 1][i + h], DPmax[i][i + h]);
                        DPmin[i][i + h] = Math.min(DPmin[i][k] - DPmax[k + 1][i + h], DPmin[i][i + h]);
                    }
                }
            }
        }
        int answer = DPmax[1][l];
        return answer;
    }

    @Override
    public void run() {
        System.out.println(solution(new String[]{"1", "-", "3", "+", "5", "-", "8"}));
    }
}
