package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_9205_김아영 {

	static int N;
	static int homex, homey, rockx, rocky;
	static List<Point> arr;
	// static int beerBox[] = { 20, 0 }; // 채워짐, 빈병

	static class Point implements Comparable<Point> {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			if (this.x > o.x)
				return 1;
			else if (this.x < o.x)
				return -1;
			else {
				if (this.y >= o.y)
					return 1;
				else
					return -1;
			}
		}

	}

	static boolean bfs() {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(homex, homey));

		while (!q.isEmpty()) {
			Point now = q.poll();
			int d = Math.abs(now.x - rockx) + Math.abs(now.y - rocky);
			if (d <= 1000) {
				return true;
			}
			for (int i = arr.size() - 1; i >= 0; i--) {
				int cnt = Math.abs(now.x - arr.get(i).x) + Math.abs(now.y - arr.get(i).y);
				if (cnt <= 1000) {
					q.add(new Point(arr.get(i).x, arr.get(i).y));
					arr.remove(i);
				}
			}
		}

		return false;

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			homex = Integer.parseInt(st.nextToken());
			homey = Integer.parseInt(st.nextToken());
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				arr.add(new Point(a, b));
			}
			st = new StringTokenizer(br.readLine());
			rockx = Integer.parseInt(st.nextToken());
			rocky = Integer.parseInt(st.nextToken());
			// 입력

			if (bfs()) {
				System.out.println("happy");
			} else
				System.out.println("sad");

		}

	}

}
