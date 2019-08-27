package com.skel.algorithm.question;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class QKakao2019C implements Question {
    static class Point {
        int i;
        int x;
        int y;

        public Point(int i, int x, int y) {
            this.i = i;
            this.x = x;
            this.y = y;
        }
    }

    static class Node {
        Point now;
        Node left;
        Node right;

        public Node(Point now) {
            this.now = now;
        }
    }

    private Node tree;
    private ArrayList<Point> list = new ArrayList<>();
    private ArrayList<ArrayList<Point>> level = new ArrayList<>();
    private int maxHeight = 0;
    // left <= now <=  right
    // 0 , 14
    // 0, 8 // 8, 14

    public void makeTree(Node now, int left, int right) {
        int nowLevel = now.now.y;
        int nextLevel = nowLevel-1;
        int nowX = now.now.x;
        for (int i = nowLevel - 1; i >= 0; i--) {
            if (!level.get(i).isEmpty()) {
                nextLevel = i;
                break;
            }
        }
        if(nextLevel==-1) return;

        // 0 이면 계속 자기자신이 나오니까... 그런걸
        // nextLevel로 감
        ArrayList<Point> nextList = level.get(nextLevel);
        // left right
        for (Point p : nextList) {
            if (left <= p.x && p.x <= nowX) {
                now.left = new Node(p);
                makeTree(now.left, left, nowX);
            } else if (nowX <= p.x && p.x <= right) {
                now.right = new Node(p);
                makeTree(now.right, nowX, right);
            }
        }
    }

    // 중 좌 우
    public void pre(Node n, ArrayList<Integer> pre) {
        if(n == null) return;
        pre.add(n.now.i+1);
        pre(n.left, pre);
        pre(n.right, pre);
    }
/// 왼 우 중
    public void post(Node n , ArrayList<Integer> post){
        if(n == null) return;
        post(n.left,post);
        post(n.right,post);
        post.add(n.now.i+1);
    }
    public int[][] solution(int[][] nodeinfo) {
        int l = nodeinfo.length;
        for (int i = 0; i <= 100000; i++) {
            level.add(new ArrayList<>());
        }
        for (int i = 0; i < l; i++) {
            level.get(nodeinfo[i][1]).add(new Point(i, nodeinfo[i][0], nodeinfo[i][1]));
            maxHeight = Math.max(nodeinfo[i][1], maxHeight);
            list.add(new Point(i, nodeinfo[i][0], nodeinfo[i][1]));
        }

        list.sort((p1, p2) -> {
            if (p2.y == p1.y) return Integer.compare(p1.x, p2.x);
            return Integer.compare(p2.y, p1.y);
        });

        tree = new Node(level.get(maxHeight).get(0));
        makeTree(tree, 0, 100000);

        ArrayList<Integer> preList = new ArrayList<>();
        ArrayList<Integer> postList = new ArrayList<>();
        pre(tree,preList);
        post(tree,postList);

        int[][] answer = new int[2][l];
        answer[0] = preList.stream().mapToInt(i->i).toArray();
        answer[1] = postList.stream().mapToInt(i->i).toArray();



        return answer;
    }

    @Override
    public void run() {
        solution(new int[][]{{0, 3},{1,2},{2,1}});
    }
}
