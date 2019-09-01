package com.skel.algorithm.question;

public class Q2017SummerE implements Question {


    public int solution(int n) {
        int ans = 0;
        for (int i = 1; i <= n; i = (i << 1)) {
            if ((n & i) == i) {
                ans++;
            }
        }
        return ans;
    }

    @Override
    public void run() {

    }
}
