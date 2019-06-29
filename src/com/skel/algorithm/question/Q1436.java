package com.skel.algorithm.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Q1436 implements Question {

    static Map<Integer, String> map = new HashMap<>();

    static boolean check(int num) {
        String s = String.valueOf(num);
        int l = s.length();
        int cnt = 0;
        boolean chk = false;
        for (int i = l - 1; i >= 0; i--) {
            if (s.charAt(i) == '6') {
                if (++cnt == 3) chk = true;
            } else {
                cnt = 0;
            }
        }
        return chk;
    }

    static void init() {
        int count = 0;
        for (int i = 666; count < 10000; i++) {
            if (check(i))
                map.put(++count, String.valueOf(i));
        }
    }

    @Override
    public void run() {
        init();
        Scanner sc = new Scanner(System.in);
        Integer i = sc.nextInt();
        System.out.println(map.get(i));
    }

}
