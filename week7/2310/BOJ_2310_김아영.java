package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_2310_김아영 {

	static int N, result;
	static ArrayList<Integer>[] list;
	static boolean[] visit;
	static List<Point> info;

	static class Point {
		char name;
		int x;

		public Point(char name, int x) {
			this.name = name;
			this.x = x;
		}
	}

	static void dfs(int cur, int cnt) {

		if (result == 1 || cur > N)
			return;
		if (cur == N) {
			result = 1;
			return;
		}

		for (int idx : list[cur]) {
			if (visit[idx])
				continue;
			if (info.get(idx).name == 'E') {
				visit[idx] = true;
				dfs(idx, cnt);
				visit[idx] = false;
			} else if (info.get(idx).name == 'L') {
				// 만약에 내 현재 금액보다 L이 줄 수 있는 금액이 더 크면 갱신
				if (info.get(idx).x > cnt) {
					visit[idx] = true;
					dfs(idx, info.get(idx).x);
					visit[idx] = false;
				} else {
					visit[idx] = true;
					dfs(idx, cnt);
					visit[idx] = false;
				}
			} else if (info.get(idx).name == 'T') {
				// 뺏을 수 있는 경우만 넣어주기
				if (info.get(idx).x <= cnt) {
					visit[idx] = true;
					dfs(idx, cnt - info.get(idx).x);
					visit[idx] = false;
				} else
					return;
			}
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);

		while (true) {
			N = sc.nextInt();
			if (N == 0)
				break;
			// 미로 방의 정보
			info = new ArrayList<>();
			// 미로 방에서 갈 수 있는 곳들
			list = new ArrayList[N + 1];
			visit = new boolean[N + 1];
			for (int i = 0; i <= N; i++) {
				list[i] = new ArrayList();
			}
			info.add(new Point('E', 0));
			for (int i = 1; i <= N; i++) {
				char a = sc.next().charAt(0);
				int b = sc.nextInt();
				info.add(new Point(a, b));
				while (true) {
					int to = sc.nextInt();
					if (to == 0)
						break;
					list[i].add(to);
				}
			}
			// 입력 end
			// 만약에 0 이 트롤이고 소지금이 0이 아닌 경우 바로 no하기
			// 아니면 bfs
			if (info.get(1).name == 'T' && info.get(1).x > 0) {
				System.out.println("No");
				return;
			}
			result = 0;
			visit[1] = true;
			dfs(1, info.get(1).x);

			if (result == 0) {
				System.out.println("No");
			} else {
				System.out.println("Yes");
			}

		}

	}

}
