package com.skel.algorithm.question;

public class Q2017SummerD implements Question {
    // N이 2억
    // stations 만개
    // W 범위

    // 배열 X

    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        for (int i = 0; i < stations.length; i++) {
            int now = stations[i];
            if (i == 0) {
                if((now-w-1)%(float)(2*w+1)<0.5)
                    answer += (now-w-1)/(2*w+1);
                else
                    answer += (now-w-1)/(2*w+1)+1;
            }
            else{
                if((stations[i]-w-w-stations[i-1]-1)%(float)(2*w+1)<0.5)
                    answer += (stations[i]-w-w-stations[i-1]-1)/(2*w+1);
                else
                    answer += (stations[i]-w-w-stations[i-1]-1)/(2*w+1)+1;
            }
            if (i == stations.length - 1) {
                if((n-now-w)%(float)(2*w+1)<0.5)
                    answer += (n-now-w)/(2*w+1);
                else
                    answer += (n-now-w)/(2*w+1)+1;
            }
        }
        return answer;
    }

    // JAVA는 float를 int로 형변환하면 자동 반올림된다.

    @Override
    public void run() {
        // System.out.println(solution(11,new int[]{4,11},1));
        System.out.println(solution(16, new int[]{9}, 2));
    }
}
