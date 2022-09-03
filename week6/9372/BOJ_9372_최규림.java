package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 상근이의 여행
public class BOJ_9372_최규림 {

	static int T, answer;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> graph;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			graph = new ArrayList<>();
			visited = new boolean[n + 1];
			answer = 0;
			for (int i = 0; i < n + 1; i++) {
				graph.add(new ArrayList<>());
			}

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				graph.get(a).add(b);
				graph.get(b).add(a);
			}

			visited[1] = true;
			dfs(1);
			sb.append(answer).append('\n');
		}
		System.out.println(sb.toString());

	}

	static void dfs(int idx) {
		for (int i = 0; i < graph.get(idx).size(); i++) {
			if (!visited[graph.get(idx).get(i)]) {
				visited[graph.get(idx).get(i)] = true;
				answer++;
				dfs(graph.get(idx).get(i));
			}
		}
	}
}
