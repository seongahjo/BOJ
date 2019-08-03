package com.skel.algorithm.question;

public class QKakao2018A implements Question{
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        for(int i=0; i<n;i++){
            int wall = 0;
                                                                    for(int j=1; j<(1<<n);j=j<<1){
                wall += (arr1[i] & j) | (arr2[i] & j);
            }

            StringBuilder sb = new StringBuilder();
            for(int j=(1<<n-1); j>=1;j=j>>1){
                if((wall & j) == j){
                    sb.append("#");
                }else{
                    sb.append(" ");
                }
                answer[i]= sb.toString();
            }
        }
        return answer;
    }
    @Override
    public void run() {
        System.out.println(solution(5,new int[]{9, 20, 28, 18, 11},new  int[]{30, 1, 21, 17, 28}));
    }
}
