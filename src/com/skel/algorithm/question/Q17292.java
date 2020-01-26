package com.skel.algorithm.question;

import java.util.*;


public class Q17292 implements Question {
	static class Card implements Comparable<Card> {
		int number;
		char color;

		public Card(int number, char color) {
			this.number = number;
			this.color = color;
		}

		@Override
		public int compareTo(Card o) {
			return Integer.compare(o.number, number);
		}

		@Override
		public String toString() {
			if (number >= 10) {
				return String.valueOf((char) ('a' + (number - 10))) + color;
			}
			return String.valueOf(number) + color;
		}
	}

	static class CardPair implements Comparable<CardPair> {
		Card one;
		Card two;
		int weight;

		public CardPair(Card one, Card two) {
			this.one = one;
			this.two = two;
			calWeight();
		}

		public Card big() {
			if (one.number > two.number) {
				return one;
			} else return two;
		}

		public Card small() {
			if (one.number > two.number) {
				return two;
			} else return one;
		}

		public void calWeight() {
			if (Math.abs(one.number - two.number) == 1 || Math.abs(one.number - two.number) == 14)
				weight += 1;
			weight *= 10;
			if (Math.abs(one.number - two.number) == 0)
				weight += 1;
			weight *= 10;
			if (one.color == two.color)
				weight += 1;
			weight *= 100;
			weight += big().number;
			weight *= 100;
			weight += small().number;
			weight *= 10;
			weight += big().color == 'b' ? 1 : 0;
		}


		@Override
		public int compareTo(CardPair o) {
			return Integer.compare(o.weight, weight);
		}

		@Override
		public String toString() {
			return one.toString() + two.toString();
		}
	}

	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(sc.next(), ",");
		List<Card> list = new ArrayList<>();
		while (st.hasMoreTokens()) {
			String val = st.nextToken();
			int sub = val.charAt(0) - '0' >= 10 ? ('a' - 10) : '0';
			list.add(new Card(val.charAt(0) - sub, val.charAt(1)));
		}
		PriorityQueue<CardPair> pq = new PriorityQueue<>();
		for (int i = 0; i < 6; i++) {
			for (int j = i + 1; j < 6; j++) {
				pq.offer(new CardPair(list.get(i), list.get(j)));
			}
		}

		while (!pq.isEmpty()) {
			CardPair cp = pq.poll();
			System.out.println(cp.toString());
		}
	}
}
