package com.skel.algorithm.question;

import java.util.ArrayList;
import java.util.Stack;
import java.util.stream.Collectors;

public class QKakao2018G implements Question {

    static Stack<Double> stack = new Stack<>();
    static int parse(String s) {
        int l = s.length();
        int i = 0;
        int next = 0;
        while (i < l) {
            double number = s.charAt(i) - '0';
            char oper = ' ';
            char special = ' ';
            if (number == 1 && i + 1 < l && s.charAt(i + 1) == '0') {
                number = 10;
                oper = s.charAt(i + 2);
                next = 3;
            } else {
                oper = s.charAt(i + 1);
                next = 2;
            }
            int operNum = -1;

            switch (oper) {
                case 'S':
                    operNum = 1;
                    break;
                case 'D':
                    operNum = 2;
                    break;
                case 'T':
                    operNum = 3;
                    break;
            }
            number = Math.pow(number, operNum);
            if (i + next + 1 <= l) {
                int specialIndex = i + next;
                next++;
                if (s.charAt(specialIndex) == '*') {
                    number *= 2;
                    if(!stack.isEmpty()){
                        double d = stack.pop();
                        stack.push(d*2);
                    }

                } else if (s.charAt(specialIndex) == '#') {
                    number = -number;
                } else {
                    next--;
                }
                // 연산후 스택에 넣기?
            }
            stack.push(number);
            i += next;
        }
        int result = 0 ;
        while(!stack.isEmpty()){
            result+=stack.pop();
        }

        return result;

    }

    public int solution(String dartResult) {
        return parse(dartResult);
    }



    @Override
    public void run() {
        String[] q = {"1S2D*3T","1D2S#10S", "1D2S0T","1S*2T*3S"};
        System.out.println(solution("1D2S3T*"));
        // S, D, T
        // 0 ~ 10
        // 숫자 / 문자 / [옵션]
        // * 는 이전까지 얻은 점수 *2, 지금 점수 *2
        // #은 마이너스

        // 1. 파싱


        // 2. 파싱 결과 수정
    }
}
