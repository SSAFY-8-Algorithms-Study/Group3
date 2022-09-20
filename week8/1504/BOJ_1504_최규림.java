package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 특정한 최단 경로
public class BOJ_1504_최규림 {

	static int N, E, v1, v2, result1, result2;
	static ArrayList<ArrayList<Point>> list;

	static class Point implements Comparable<Point> {
		int target, dist;

		Point(int target, int dist) {
			this.target = target;
			this.dist = dist;
		}

		@Override
		public int compareTo(Point o) {
			return this.dist - o.dist;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		list = new ArrayList<ArrayList<Point>>();
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list.get(a).add(new Point(b, c));
			list.get(b).add(new Point(a, c));
		}

		st = new StringTokenizer(br.readLine(), " ");
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());

		// 1 -> v1
		int r1 = dijkstra(1, v1);
		// v1 -> v2
		int r2 = dijkstra(v1, v2);
		// v2 -> N
		int r3 = dijkstra(v2, N);
		if (r1 == Integer.MAX_VALUE || r2 == Integer.MAX_VALUE || r3 == Integer.MAX_VALUE) {
			result1 = Integer.MAX_VALUE;
		} else {
			result1 = r1 + r2 + r3;
		}		
		
		
		// 1 ->v2
		r1 = dijkstra(1, v2);
		// v2 -> v1
		r2 = dijkstra(v2, v1);
		// v1-> N
		r3 = dijkstra(v1, N);
		if (r1 == Integer.MAX_VALUE || r2 == Integer.MAX_VALUE || r3 == Integer.MAX_VALUE) {
			result2 = Integer.MAX_VALUE;
		} else {
			result2 = r1 + r2 + r3;
		}

		if (result1 == Integer.MAX_VALUE && result2 == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(Math.min(result1, result2));
		}

	}

	static int dijkstra(int start, int end) {
		int[] distance = new int[N + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		boolean[] visited = new boolean[N + 1];

		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.add(new Point(start, 0));
		distance[start] = 0;

		while (!pq.isEmpty()) {
			Point point = pq.poll();
			int target = point.target;

			if (visited[target])
				continue;
			visited[target] = true;
			for (Point p : list.get(target)) {
				if (visited[p.target])
					continue;
				if (distance[p.target] > distance[target] + p.dist) {
					distance[p.target] = distance[target] + p.dist;
					pq.add(new Point(p.target, distance[p.target]));
				}
			}

		}

		return distance[end];
	}

}
