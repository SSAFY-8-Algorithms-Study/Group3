package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 부분수열의 합
public class BOJ_14225_최규림 {
	static int N;
	static int[] arr;
	static boolean[] selected, visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		arr = new int[N];
		selected = new boolean[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		visited = new boolean[100000 * N + 1];

		subset(0, 0);
		for (int i = 1; i < visited.length; i++) {
			if (!visited[i]) {
				System.out.println(i);
				return;
			}
		}
	}

	static void subset(int idx, int cnt) {
		if (cnt == N + 1) {
			return;
		}

		if (idx == N) {
			int temp = 0;
			for (int i = 0; i < N; i++) {
				if (selected[i]) {
					temp += arr[i];
				}
			}
			visited[temp] = true;
			return;
		}

		selected[idx] = true;
		subset(idx + 1, cnt + 1);
		selected[idx] = false;
		subset(idx + 1, cnt);

	}
}
