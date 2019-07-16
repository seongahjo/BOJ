package com.skel.algorithm.question;

import java.util.Scanner;

public class Q2630 implements Question {
    static int N;
    static int arr[][]=new int[128][128];

    static int b =0, w = 0;

    static boolean check(int n , int x,int y){
        int c = arr[x][y];
        int offset = n;
        for(int i=0; i<offset;i++){
            for(int j=0; j<offset;j++){
                if(arr[x+i][y+j]!=c){
                   return false;
                }
            }
        }
        return true;
    }   
    static void divide(int n,int x,int y){
        int offset = n/2;
        if (check(n, x, y)) {
            if(arr[x][y]==1) b++;
            else w++;
            return;
        }
        divide(n/2, x,y);
    divide(n/2,x+offset,y);
    divide(n/2,x,y+offset);
    divide(n/2,x+offset,y+offset);

    }
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        N=sc.nextInt();
        for(int i=0; i<N;i++)
            for(int j=0; j<N;j++)
                arr[i][j]=sc.nextInt();
        divide(N,0,0);
        System.out.println(w);
        System.out.println(b);

    }
}
