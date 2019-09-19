package com.skel.algorithm.question;

public class QKakao2020A implements Question {




    public int solution(String s) {
        int l = s.length();
        int answer = 987654321;
        for (int i = 1; i < l; i++) { // i개의 길이만큼
            int index = 0;// 시작 인덱스
            StringBuilder sb = new StringBuilder();
            while (index < l) { // 이게 순회
                int next = index + i;
                String nowString = next <= l ? s.substring(index, next) : s.substring(index, l);
                // 시작 인덱스
                int count = 1;
                while (next <= l) {
                    String nextString = next + i >= l ? s.substring(next, l) : s.substring(next, next + i);
                    if (nowString.equals(nextString)) {
                        count++;
                        next += i;
                    } else
                        break;
                }
                if (count != 1)
                    sb.append(count);
                sb.append(nowString);
                index = next;
            }
            answer = Math.min(answer, sb.length());
        }
        if(l==1) answer = 1;

        return answer;
    }

    @Override
    public void run() {
        System.out.println(solution("a"));
    }
}

