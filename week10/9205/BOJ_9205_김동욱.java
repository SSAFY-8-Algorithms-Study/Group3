package boj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_9205_kdw {

	static ArrayList<Point> coords;
	static ArrayList<ArrayList<Integer>> adjList;
	static StringBuilder sb;

	public static class Point {
		int x;
		int y;

		public Point() {
		}

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}

	}

	public static int dist(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();
		int testcase = sc.nextInt();
		for (int tc = 0; tc < testcase; tc++) {
			int store = sc.nextInt();
			coords = new ArrayList<>();
			adjList = new ArrayList<>();
			for (int i = 0; i < store + 2; i++) {

				coords.add(new Point(sc.nextInt(), sc.nextInt()));
				adjList.add(new ArrayList<>());
			} // input end
			
			for (int i = 0; i < store + 2; i++) {
				for (int j = i + 1; j < store + 2; j++) {
					Point p1 = coords.get(i);
					Point p2 = coords.get(j);
					
					if (dist(p1, p2) <= 1000) {
						
						adjList.get(i).add(j);
						adjList.get(j).add(i);
					}
				}
			} // adjList end
			bfs(adjList, store);
			
			
		}
		 System.out.println(sb);
	}

	public static void bfs(ArrayList<ArrayList<Integer>> adjList, int store) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[store + 2];
		queue.add(0);
		visited[0] = true;
		while (!queue.isEmpty()) {
			int tempPoint = queue.poll();
			if (tempPoint == store + 1) {
				sb.append("happy\n");
				return;
			}

			for (int num : adjList.get(tempPoint)) {
				if (visited[num])
					continue;
				visited[num] = true;
				queue.add(num);
			}
		}
		sb.append("sad\n");
		return;
	}

}
