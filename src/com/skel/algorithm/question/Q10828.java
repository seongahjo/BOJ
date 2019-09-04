package com.skel.algorithm.question;

import java.util.Scanner;

public class Q10828 implements Question {
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int arr[] = new int[10001];
        int pointer = -1;
        for (int i = 0; i < N; i++) {
            String command = sc.next();
            switch (command) {
                case "push":
                    arr[++pointer] = sc.nextInt();
                    break;
                case "pop":
                    if (pointer == -1)
                        System.out.println(-1);
                    else
                        System.out.println(arr[pointer--]);
                    break;
                case "size":
                    System.out.println(pointer+1);
                    break;
                case "empty":
                    if (pointer == -1)
                        System.out.println(1);
                    else
                        System.out.println(0);
                    break;
                case "top":
                    if (pointer == -1)
                        System.out.println(-1);
                    else
                        System.out.println(arr[pointer]);
                    break;
            }
        }
    }
}
