package com.skel.algorithm.question;

import java.util.HashMap;
import java.util.Map;

public class QKakao2018E implements Question {

    static Map<String, Integer> A = new HashMap<>();
    static Map<String, Integer> B = new HashMap<>();
    static Map<String, Integer> AB = new HashMap<>();
    static Map<String, Integer> AnB = new HashMap<>();

    static int mapValue(Map<String, Integer> m) {
        int val = 0;
        for (Map.Entry<String, Integer> e : m.entrySet()) {
            val += e.getValue();
        }

        return val;
    }

    public int solution(String str1, String str2) {
        for (int i = 0; i < str1.length() - 1; i++) {
            str1 = str1.intern();
            char c1 = Character.toLowerCase(str1.charAt(i));
            char c2 = Character.toLowerCase(str1.charAt(i + 1));
            if (Character.isAlphabetic(c1) && Character.isAlphabetic(c2)) {
                StringBuilder sb = new StringBuilder();
                sb.append(c1);
                sb.append(c2);
                A.merge(sb.toString(), 1, (prev, one) -> prev + one);
            }
        }

        for (int i = 0; i < str2.length() - 1; i++) {
            str2 = str2.intern();
            char c1 = Character.toLowerCase(str2.charAt(i));
            char c2 = Character.toLowerCase(str2.charAt(i + 1));
            if (Character.isAlphabetic(c1) && Character.isAlphabetic(c2)) {
                StringBuilder sb = new StringBuilder();
                sb.append(c1);
                sb.append(c2);
                B.merge(sb.toString(), 1, (prev, one) -> prev + one);
            }
        }

        AB.putAll(B);
        for (Map.Entry<String, Integer> m : A.entrySet()) {
            if (B.containsKey(m.getKey())) {
                AnB.put(m.getKey(), Math.min(m.getValue(), B.get(m.getKey())));
                AB.put(m.getKey(), Math.max(m.getValue(), B.get(m.getKey())));
            } else {
                AB.put(m.getKey(), m.getValue());
            }
        }
        int b = 0;
        int f = mapValue(AnB);
        int s = mapValue(AB);
        if( f== s && s== 0)
            return 1;
        int answer = (int) (65536 * f / s);
        return answer;
    }

    @Override
    public void run() {
        System.out.println(solution("E=M*C^2", "e=m*c^2"));
    }
}
