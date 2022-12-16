package week1004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.DelayQueue;

public class BOJ_21610_김아영 {

	static int N, M;
	static int map[][];
	static ArrayList<Point> arr;
	static int direct[][] = { { 0, 0 }, { 0, -1 }, { -1, -1 }, { -1, 0 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };
	static int directs[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
	static Queue<Point> rainCloud;

	static class Point {
		int d, s;

		public Point(int d, int s) {
			this.d = d;
			this.s = s;
		}

	}

	static void move(int m) {

		for (int i = 0; i < 4; i++) {
			Point p = rainCloud.poll();
			int dx = (p.d + (direct[arr.get(m).d][0] * (arr.get(m).s % N)) + N) % N;
			int dy = (p.s + (direct[arr.get(m).d][1] * (arr.get(m).s % N)) + N) % N;
			System.out.println(dx + " " + dy);
			map[dx][dy] = map[dx][dy] + 1;
			// 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
			// 그 자리 값 위치 기억하기
			rainCloud.add(new Point(dx, dy));
		}
	}

	static void waterMagic() {

		for (int i = 0; i < 4; i++) {
			Point p = rainCloud.poll();
			int cnt = 0;
			// 대각선
			for (int d = 0; d < 4; d++) {

				int dx = p.d + directs[i][0];
				int dy = p.s + directs[i][1];
				if (dx < 0 || dy < 0 || dx >= N || dy >= N)
					continue;
				if (map[dx][dy] >= 1)
					cnt++;
			}
			map[p.d][p.s] = map[p.d][p.s] + cnt;
			rainCloud.add(new Point(p.d, p.s));
		}

	}

	static void reduce() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] >= 2) {
					map[i][j] = map[i][j] - 2;
				}
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		arr = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			arr.add(new Point(d - 1, s - 1));
		}
		rainCloud = new LinkedList<>();
		rainCloud.add(new Point(N - 1, 0));
		rainCloud.add(new Point(N - 1, 1));
		rainCloud.add(new Point(N - 2, 0));
		rainCloud.add(new Point(N - 2, 1));
		// 입력 end

		for (int i = 0; i < M; i++) {
			move(i);
			waterMagic();
			reduce();
			print();
		}

		int ans = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ans = ans + map[i][j];
			}
		}
		System.out.println(ans);
	}
	
	static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("----------------------------------");
	}

}
