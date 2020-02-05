package com.skel.algorithm.question;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q14647 implements Question {
	public static int getNine(int v) {
		String s = String.valueOf(v);
		int l = s.length();
		int num = 0;
		for (int i = 0; i < l; i++) {
			if (s.charAt(i) == '9')
				num++;
		}
		return num;
	}

	static class Point {
		int value = 0;

	}

	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int map[][] = new int[501][501];
		List<Point> vertical = new ArrayList<>();
		List<Point> horizontal = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			horizontal.add(new Point());
		}
		for (int i = 0; i < m; i++) {
			vertical.add(new Point());
		}
		int all = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
				int nine = getNine(map[i][j]);
				all += nine;
				Point p = horizontal.get(i);
				p.value += nine;

				p = vertical.get(j);
				p.value += nine;
			}
		}

		int max = 0;
		for (int i = 0; i < n; i++)
			max = Math.max(horizontal.get(i).value, max);
		for (int i = 0; i < m; i++)
			max = Math.max(vertical.get(i).value, max);
		System.out.println(all - max);
	}
}
