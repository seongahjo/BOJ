package com.skel.algorithm.question;

import java.util.Scanner;

public class Q2935 implements Question {
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        String first = sc.next();
        String operation = sc.next();
        String second = sc.next();
        StringBuilder sb = new StringBuilder();
        if (operation.equals("+")) {
            int maxLength = Math.max(first.length(), second.length());
            int minLength = Math.min(first.length(), second.length());
            if(maxLength == minLength) {
                sb.append(2);
                for (int i = 0; i < maxLength - 1; i++)
                    sb.append(0);
                System.out.println(sb.toString());
                return;
            }
            sb.append(1);
            for(int i=0; i<maxLength-minLength-1; i++)
                sb.append(0);
            sb.append(1);
            for(int i=0; i<minLength-1;i++)
                sb.append(0);
            System.out.println(sb.toString());
        } else {
            int length = first.length() + second.length()-2;
            sb.append(1);
            for (int i = 0; i < length; i++)
                sb.append(0);
            System.out.println(sb.toString());
        }
    }
}
