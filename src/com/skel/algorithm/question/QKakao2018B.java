package com.skel.algorithm.question;

import java.util.*;

public class QKakao2018B implements Question {

    class Food implements Comparable<Food> {
        int index;
        long count;

        public Food(int index, int count) {
            this.index = index;
            this.count = count;
        }

        @Override
        public int compareTo(Food o) {
            if (count == o.count) return Integer.compare(index, o.index);
            return Long.compare(count, o.count);
        }
    }


    //20만 , nlogn
    // k 초에 먹는거
    // 주기 t
    // 3 , 2 , 1


    ArrayList<Food> reverse = new ArrayList<>();
    Map<Long, Integer> check = new HashMap<>();
    boolean endIndex[] = new boolean[200001];

    public int solution(int[] food_times, long k) {
        int max = 0;
        for (int i = 0; i < food_times.length; i++) {
            reverse.add(new Food(i + 1, food_times[i]));
            //
            max = Math.max(food_times[i], max);
        }

        Collections.sort(reverse);
        // 3 , 2 , 1
        // 1   2 3

        // 빼고 남은애들
        // reverse 작 -> 큰
        // list 큰 -> 작


        long before = 0;

        for (int i = 0; i < reverse.size(); i++) {
            // reverse.size() - i 가 현재 남아있는 개수
            // 1번 부터
            if (before == reverse.get(i).count){  endIndex[reverse.get(i).index] = true; continue;}
            int size = reverse.size() - i;
            long now = size * (reverse.get(i).count - before);
            // 0 ~ 3 4일경우

            // 이 주기
            // 이 주기에 포함
            if (k < now) {
                // size 개수 만큼 있고 그 중 하나임
                // 1 2 3 4 5 6        40 % 6 => 4
                int index = (int) (k % size);
                int cnt = 0;
                for (int j = 1; j <= food_times.length; j++) {
                    if (endIndex[j]) continue;
                    if (cnt++ == index) {
                        return j;
                    }
                }
                // 1 번 인덱스
            }
            k -= now;
            before = reverse.get(i).count;
            endIndex[reverse.get(i).index] = true;
        }

        int answer = -1;

        // cnt

        return answer;
    }

    @Override
    public void run() {
        System.out.println(solution(new int[]{1,1,3,3}, 5));
    }
    // 1 2 3 4
    //     3 4
    //     3 4
    // 1,2,3,4

}
