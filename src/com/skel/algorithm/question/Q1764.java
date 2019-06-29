package com.skel.algorithm.question;

import java.util.*;

public class Q1764 implements Question {
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        Map<String, Integer> map = new HashMap<>();

        for(int i=0; i<N;i++){
            String s = sc.next();
            if(map.get(s)==null)
                map.put(s,1);
        }

        for(int i=0; i<M;i++){
            String s = sc.next();
            if(map.get(s)==null)
                map.put(s,1);
            else
                map.put(s,2);
        }

        Iterator<String> key=map.keySet().iterator();
        ArrayList<String> list = new ArrayList<>();
        while(key.hasNext()){
            String _key = key.next();
            if(map.get(_key)==2)
                list.add(_key);
        }

        Collections.sort(list);
        System.out.println(list.size());
        for(String s : list){
            System.out.println(s);
        }
    }
}
