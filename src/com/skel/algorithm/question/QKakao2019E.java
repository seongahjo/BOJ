package com.skel.algorithm.question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QKakao2019E implements Question {

    class Page {
        int index;
        String name;
        double point;
        ArrayList<String> link;

        public Page(int index, String name, double point, ArrayList<String> link) {
            this.index = index;
            this.name = name;
            this.point = point;
            this.link = link;
        }
    }


    class Point implements Comparable<Point> {
        int index;
        double point = 0f;

        public Point(int index, double point) {
            this.index = index;
            this.point = point;
        }

        @Override
        public int compareTo(Point o) {
            int comp = Double.compare(o.point, point);
            return comp == 0 ? Integer.compare(index, o.index) : comp;
        }
    }

    ArrayList<Page> list = new ArrayList<>();

    public Page parse(String word, String page) {
        word = word.toLowerCase();
        page = page.toLowerCase();
        Matcher mm = Pattern.compile("<meta property=\"og:url\" content=\"https://([^>\"]*)\"/>", Pattern.DOTALL | Pattern.MULTILINE).matcher(page);
        Matcher ma = Pattern.compile("<a href=\"https://([^>\"]*)\">", Pattern.DOTALL | Pattern.MULTILINE).matcher(page);
        // 네임 파싱 O
        // 링크 파싱 O

        double count = 0;
        String name = mm.find() ? mm.group(1) : "";


        StringBuilder sb = new StringBuilder();
        sb.append("[^a-z]?");
        sb.append(word);
        sb.append("[^a-z]?");
        Matcher m = Pattern.compile(sb.toString(), Pattern.DOTALL | Pattern.MULTILINE).matcher(page);

        while (m.find()) {
            count++;
        }

//        int find = page.indexOf(word);
//        while (find != -1)
//        {
//            Character[] wordBorder = { page.charAt(find - 1), page.charAt(find + word.length()) };
//            if (Arrays.stream(wordBorder).anyMatch(ch -> ch.charValue() >= 'a' && ch.charValue() <= 'z') == false)
//                count++;
//            find = page.indexOf(word, find + 1);
//        }

        ArrayList<String> links = new ArrayList<>();
        while (ma.find()) {
            links.add(ma.group(1));
        }
        return new Page(list.size(), name, count, links);
    }

    public int solution(String word, String[] pages) {
        for (int i = 0; i < pages.length; i++) {
            list.add(parse(word, pages[i]));
        }
        ArrayList<Point> points = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Page from = list.get(i);
            Point p = new Point(i, from.point);
            for (int j = 0; j <list.size(); j++) {
                if (i == j) continue;
                Page to = list.get(j);
                for (String l : to.link) {
                    if (from.name.equals(l)) {
                        p.point += (to.point / to.link.size());
                    }
                }
            }
            points.add(p);
        }
        Collections.sort(points);
        return points.get(0).index;
    }

    @Override
    public void run() {
//        System.out.println(solution("test", new String[]{"", ""}));
//         System.out.println(solution("BlInd", new String[]{"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n</body>\n</html>"}));
        System.out.println(solution("muzi",new String[]{"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"}));

    }
}
