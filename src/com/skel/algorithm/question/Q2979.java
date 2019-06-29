package com.skel.algorithm.question;

import com.skel.algorithm.question.Question;

import java.util.Scanner;

public class Q2979 implements Question {
    static int cost[] = new int[4];
    static int time[] = new int[101];

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        cost[1] = sc.nextInt();
        cost[2] = sc.nextInt();
        cost[3] = sc.nextInt();
        int maxEnd = 0;
        for (int i = 0; i < 3; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            maxEnd = Math.max(end, maxEnd);
            for (int s = start; s < end; s++)
                time[s]++;
        }
        int result = 0;
        for (int i = 0; i <= maxEnd; i++)
            result += cost[time[i]]*time[i];
        System.out.println(result);

    }
}
