package com.skel.algorithm.question;

import java.util.Scanner;

public class Q2857 implements Question {
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<5;i++){
            String s = sc.next();
            if(s.contains("FBI")) {
                sb.append(i + 1);
                sb.append(" ");
            }
        }
        if(sb.length()==0)
            System.out.println("HE GOT AWAY!");
        else
        System.out.println(sb.toString());
    }
}
