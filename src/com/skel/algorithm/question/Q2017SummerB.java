package com.skel.algorithm.question;

public class Q2017SummerB implements Question {

    public int solution(int[] nums) {

        int maxNum = 50000;
        boolean check[] = new boolean[50001];

        for (int i = 2; i <= Math.sqrt(maxNum); i++) {
            for (int j = i; j * i <= maxNum; j++) {
                check[i * j] = true;
            }
        }
        int answer = 0;
        for(int i=0; i<nums.length;i++){
            for(int j=i+1; j< nums.length;j++){
                for(int h= j+1;h<nums.length;h++){
                    if(!check[nums[i]+nums[j]+nums[h]])answer++;
                }
            }
        }

        return answer;
    }

    @Override
    public void run() {

    }
}
