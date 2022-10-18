package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

// 거짓말
public class BOJ_1043_최규림 {

	static int N, M, ans;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> graph;
	static Queue<Integer> q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N + 1];

		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		st = new StringTokenizer(br.readLine(), " ");
		int smartCnt = Integer.parseInt(st.nextToken());
		q = new ArrayDeque<>();

		// 진실을 아무도 모르는 경우
		if (smartCnt == 0) {
			System.out.println(M);
			return;
		}

		// 진싫을 아는 사람 초기화
		for (int i = 0; i < smartCnt; i++) {
			int num = Integer.parseInt(st.nextToken());
			q.add(num);
			visited[num] = true;
		}

		int[][] partArr = new int[M][];

		// 각 파티별 케이스
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int cnt = Integer.parseInt(st.nextToken());

			int[] temp = new int[cnt];
			for (int j = 0; j < cnt; j++) {
				temp[j] = Integer.parseInt(st.nextToken());
			}
			partArr[i] = temp;

			// 관계 연결
			for (int j = 0; j < cnt; j++) {
				for (int k = 0; k < cnt; k++) {
					if (j != k) {
						graph.get(temp[j]).add(temp[k]);
					}
				}
			}

		}

		spread(); // 소문 퍼짐

		// 다시 파티별 확인
		for (int i = 0; i < M; i++) {
			int[] temp = partArr[i];
			for (int num : temp) {
				if (visited[num]) {
					ans++; // 거짓말 할 수 없는 횟수 증가
					break;
				}
			}
		}

		System.out.println(M - ans); // 파티 횟수 - 거짓말 불가능한 파티 횟수

	}

	static void spread() {
		while (!q.isEmpty()) {
			int now = q.poll();
			for (int nxt : graph.get(now)) {
				if (!visited[nxt]) {
					visited[nxt] = true;
					q.add(nxt);
				}
			}
		}
	}
}
