package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

// 효율적인 해킹
public class BOJ_1325_최규림 {

	static int N, M, maxValue;

	static class Computer {
		int idx, cnt;
		List<Integer> list = new ArrayList<>();
	}

	static boolean[] visited;

	static Computer[] computers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		computers = new Computer[N + 1];

		for (int i = 1; i <= N; i++) {
			computers[i] = new Computer();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			computers[a].list.add(b);
		}

		int maxValue = 0;
		for (int i = 1; i <= N; i++) {
			visited = new boolean[N + 1];
			visited[i] = true;
			dfs(i);
		}

		for (int i = 1; i <= N; i++) {
			maxValue = Math.max(maxValue, computers[i].cnt);
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			if (computers[i].cnt == maxValue) {
				sb.append(i + " ");
			}
		}
		System.out.println(sb.toString());
	}

	static void dfs(int idx) {
		computers[idx].cnt += 1;
		List<Integer> temp = computers[idx].list;
		for (int i = 0; i < temp.size(); i++) {
			if (visited[temp.get(i)])
				continue;
			visited[temp.get(i)] = true;
			dfs(temp.get(i));
		}
	}

}
