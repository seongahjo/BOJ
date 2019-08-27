package com.skel.algorithm.question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class QKakao2018AC implements Question {


    class File implements Comparable<File> {
        int index;
        String HEAD;
        int NUMBER;
        String TAIL;

        public File(int index, String input) {
            this.index = index;
            int l = input.length();
            int i = 0, j = 0;
            for (i = 0; i < l; i++) {
                if (Character.isDigit(input.charAt(i)))
                    break;
            }
            HEAD = input.substring(0, i).toLowerCase();
            for (j = i; j < l; j++) {
                if (!Character.isDigit(input.charAt(j))) break;
            }
            NUMBER = Integer.parseInt(input.substring(i, j));
            TAIL = input.substring(j, l);
        }

        @Override
        public int compareTo(File o) {
            if (HEAD.compareTo(o.HEAD) == 0) {
                if (Integer.compare(this.NUMBER, o.NUMBER) == 0) {
                    return Integer.compare(index, o.index);
                }
                return Integer.compare(this.NUMBER, o.NUMBER);
            } else
                return HEAD.compareTo(o.HEAD);
        }
    }
    ArrayList<File> fs = new ArrayList<>();
    public String[] solution(String[] files) {
        for(int i=0; i<files.length;i++){
            fs.add(new File(i,files[i]));
        }
        Collections.sort(fs);

        String[] answer = new String[files.length];
        for(int i=0; i<files.length;i++){
            answer[i] = files[fs.get(i).index];
        }
        return answer;
    }


    @Override
    public void run() {
        Arrays.stream(solution(new String[]{"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"})).forEach(System.out::println);
    }
}
