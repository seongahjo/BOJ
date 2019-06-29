package com.skel.algorithm.question;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Q14500 implements Question {
    static class Shape {
        boolean shape[][];
        int sizeX;
        int sizeY;

        public Shape(boolean[][] shape) {
            this.shape = shape;
            sizeX = shape.length;
            sizeY = shape[0].length;
        }

    }

    static int N, M;
    static int map[][];
    static int mv[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static ArrayList<Shape> shape = new ArrayList<>();

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static {
        boolean[][] a = {{true, true, true, true}};
        shape.add(new Shape(a));
        boolean[][] b = {{true}, {true}, {true}, {true}};
        shape.add(new Shape(b));

        boolean[][] c = {{true, true}, {true, true}};
        shape.add(new Shape(c));

        boolean[][] d = {{true, false}, {true, false}, {true, true}};
        shape.add(new Shape(d));
        boolean[][] d2 = {{false, true}, {false, true}, {true, true}};
        shape.add(new Shape(d2));
        boolean[][] e = {{true, false, false}, {true, true, true}};
        shape.add(new Shape(e));
        boolean[][] e2 = {{true, true, true}, {true, false, false}};
        shape.add(new Shape(e2));
        boolean[][] f = {{true, true}, {false, true}, {false, true}};
        shape.add(new Shape(f));
        boolean[][] f2 = {{true, true}, {true, false}, {true, false}};
        shape.add(new Shape(f2));
        boolean[][] g = {{false, false, true}, {true, true, true}};
        shape.add(new Shape(g));
        boolean[][] g3 = {{true, true, true}, {false, false, true}};
        shape.add(new Shape(g3));

        boolean[][] h = {{true, false}, {true, true}, {false, true}};
        shape.add(new Shape(h));
        boolean[][] h2 = {{false,true}, {true, true}, {true, false}};
        shape.add(new Shape(h2));
        boolean[][] i = {{false, true, true}, {true, true, false}};
        shape.add(new Shape(i));
        boolean[][] i2 = {{true, true, false}, {false, true, true}};
        shape.add(new Shape(i2));

        boolean[][] j = {{true, true, true}, {false, true, false}};
        shape.add(new Shape(j));
        boolean[][] k = {{false, true}, {true, true}, {false, true}};
        shape.add(new Shape(k));
        boolean[][] k2 = {{true, false}, {true, true}, {true, false}};
        shape.add(new Shape(k2));
        boolean[][] l = {{false, true, false}, {true, true, true}};
        shape.add(new Shape(l));

    }

    static long add(Shape s, Point now) {
        long value = 0;
        int startX=now.x;
        int startY=now.y;
        int endX = startX + s.sizeX;
        int endY = startY + s.sizeY;
        for(int i=startX; i<endX; i++){
            for(int j=startY; j<endY;j++){
                if(s.shape[i-startX][j-startY])
                    value+=map[i][j];
            }
        }
        return value;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[500][500];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        int l = shape.size();
        long result = 0;

        for (int i = 0; i < l; i++) {
            Queue<Point> q = new LinkedList<>();
            boolean chk[][] = new boolean[500][500];
            Shape nowShape = shape.get(i);
            q.offer(new Point(0, 0));
            chk[0][0] = true;
            while (!q.isEmpty()) {
                Point now = q.poll();
                result = Math.max(add(nowShape, now), result);
                for (int j = 0; j < 4; j++) {
                    int rx = now.x + mv[j][0];
                    int ry = now.y + mv[j][1];
                    if (rx >= N || rx < 0 || ry >= M || ry < 0 || rx + nowShape.sizeX > N || ry + nowShape.sizeY > M || chk[rx][ry])
                        continue;
                    chk[rx][ry] = true;
                    q.offer(new Point(rx, ry));
                }
            }


        }
        System.out.println(result);
    }
}
