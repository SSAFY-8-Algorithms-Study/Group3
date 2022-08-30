package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_16235 {
	static int N, M, K;
	static int addmap[][];
	static int map[][];
	static List<Point> arr;
	static int result;
	static int direct[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 1 }, { -1, -1 }, { 1, -1 }, { -1, 1 } };

	static class Point implements Comparable<Point> {
		int x, y, age;

		public Point(int x, int y, int age) {
			this.x = x;
			this.y = y;
			this.age = age;
		}

		@Override
		public int compareTo(Point o) {
			return this.age - o.age;
		}

	}

	static void treegrow() {

		PriorityQueue<Point> pq = new PriorityQueue<>();
		for (int i = 0; i < arr.size(); i++) {
			pq.add(new Point(arr.get(i).x, arr.get(i).y, arr.get(i).age));
		}

		int Kcnt = 0;
		while (!pq.isEmpty()) {
			List<Point> copy = new ArrayList<>();
			List<Point> die = new ArrayList<>();

			int size = pq.size();
			if (Kcnt == K) {
				result = size;
				break;
			}
			Kcnt++;
			for (int s = 0; s < size; s++) {
				Point cur = pq.poll();
				// 봄 => 자신의 나이만큼 양분 먹고 나이 + 1, 양분 못먹으면 죽는다.
				if (map[cur.x][cur.y] - cur.age >= 0) {
					map[cur.x][cur.y] = map[cur.x][cur.y] - cur.age;
					copy.add(new Point(cur.x, cur.y, cur.age + 1));
					cur.age++;
					// 가을
					if (cur.age % 5 == 0) {
						for (int d = 0; d < 8; d++) {
							int dx = cur.x + direct[d][0];
							int dy = cur.y + direct[d][1];
							if (dx < 0 || dy < 0 || dx >= N || dy >= N)
								continue;
							copy.add(new Point(dx, dy, 1));
						}
					}
//					System.out.println("---------------------------" + Kcnt);
//					System.out.println(cur.x + " " + cur.y + " " + cur.age);
//					for (int i = 0; i < N; i++) {
//						System.out.println(Arrays.toString(map[i]));
//					}

				} // 여름 만약에 못먹으면 죽고 그 맵에 양분으로 추가
				else {
					die.add(new Point(cur.x, cur.y, cur.age));
				}
			} // end size
			for(int i=0;i<die.size();i++) {
				map[die.get(i).x][die.get(i).y] = map[die.get(i).x][die.get(i).y] + die.get(i).age / 2;
			}
			for (int i = 0; i < copy.size(); i++) {
				pq.add(new Point(copy.get(i).x, copy.get(i).y, copy.get(i).age));
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = map[i][j] + addmap[i][j];
				}
			}

		} // end while
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		addmap = new int[N][N];
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				addmap[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5;
			}
		}
		arr = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			arr.add(new Point(r - 1, c - 1, k));
		}
		Collections.sort(arr);
		treegrow();
		System.out.println(result);

	}

}
