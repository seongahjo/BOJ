package com.skel.algorithm.question;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Q3649 implements Question {


    @Override
    public void run() {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        Integer x;
//        String line = null;
//        while (( line = br.readLine())!= null) {
//            if(line.equals(""))
//                break;
//            x = Integer.parseInt(line);
//            x *= 10000000;
//            int n = Integer.parseInt(br.readLine());
//            Map<Integer, Integer> map = new HashMap<>();
//            int max = 0;
//            int f1 = -1, f2 = -1;
//            for (int i = 0; i < n; i++) {
//                int next = Integer.parseInt(br.readLine());
//                if (map.get(next) == null) {
//                    map.put(next, 1);
//                } else
//                    map.put(next, map.get(next) + 1);
//
//                if (next * 2 == x && map.get(next) >= 2) {
//                    if (max == 0) {
//                        f1 = next;
//                        f2 = next;
//                    }
//                    continue;
//                }
//
//                if (map.get(x - next) != null) {
//                    int ll=Math.abs(next - x + next);
//                    if(max < ll){
//                        max = ll;
//                        f1 = Math.min(next,x-next);
//                        f2 = Math.max(next,x-next);
//                    }
//                }
//            }
//
//            if (f1==f2 && f1==-1) {
//                System.out.println("danger");
//            } else
//                System.out.printf("yes %d %d\n",f1,f2);
//            }
    }
}
