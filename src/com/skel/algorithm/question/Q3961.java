package com.skel.algorithm.question;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Q3961 implements Question {
    static char keyboard[][] = {{'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'},
            {'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'},
            {'z', 'x', 'c', 'v', 'b', 'n', 'm'}};

    static int find(char f, char s) {
        int fi = 0;
        int fj = 0;

        int si = 0;
        int sj = 0;

        for (int i = 0; i < keyboard.length; i++) {
            for (int j = 0; j < keyboard[i].length; j++) {
                if (f == keyboard[i][j]) {
                    fi = i;
                    fj = j;
                }
            }
        }

        for (int i = 0; i < keyboard.length; i++) {
            for (int j = 0; j < keyboard[i].length; j++) {
                if (s == keyboard[i][j]) {
                    si = i;
                    sj = j;
                }
            }
        }

        return Math.abs(fi - si) + Math.abs(fj - sj);
    }

    static class Word implements Comparable<Word> {
        String value;
        int cnt;

        public Word(String value, int cnt) {
            this.value = value;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return value + " " + cnt;
        }

        @Override
        public int compareTo(Word o) {
            if (cnt == o.cnt) {
                return value.compareTo(o.value);
            }
            return Integer.compare(cnt, o.cnt);
        }
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            String s = sc.next();
            int l = sc.nextInt();

            PriorityQueue<Word> pq = new PriorityQueue<>();
            for (int i = 0; i < l; i++) {

                String next = sc.next();
                int cnt = 0;
                for (int j = 0; j < next.length(); j++) {
                    cnt += find(s.charAt(j), next.charAt(j));

                    pq.offer(new Word(next, cnt));
                }
                while (!pq.isEmpty()) {
                    System.out.println(pq.poll().toString());
                }
            }
        }
    }
}