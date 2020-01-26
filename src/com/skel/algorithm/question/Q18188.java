package com.skel.algorithm.question;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Q18188 implements Question {
	static int H, W, N;
	static char map[][] = new char[21][21];

	static class Point implements Comparable<Point> {
		int x;
		int y;
		int cnt;
		List<Character> move;

		public Point(int x, int y, int cnt, List<Character> move) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.move = move;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(cnt, o.cnt);
		}
	}


	static class Move {
		List<Integer> innerMove = new ArrayList<>();

		public Move(char first, char second) {
			innerMove.add(convertToInt(first));
			innerMove.add(convertToInt(second));
		}

	}

	static int mv[][] = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};


	static int convertToInt(char c) {
		int mv = -1;
		switch (c) {
			case 'W':
				mv = 2;
				break;
			case 'A':
				mv = 3;
				break;
			case 'S':
				mv = 0;
				break;
			case 'D':
				mv = 1;
				break;
		}
		return mv;
	}

	static char convertToChar(int i) {
		char move = ' ';
		switch (i) {
			case 0:
				move = 'S';
				break;
			case 1:
				move = 'D';
				break;
			case 2:
				move = 'W';
				break;
			case 3:
				move = 'A';
				break;
		}
		return move;
	}

	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		H = sc.nextInt();
		W = sc.nextInt();
		Point start = null;
		int endX = -1, endY = -1;
		for (int i = 1; i <= H; i++) {
			String s = sc.next();
			for (int j = 1; j <= W; j++) {
				map[i][j] = s.charAt(j - 1);
				if (map[i][j] == 'D') {
					start = new Point(i, j, 0, new ArrayList<>());
				} else if (map[i][j] == 'Z') {
					endX = i;
					endY = j;
				}
			}
		}

		N = sc.nextInt();
		List<Move> m = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String s = sc.next();
			String s2 = sc.next();
			m.add(new Move(s.charAt(0), s2.charAt(0)));
		}

		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.offer(start);
		while (!pq.isEmpty()) {
			Point now = pq.poll();
			if (now.cnt > N) break;
			if (now.x == endX && now.y == endY) {
				System.out.println("YES");
				for (char i : now.move) {
					System.out.printf("%c", i);
				}
				return;
			}
			if (m.size() > now.cnt) {
				Move mv = m.get(now.cnt);
				for (int mvValue : mv.innerMove) {
					Point p = move(now, mvValue);
					if (p != null) pq.offer(p);
				}
			} else {
				for (int i = 0; i < 4; i++) {
					Point p = move(now, i);
					if (p == null) continue;
					pq.offer(p);
				}
			}

		}
		System.out.println("NO");
	}

	static Point move(Point p, int m) {
		int rx = p.x + mv[m][0];
		int ry = p.y + mv[m][1];
		if (rx < 1 || rx > H || ry < 1 || ry > W || map[rx][ry] == '@') return null;
		List<Character> newMove = new ArrayList<>(p.move);
		newMove.add(convertToChar(m));
		return new Point(rx, ry, p.cnt + 1, newMove);
	}
}
