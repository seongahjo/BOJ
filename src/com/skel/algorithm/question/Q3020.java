package com.skel.algorithm.question;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q3020 implements Question {

    static int N, H;
    static int high[] = new int[500003];
    static int bottom[] = new int[500003];

    static int highCount[] = new int[500003];
    static int bottomCount[] = new int[500003];

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());


            for (int i = 1; i <= N / 2; i++) {
                bottom[Integer.parseInt(br.readLine())]++;
                high[Integer.parseInt(br.readLine())]++;
            }

            bottomCount[H] = bottom[H];
            for (int i = H - 1; i >= 1; i--)
                bottomCount[i] = bottom[i] + bottomCount[i + 1];
            highCount[1] = high[H];
            for (int i = 2; i <= H; i++)
                highCount[i] = highCount[i - 1] + high[H - i + 1];

            int min = 987654321;
            int count = 1;
            for (int i = 1; i <= H; i++) {
                if (min > highCount[i] + bottomCount[i]) {
                    min = highCount[i] + bottomCount[i];
                    count = 1;
                } else if (min == highCount[i] + bottomCount[i])
                    count++;
            }
            System.out.printf("%d %d \n", min, count);


        } catch (Exception e) {

        }

    }
}