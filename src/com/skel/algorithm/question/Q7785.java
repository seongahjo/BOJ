package com.skel.algorithm.question;
import java.util.*;
import java.lang.*;
import java.io.*;

public class Q7785 implements Question {
    static int n;
    static SortedSet<String> set =new TreeSet<>(Comparator.reverseOrder());
    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            n = Integer.parseInt(br.readLine());
            for (int i = 1; i <= n; i++) {
                String s = br.readLine();
                StringTokenizer st = new StringTokenizer(s);
                String name = st.nextToken();
                String flag = st.nextToken();
                if (flag.equals("enter")) {
                    set.add(name);
                } else {
                    set.remove(name);
                }
            }
            for(Iterator<String> iter = set.iterator(); iter.hasNext();){
                bw.write(iter.next());
                bw.write("\n");
            }
            bw.flush();
        }catch(Exception e){

        }
        // your code goes here
    }
}
