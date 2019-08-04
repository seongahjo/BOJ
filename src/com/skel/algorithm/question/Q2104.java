package com.skel.algorithm.question;

import java.util.Scanner;

public class Q2104 implements Question {
    static int N;
    static long arr[] = new long[100001];


    // (i 부터 j 까지 값 합) * (i 부터 j 까지 최소값)
    static long sum(int left, int right) {
        if (left == right) {
            return arr[left] * arr[left];
        }
        int mid = (left + right) >> 1;
        // 왼쪽 끝
        long result = Math.max(sum(left, mid), sum(mid + 1, right));
        int start = mid;
        int end = mid;
        long minResult = arr[start];
        long min = arr[start];
        result = Math.max(result, minResult * min);
        while (start > left || end < right) {
            if (end < right && (left == start || arr[end + 1] > arr[start - 1])) {
                minResult += arr[++end];
                min = Math.min(arr[end], min);
            } else {
                minResult += arr[--start];
                min = Math.min(arr[start], min);
            }
            result = Math.max(result, minResult * min);
        }
        return result;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++)
            arr[i] = sc.nextInt();

        System.out.println(sum(0, N - 1));
    }
}
