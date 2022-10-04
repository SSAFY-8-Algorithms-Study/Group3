package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 맥주 마시면서 걸어가기
public class BOJ_9205_최규림 {

	static int t, n;
	static String answer;
	static boolean[] visited;

	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static Point house, rock;
	static Point[] stores;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			answer = "sad";
			n = Integer.parseInt(br.readLine());
			stores = new Point[n];

			st = new StringTokenizer(br.readLine(), " ");
			house = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				stores[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			st = new StringTokenizer(br.readLine(), " ");
			rock = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			if (calcDistance(house, rock) <= 1000) {
				answer = "happy";
			} else {
				visited = new boolean[n];
				bfs();
//				for (int i = 0; i < n; i++) {
//					if (answer.equals("sad")) {
//						visited[i] = true;
//						dfs(house, stores[i]);
//						visited[i] = false;
//					} else {
//						break;
//					}
//				}
			}

			System.out.println(answer);
		}
	}

	static void bfs() {
		Queue<Point> q = new ArrayDeque<>();
		q.add(house);
		visited = new boolean[n];

		while (!q.isEmpty()) {
			Point now = q.poll();
			if (calcDistance(now, rock) <= 1000) {
				answer = "happy";
				return;
			}

			for (int i = 0; i < n; i++) {
				if (!visited[i]) {
					if (calcDistance(now, stores[i]) <= 1000) {
						visited[i] = true;
						q.add(stores[i]);
					}
				}
			}
		}
	}

//	static void dfs(Point start, Point end) {
//		// 다음 편의점까지 이동 불가한 경우
//		if (calcDistance(start, end) > 1000) {
//			return;
//		}
//
//		// 다음 편의점에서 페스티벌 가능한 경우
//		if (calcDistance(end, rock) <= 1000) {
//			answer = "happy";
//			return;
//		}
//
//		// 다른 편의점으로 이동
//		for (int i = 0; i < n; i++) {
//			if (!visited[i]) {
//				visited[i] = true;
//				dfs(end, stores[i]);
//				visited[i] = false;
//			}
//		}
//	}

	static int calcDistance(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}
}
