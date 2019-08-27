package com.skel.algorithm.question;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class QKakao2018AA implements Question {


    public Character next(Queue<Character> q, int m, int p) {
        // first+p-1 가 자신
        char now = ' ';
        for (int i = 1; i <= m; i++) {
            Character next = q.poll();
            if (i == p) {
                now = next;
            }
        }
        return now;
    }


    public Queue<Character> possibility(int n) {
        //100000

        // 10 이면 1 , 0 으로 끊는뎀?

        // 진수 만들기
        Queue<Character> list = new LinkedList<>();
        for (int i = 0; i < 100000; i++) {
            int number = i;
            // 진수 변환
            Stack<Character> st = new Stack<>();
            while (number >= n) {
                int addNext = number % n;
                if (addNext >= 10) {
                    st.push((char) ((int) 'A' + addNext - 10));
                } else {
                    st.push((char) ('0' + addNext));
                }
                number /= n;
            }
            int addNext = number % n;
            if (addNext >= 10)
                st.push((char) ((int) 'A' + addNext - 10));
            else
                st.push((char) ('0' + addNext));
            while (!st.isEmpty()) {
                list.add(st.pop());
            }
        }
        return list;
    }

    public String solution(int n, int t, int m, int p) {
        // t 개
        // n 진법, m 인원
        // p 순서 1 ~ m번
        Queue<Character> q = possibility(n);

        int add = 0;
        String answer = "";
        while (add < t) {
            answer += next(q, m, p);
            add++;
        }
        return answer;
    }

    // 100000
    @Override
    public void run() {
        System.out.println(solution(16, 16, 2, 2));
    }
}
