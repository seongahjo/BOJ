package com.skel.algorithm.question;

public class QWinter2018B implements Question {
    int map[][][][] = new int[11][11][11][11];

    int answer = 0;

    public int solution(String dirs) {
        int nowX = 5;
        int nowY = 5;
        for (int i = 0; i < dirs.length(); i++) {
            char m = dirs.charAt(i);
            int nextX = nowX;
            int nextY = nowY;

            switch (m) {
                case 'L':
                    if (nowY > 0)
                        nextY--;
                    break;
                case 'R':
                    if (nowY < 10)
                        nextY++;
                    break;
                case 'U':
                    if (nowX > 0)
                        nextX--;
                    break;
                case 'D':
                    if (nowX < 10)
                        nextX++;
                    break;
            }
            if (nowX == nextX && nowY == nextY) continue;
            if (map[nowX][nowY][nextX][nextY] == 0) {
                answer++;
                map[nowX][nowY][nextX][nextY] = 1;
                map[nextX][nextY][nowX][nowY] = 1;
            }
            nowX = nextX;
            nowY = nextY;
        }
        return answer;
    }


    @Override
    public void run() {
        System.out.println(solution("ULURRDLLU"));
    }
}
