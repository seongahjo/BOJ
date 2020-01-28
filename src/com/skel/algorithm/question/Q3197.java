package com.skel.algorithm.question;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Q3197 implements Question {
	static class Point implements Comparable<Point> {
		int x;
		int y;
		int cnt;

		public Point(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(cnt, o.cnt);
		}
	}


	@Override
	public void run() {
		List<Point> birds = new ArrayList<>();
		InputReader sc = new InputReader(System.in);
			int R = sc.readInt();
			int C = sc.readInt();
			int mv[][] = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
			char map[][] = new char[1501][1501];
			int mapIce[][] = new int[1501][1501];
			for (int i = 0; i < R; i++) {
				String s = sc.readString();
				for (int j = 0; j < C; j++) {
					map[i][j] = s.charAt(j);
					if (map[i][j] == 'L') {
						birds.add(new Point(i, j, 0));
					}
				}
			}

			PriorityQueue<Point> pq = new PriorityQueue<>();
			boolean check[][] = new boolean[1501][1501];
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (!check[i][j] && map[i][j] != 'X') {
						pq.offer(new Point(i, j, 0));
						check[i][j] = true;
					}
				}
			}



			int max = 0;
			while (!pq.isEmpty()) {
				Point now = pq.poll();
				max = Math.max(max,now.cnt);
				for (int h = 0; h < 4; h++) {
					int rx = now.x + mv[h][0];
					int ry = now.y + mv[h][1];
					if (rx < 0 || ry < 0 || rx >= R || ry >= C || check[rx][ry]) continue;
					check[rx][ry] = true;
					mapIce[rx][ry] = now.cnt + (map[rx][ry] == 'X' ? 1 : 0);
					pq.offer(new Point(rx, ry, mapIce[rx][ry]));
				}
			}
			int l = 0, r = 8123;
			int result = 0;
			Point start = birds.get(0);
			Point end = birds.get(1);
			while (l <= r) {
				int mid = (l + r) >> 1; // 걸리는 날
				Queue<Point> q = new LinkedList<>();
				boolean c[][] = new boolean[1501][1501];
				q.offer(start);
				boolean isSuccess = false;
				while (!q.isEmpty()) {
					Point now = q.poll();
					if (now.x == end.x && now.y == end.y) {
						isSuccess = true;
						break;
					}
					for (int i = 0; i < 4; i++) {
						int rx = now.x + mv[i][0];
						int ry = now.y + mv[i][1];
						if (rx < 0 || ry < 0 || rx >= R || ry >= C || c[rx][ry] || mapIce[rx][ry] > mid) continue;
						c[rx][ry] = true;
						q.offer(new Point(rx, ry, 0));
					}
				}
				if (isSuccess) {
					result = mid;
					r = mid - 1;
				} else {
					l = mid + 1;
				}
			}
			System.out.println(result);
	}

	private static class InputReader {
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;
		private SpaceCharFilter filter;

		public InputReader(InputStream stream) {
			this.stream = stream;
		}

		public int read() {
			if (numChars == -1) {
				throw new InputMismatchException();
			}
			if (curChar >= numChars) {
				curChar = 0;
				try {
					numChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (numChars <= 0) {
					return -1;
				}
			}
			return buf[curChar++];
		}

		public int readInt() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9') {
					throw new InputMismatchException();
				}
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public String readString() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isSpaceChar(c));
			return res.toString();
		}

		public double readDouble() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			double res = 0;
			while (!isSpaceChar(c) && c != '.') {
				if (c == 'e' || c == 'E') {
					return res * Math.pow(10, readInt());
				}
				if (c < '0' || c > '9') {
					throw new InputMismatchException();
				}
				res *= 10;
				res += c - '0';
				c = read();
			}
			if (c == '.') {
				c = read();
				double m = 1;
				while (!isSpaceChar(c)) {
					if (c == 'e' || c == 'E') {
						return res * Math.pow(10, readInt());
					}
					if (c < '0' || c > '9') {
						throw new InputMismatchException();
					}
					m /= 10;
					res += (c - '0') * m;
					c = read();
				}
			}
			return res * sgn;
		}

		public long readLong() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9') {
					throw new InputMismatchException();
				}
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public boolean isSpaceChar(int c) {
			if (filter != null) {
				return filter.isSpaceChar(c);
			}
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		public String next() {
			return readString();
		}

		public interface SpaceCharFilter {
			public boolean isSpaceChar(int ch);
		}
	}
}
