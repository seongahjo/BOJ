package com.skel.algorithm.question;

import java.util.*;

public class Q1111 implements Question {

    static boolean isCheck(int arr[], long a, long b) {
        for (int i = 0; i < arr.length - 1; i++) {
            long next = arr[i] * a + b;
            if (next != arr[i + 1]) return false;
        }
        return true;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int arr[] = new int[N];
        for (int i = 0; i < N; i++)
            arr[i] = sc.nextInt();
        // N이 4 이상이면 무조건 고정 없거나 하나거나

        Queue<Integer> pq = new LinkedList<>();
        if (N > 2) {
            // 3일떄?

            // 긁기
            // 4 이상은 무조건 하나임
            for (int i = -1000; i <= 1000; i++)
                pq.offer(i);

            // 4 2 0

            for (int i = 1; i < N; i++) {
                if (arr[i - 1] != 0)
                    pq.offer(arr[i] / arr[i - 1]);
            }
            // 2
            // 1 2 4

            // 0

            while (!pq.isEmpty()) {
                long now = pq.poll();
                long b = arr[1] - arr[0] * now;
                if (isCheck(arr, now, b)) {
                    System.out.println(arr[N - 1] * now + b);
                    return;
                }
            }
        } else {
            if (N == 2) {
                if (arr[0] == arr[1]) {
                    System.out.println(arr[0]);
                    return;
                }
            }
            System.out.println("A");
            return;
        }
        System.out.println("B");
    }
}
