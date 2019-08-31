package com.skel.algorithm.question;

public class Q2017Summer2 implements Question {

    int inDP[][] = new int[100001][2];
    int notDP[][] = new int[100001][2];

    // 첫빠다에 1이었는지?
    public int solution(int sticker[]) {
        int l = sticker.length;
        inDP[0][1] = sticker[0];
        inDP[1][0] = sticker[0];
        inDP[1][1] = sticker[1];

        notDP[1][1] = sticker[1];
        for (int i = 2; i < l; i++) {

            inDP[i][0] = Math.max(inDP[i - 1][1], inDP[i - 1][0]);
            if (i != l - 1)
                inDP[i][1] = inDP[i - 1][0] + sticker[i];
            notDP[i][0] = Math.max(notDP[i - 1][1], notDP[i - 1][0]);
            notDP[i][1] = notDP[i - 1][0] + sticker[i];
        }

        int answer = Math.max(Math.max(inDP[l - 1][0], inDP[l - 1][1]), Math.max(notDP[l - 1][0], notDP[l - 1][1]));
        return answer;
    }

    @Override
    public void run() {

    }
}
