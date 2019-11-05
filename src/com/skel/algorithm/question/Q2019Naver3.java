package com.skel.algorithm.question;

import java.util.*;

public class Q2019Naver3 implements Question {

    public int equals(String command, String button, Q q) {
        int cl = command.length();
        int bl = button.length();
        char nowB = button.charAt(0);
        for (int j = 0; j < cl; j++) {
            char nowC = command.charAt(j);
            if (nowB == nowC) {
                int rest = cl - j;
                if (bl > rest) return -1;
                for (int i = 0; i < bl; i++, j++) {
                    if (button.charAt(i) != command.charAt(j)) return -1;
                }
                return j;
            }
            q.point++;
        }
        return -1;
    }

    class Q {
        String command;
        int buttonIndex;
        int point;


        public Q(String command, int buttonIndex, int point) {
            this.command = command;
            this.buttonIndex = buttonIndex;
            this.point = point;
        }
    }

    public int solution(String command, String[] buttons, int[] scores) {
        Queue<Q> q = new LinkedList<>();
        int bl = buttons.length;
        for (int i = 0; i < bl; i++) {
            q.offer(new Q(command, i, 0));
        }
        int answer = command.length();
        while (!q.isEmpty()) {
            Q nowq = q.poll();
            String nowCommand = nowq.command;
            String nowButton = buttons[nowq.buttonIndex];
            int nextIndex = equals(nowCommand, nowButton,nowq);
            answer = Math.max(answer, nowq.point);
            if (nextIndex == -1) continue;
            int nextScore = nowq.point + scores[nowq.buttonIndex];
            for (int i = 0; i < bl; i++)
                q.offer(new Q(nowCommand.substring(nextIndex), i, nextScore));
        }
        return answer;
    }

    @Override
    public void run() {

    }
}
