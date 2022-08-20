package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 절댓값 힙
public class BOJ_11286_최규림 {

	static int N;

	static class Num implements Comparable<Num> {
		int absNum, num;

		Num(int absNum, int num) {
			this.absNum = absNum;
			this.num = num;
		}

		@Override
		public int compareTo(Num o) {
			return this.absNum != o.absNum ? this.absNum - o.absNum : this.num - o.num;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		PriorityQueue<Num> q = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());
			if (x == 0) {
				if (q.isEmpty()) {
					sb.append(0).append('\n');
				} else {
					sb.append(q.poll().num).append('\n');
				}
			} else {
				q.add(new Num(Math.abs(x), x));
			}
		}

		System.out.println(sb.toString());
	}
}
