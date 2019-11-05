package com.skel.algorithm.question;

public class Q2019Naver1 implements Question {

    // 	[3, 5, 6, 5, 4, 5]

    public int solution(int[] grade) {
        int answer = 0;
        int l = grade.length;
        for (int i = l - 1; i >= 1; i--) {
            if (i == l - 1 && grade[l - 2] > grade[l - 1]) {
                answer += Math.abs(grade[l - 2] - grade[l - 1]);
                grade[l - 1] = grade[l - 2];
            } else if (grade[i] < grade[i - 1]) {
                answer += Math.abs(grade[i] - grade[i - 1]);
                grade[i - 1] = grade[i];
            }
        }
        return answer;
    }

    @Override
    public void run() {

    }
}
