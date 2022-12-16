package week1004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1670_김아영 {

	static int N, L, P, ans;
	static ArrayList<Point> arr;

	static class Point implements Comparable<Point> {
		int a, b;

		public Point(int a, int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo(Point o) {
			return this.a - o.a;
		}

	}


	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new ArrayList<>();
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr.add(new Point(a, b));
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		// 입력 end
		// 거리순으로 정렬 됨
		Collections.sort(arr);

		// 멀리가는 주요소중 가장 연료많은 곳으로 간다.
		for (int i = 0; i < N; i++) {
			if (arr.get(i).a > P) { // 이 주요소까지는 못왔다는 뜻
				int s = pq.size();
				for (int j = 0; j < s; j++) {
					P = P + pq.poll();
					ans++;
					if (arr.get(i).a <= P) break;
				}
			}
			pq.add(arr.get(i).b);
		}

		if (L > P) { // 이 주요소까지는 못왔다는 뜻
			int s = pq.size();
			for (int j = 0; j < s; j++) {
				P = P + pq.poll();
				ans++;
				if (L <= P) {
					System.out.println(ans);
					return;
				}
			}
			System.out.println(-1);
		}
		else {
			System.out.println(ans);
			return;
		}
	}

}
