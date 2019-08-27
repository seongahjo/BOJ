package com.skel.algorithm.question;

import java.util.*;

public class QKakao2018AE implements Question {


    class Node {
        int index;
        char now;
        List<Node> children;

        public Node(char now) {
            this.now = now;
        }

        public Node add(int index, char next) {
            if (children == null) children = new ArrayList<>();
            Node n = new Node(next);
            n.index = index;
            children.add(n);
            return n;
        }

        public Node getNode(char c) {
            if (children == null) return null;
            for (Node n : children) {
                if (n.now == c) return n;
            }
            return null;
        }
    }

    Node tree = new Node('!');
    int lastIndex = 0;
    int indexs[] = new int[1000001];

    public Node makeTree(Node now, String s, int index) {
        if (s.length() <= index || now == null) return null;
        char charNow = s.charAt(index);
        Node nowNode = null;
        Node nextNode = now.getNode(charNow);
        if (nextNode == null) {
            indexs[lastIndex++]++;
            nowNode = now.add(lastIndex - 1, charNow);
        } else {
            indexs[nextNode.index]++;
            nowNode = nextNode;
        }
        return nowNode;
//        makeTree(nowNode, s, index + 1);
    }

    int result = 0;

    public void search(Node now, String s, int index) {
        if (s.length() <= index || now == null) return;
        char charNow = s.charAt(index);
        Node nextNode = now.getNode(charNow);
        Integer count = indexs[nextNode.index];
        result++;
        if (count > 1) {
            search(nextNode, s, index + 1);
        }
    }


    public int solution(String[] words) {
        for (int i = 0; i < words.length; i++) {
            Node now = tree;
            for (int j = 0; j < words[i].length(); j++) {
                now = makeTree(now, words[i], j);
            }
        }
        for (int i = 0; i < words.length; i++)
            search(tree, words[i], 0);
        int answer = result;
        return answer;
    }


//    class Node {
//        int index;
//        char now;
//        List<Node> children;
//
//        public Node(char now) {
//            this.now = now;
//        }
//
//        public Node add(int index, char next) {
//            if (children == null) children = new ArrayList<>();
//            Node n = new Node(next);
//            n.index = index;
//            children.add(n);
//            return n;
//        }
//
//        public Node getNode(char c) {
//            if (children == null) return null;
//            for (Node n : children) {
//                if (n.now == c) return n;
//            }
//            return null;
//        }
//    }
//
//    Map<String, Integer> map = new HashMap<>();
//
//    Node tree = new Node('!');
//    int lastIndex = 0;
//    int indexs[] = new int[1000001];
//
//    public void makeTree(Node now, String s, int index, String nextString) {
//        if (s.length() <= index || now == null) return;
//        char charNow = s.charAt(index);
//        Node nowNode = null;
//        Node nextNode = now.getNode(charNow);
//        nextString += charNow;
//
//        if (nextNode == null) {
//            indexs[lastIndex++]++;
//            map.put(nextString, 1);
//            nowNode = now.add(lastIndex - 1, charNow);
//        } else {
//            indexs[nextNode.index]++;
//            map.put(nextString, map.get(nextString) + 1);
//            nowNode = nextNode;
//        }
//    }
//
//    int result = 0;
//
//    public void search(Node now, String s, int index, String nextString) {
//        if (s.length() <= index || now == null) return;
//        char charNow = s.charAt(index);
//        Node nextNode = now.getNode(charNow);
//        nextString += charNow;
//        int count = map.get(nextString);
//        result++;
//        if (count > 1) {
//            search(nextNode, s, index + 1, nextString);
//        }
//    }
//
//
//    public int solution(String[] words) {
//        Arrays.sort(words, Comparator.reverseOrder());
//        for (int i = 0; i < words.length; i++)
//            for (int j = 0; j < words[i].length(); j++)
//                makeTree(tree, words[i], j, "");
//
//
//        for (int i = 0; i < words.length; i++) {
//            if (map.get(words[i]) > 1) {
//                result += words[i].length();
//            } else
//                search(tree, words[i], 0, "");
//        }
//        int answer = result;
//        return answer;
//    }


    @Override
    public void run() {
        //길이의 역순
        System.out.println(solution(new String[]{"a", "aa", "aaa", "aaaa"}));
    }
}
