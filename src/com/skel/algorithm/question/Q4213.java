package com.skel.algorithm.question;

import java.util.*;

public class Q4213 implements Question {

    static class Point implements Comparable<Point> {
        int index;
        int length;

        public Point(int index, int length) {
            this.index = index;
            this.length = length;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(length, o.length);
        }
    }

    static int checkSequence(int sequenceArr[]){
        int num=0;
        for(int i=0; i<sequenceArr.length-1;i++){
            if(sequenceArr[i] == sequenceArr[i+1]-1) num++;
        }
        return num;
    }
    // split n
    // merge n+1 => 2n+1
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        int caseNum = 0;
        while (sc.hasNext()) {
            int n;
            int arr[][];
            boolean check[][];
            int answer = 0;
            List<Integer> length = new ArrayList<>();
            List<Point> sequence = new ArrayList<>();
            n = sc.nextInt();
            int lengthCount[] = new int[10001];
            arr = new int[n][];
            boolean lengthCheck[] = new boolean[10001];
            check = new boolean[10001][51];
            answer += n - 1;
            for (int i = 0; i < n; i++) {
                int h = sc.nextInt();
                arr[i] = new int[h];

                int before = -1;
                for (int j = 0; j < h; j++) {
                    arr[i][j] = sc.nextInt();
                    if (before == -1) {
                        before = arr[i][j];
                        lengthCount[arr[i][j]]++;
                    } else if (before != arr[i][j]) {
                        answer += 2;
                        before = arr[i][j];
                        lengthCount[arr[i][j]]++;
                    }
                    length.add(arr[i][j]);
                    check[arr[i][j]][i] = true;
                }
                sequence.add(new Point(i, checkSequence(arr[i])));
            }
            // 80퍼 슈밤,,..


            List<Integer> realLength = new ArrayList<>(new HashSet<>(length));
            Collections.sort(realLength);
            // 1
            // 1 2 3
            // 2 3
            //  => 4
            // split 3 merge 5
            //


            // split 2 merge 4
            // -4
            // => 2

            // 92퍼

            // 인덱스 매칭 경우의 수
            Collections.sort(sequence);
            for (Point p : sequence) {
                int nowIndex = p.index;
                for (int i = 0; i < realLength.size() - 1; i++) {
// 1 2 2 3
                    int nowLength = realLength.get(i);
                    int nextLength = realLength.get(i + 1);
                    if (lengthCheck[nowLength]) continue;
                    if (check[nowLength][nowIndex] && check[nextLength][nowIndex]) {
                        answer -= 2;
                        lengthCheck[nowLength] = true;
                        check[nowLength][nowIndex] = false;
                        check[nextLength][nowIndex] = false;
                        if (lengthCount[nextLength] == 1) {
                            check[nextLength][nowIndex] = true;
                        }

                    }
                }
            }
            // 인덱스마다 플레이트 번호로?>?

            // 일단 다 나눔
            // (1,1) (1,2), (1,3) (2,1) (2,2) (2,3) (3,3) (3,1) (3,2)---이 되야댐..
            // 1 2 3 (1,1) (2,1) (3,1)
            // 4 5 6  (1,2) (2,2 (3,2)
            // 7 8 9  (1,1) (2,1) (3,1)
            // 예외  -> 같은 경우가 있을경우.... 3개 이상부터 문
            // 1 ~ 3
            // 1 ~ 3
            // 1 ~ 3
            // 1 , 2 , 3 을 다 분리,,..

            // 같은 숫자가 여러개면 한번에 합체

            System.out.printf("Case %d: %d\n", ++caseNum, answer);
        }
    }
}
