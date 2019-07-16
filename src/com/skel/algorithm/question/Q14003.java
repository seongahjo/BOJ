package com.skel.algorithm.question;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Q14003 implements Question {
    static int N;
    static int A[] = new int[1000000];
    static int result[] = new int[1000000];
    static ArrayList<Pair> pairs = new ArrayList<>();

    static class Pair {
        int position;
        int value;

        public Pair(int position, int value) {
            this.position = position;
            this.value = value;
        }
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int lastIndex = 0;
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
            if (i == 0) {
                result[lastIndex] = A[i];
                pairs.add(new Pair(0, result[lastIndex]));
                continue;
            }
            if (result[lastIndex] < A[i]) {
                result[++lastIndex] = A[i];
                pairs.add(new Pair(lastIndex, A[i]));
            } else {
                int left = 0;
                int right = lastIndex;
                int mid = 0;
                int ans = 0;
                while (left <= right) {
                    mid = (left + right) >> 1;
                    if (result[mid] >= A[i]) {
                        ans = mid;
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
                pairs.add(new Pair(ans, A[i]));
                result[ans] = A[i];
            }
        }
        System.out.println(lastIndex + 1);
        Stack<Integer> st = new Stack<>();
        for (int i = pairs.size() - 1; i >= 0; i--) {
            Pair p = pairs.get(i);
            if (lastIndex == p.position) {
                lastIndex--;
                st.push(p.value);
            }
        }
        while (!st.isEmpty()) {
            System.out.printf("%d ", st.pop());
        }

    }
}
