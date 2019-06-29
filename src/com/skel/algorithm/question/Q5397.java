package com.skel.algorithm.question;

import java.util.Scanner;
import java.util.Stack;

public class Q5397 implements Question {
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            String s = sc.next();
            Stack<Character> left = new Stack<>();
            Stack<Character> right = new Stack<>();
            int l = s.length();
            for (int i = 0; i < l; i++) {
                char now = s.charAt(i);
                switch (now) {
                    case '-':
                        if(left.isEmpty()) continue;
                        left.pop();
                        break;
                    case '<':
                        if (left.isEmpty()) continue;
                        right.push(left.pop());
                        break;
                    case '>':
                        if (right.isEmpty()) continue;
                        left.push(right.pop());
                        break;
                    default:
                        left.push(now);
                        break;
                }
            }
            while (!left.isEmpty()) {
                right.push(left.pop());
            }
            StringBuilder sb = new StringBuilder();
            while (!right.isEmpty()) {
                sb.append(right.pop());
            }
            System.out.println(sb.toString());
        }
    }
}
