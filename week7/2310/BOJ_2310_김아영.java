import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	static int N, result;
	static ArrayList<Integer>[] list;
	static boolean[] visit;
	static List<Point> info;

	static class Point {
		int idx;
		char name;
		int x;
		int cnt;

		public Point(int idx, char name, int x, int cnt) {
			this.idx = idx;
			this.name = name;
			this.x = x;
			this.cnt = cnt;
		}
	}

	static void bfs() {

		Queue<Point> q = new LinkedList<>();
		if (info.get(1).name == 'T' && info.get(1).x > 0) return;
		q.offer(new Point(1, info.get(1).name, info.get(1).x, info.get(1).cnt));
		visit[1] = true;
		result = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for(int i = 0; i < size; i++) {
				Point cur = q.poll();
				visit[cur.idx] = true;
				if (cur.idx == N) {result = 1; return;}
				// 내가 갈 수 있는 곳들을 list에서 방문하기
				for(int idx : list[cur.idx]) {
					if (visit[idx])continue;
					if (info.get(idx).name == 'E') {
						q.add(new Point(idx, info.get(idx).name, info.get(idx).x, cur.cnt));
					}
					else if (info.get(idx).name == 'L') {
						// 만약에 내 현재 금액보다 L이 줄 수 있는 금액이 더 크면 갱신
						if (info.get(idx).x > cur.cnt) {
							q.add(new Point(idx, info.get(idx).name, info.get(idx).x, info.get(idx).x));
						}
						else {
							q.add(new Point(idx, info.get(idx).name, info.get(idx).x, cur.cnt));
						}
					}
					else if (info.get(idx).name == 'T') {
						// 뺏을 수 있는 경우만 넣어주기
						if (info.get(idx).x <= cur.cnt) {
							q.add(new Point(idx, info.get(idx).name, info.get(idx).x, cur.cnt - info.get(idx).x));
						}
					}
				}
				// System.out.println(cur.idx);
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
			info.add(new Point(0, 'E', 0, 0));
			for (int i = 0; i < N; i++) {
				char a = sc.next().charAt(0);
				int b = sc.nextInt();
				info.add(new Point(i + 1, a, b, 0));
				while (true) {
					int to = sc.nextInt();
					if (to == 0)
						break;
					list[i + 1].add(to);
				}
			}
			// 입력 end
			// 만약에 0 이 트롤이고 소지금이 0이 아닌 경우 바로 no하기
			// 아니면 bfs
			bfs();
			
			if (result == 0) {
				System.out.println("No");
			}
			else {
				System.out.println("Yes");
			}

		}

	}

}
