package com.skel.algorithm.question;

import java.util.Scanner;

public class Q11366 implements Question {

	static class Value {
		long a1;
		long a2;
		long b1;
		long b2;

		public Value(long a1, long a2, long b1, long b2) {
			this.a1 = a1;
			this.a2 = a2;
			this.b1 = b1;
			this.b2 = b2;
		}


		public Value(Value v) {
			this(v.a1, v.a2, v.b1, v.b2);
		}

		public void multiply(Value v) {
			long newA1 = a1 * v.a1 + b1 * v.a2;
			long newA2 = a1 * v.b1 + b1 * v.b2;
			long newB1 = a2 * v.a1 + b2 * v.a2;
			long newB2 = a2 * v.b1 + b2 * v.b2;
			this.a1 = newA1;
			this.a2 = newA2;
			this.b1 = newB1;
			this.b2 = newB2;
		}

		public long getValue(long val1, long val2) {
			return a1 * val1 + b1 * val2;
		}
	}

	static final Value first = new Value(1, 1, 1, 0);

	public static Value getExp(int num) {
		if (num == 1) return new Value(first);
		if (num % 2 == 0) {
			Value val = getExp(num / 2);
			val.multiply(val);
			return val;
		}
		Value v = getExp(num - 1);
		v.multiply(first);
		return v;
	}

	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		int a, b, c;
		do {
			a = sc.nextInt();
			b = sc.nextInt();
			c = sc.nextInt();
			if (a == 0 && b == 0 && c == 0) break;
			long value = getExp(c).getValue(b, a);
			System.out.println(value);
		} while (true);
	}
}
