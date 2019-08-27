package com.skel.algorithm.question;

public class QWinter2018C implements Question {

    int sum[][];
    int result = 0;
    int N;


    public int solution(int[] cookie) {
        N = cookie.length;
        sum = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                if (j == i) {
                    sum[i][j] = cookie[j];
                    continue;
                }
                sum[i][j] = sum[i][j - 1] + cookie[j];
            }
        }
        for (int mid = 0; mid < N; mid++) {
            int left = mid;
            int right = mid + 1;
            while (!(left < 0 || right >= N || !((left <= mid) && (mid + 1 <= right)))) {
                int leftSum = sum[left][mid];
                int rightSum = sum[mid + 1][right];
                if (leftSum == rightSum) {
                    result = Math.max(result, leftSum);
                }
                if (leftSum < rightSum) {
                    left -= 1;
                } else if (leftSum > rightSum) {
                    right += 1;
                } else {
                    left -= 1;
                    right += 1;
                }
            }
        }
        // 왼쪽 줄어드는건 내가 클경우
        // 오른쪽 넓어지는건 내가 작을경우

        int answer = result;
        return answer;
    }

    @Override
    public void run() {
        System.out.println(solution(new int[]{1, 1, 2, 3}));
    }
}
