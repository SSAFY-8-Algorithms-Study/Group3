package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

// 회사 문화1
public class BOJ_14267_최규림 {

	static int N, M;
	static int[] arr;
	static ArrayList<Integer>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N + 1];
		graph = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (num == -1)
				continue;

			graph[num].add(i);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			arr[num] += w;
		}
		bfs(1, 0);

		for (int i = 1; i <= N; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	static void bfs(int i, int w) {
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.add(i);
		while (!q.isEmpty()) {
			int now = q.poll();
			for (int nxt : graph[now]) {
				arr[nxt] += arr[now];
				q.add(nxt);
			}
		}
	}
}
