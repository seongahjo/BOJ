package com.skel.algorithm.question;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Q17090 implements Question {
	static int N, M;
	static char map[][] = new char[501][501];

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}


	static int mv[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};


	static int chartoint(char c) {
		switch (c) {
			case 'U':
				return 0;
			case 'R':
				return 1;
			case 'D':
				return 2;
			case 'L':
				return 3;
		}
		return -1;
	}

	static int head[] = new int[501 * 502 + 3];

	static int find(int i) {
		if (head[i] == i) return i;
		head[i] = find(head[i]);
		return head[i];
	}

	static final int G = 501 * 502 + 1;
	static final int NG = 501 * 502 + 2;
	static char rand[] = new char[]{'U','R','L','D'};
	static void union(int i, int j) {
		i = find(i);
		j = find(j);
		if (i == j) return;
		if (i == G || i == NG) {
			int tmp = i;
			i = j;
			j = tmp;
		}
		head[i] = j;
	}

	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		for (int i = 0; i < N; i++) {
			String s = sc.next();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
			}
		}
//		N = 500;
//		M = 500;
//		for(int i=0; i<N;i++){
//			for(int j=0; j<M;j++){
//				map[i][j] = rand[(int)(Math.random()%4)];
//			}
//		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				head[i * M + j] = i * M + j;
			}
		}
		head[G] = G;
		head[NG] = NG;
		int cnt = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int nowIndex = i * M + j;
				int nowHead = find(nowIndex);
				if (nowHead == nowIndex) {
					Queue<Point> q = new LinkedList<>();
					q.offer(new Point(i, j));
					while (!q.isEmpty()) {
						Point now = q.poll();
						int m = chartoint(map[now.x][now.y]);
						int nextX = now.x + mv[m][0];
						int nextY = now.y + mv[m][1];
						int nextIndex = nextX * M + nextY;

						if ( nextX < 0 || nextY < 0 || nextX >= N || nextY >= M ) {
							union(nowIndex, G);
							cnt++;
							break;
						}
						int nextHead = find(nextIndex);
						if(nextHead == G){
							union(nowIndex,G);
							cnt++;
							break;
						}
						if (nextHead == NG || nextHead != nextIndex) {
							union(nowIndex, NG);
							break;
						}
						union(nowIndex, nextIndex);
						q.offer(new Point(nextX, nextY));
					}
				} else if (nowHead == G) {
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
}
