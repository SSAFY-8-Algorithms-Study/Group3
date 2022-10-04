package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17179_김아영 {
	static StringBuilder sb = new StringBuilder();
	static int N, M, L;
	static int[] S, Q;

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		S = new int[M];
		Q = new int[N];
		for (int i = 0; i < M; i++)
			S[i] = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++)
			Q[i] = Integer.parseInt(br.readLine());
	}

	static boolean determination(int K, int n) {
		// 최소 K 길이로 케이크를 잘랐을 때, n 이상을 얻을 수 있으면 true, 없으면 false를 return
		int cnt = 0;
		int slice = K;
		for (int i = 0; i < M; i++) {
			if (S[i] < slice)
				continue;
			if (S[i] + K > L)
				break;
			slice = S[i] + K;
			cnt++;
		}
		return n <= cnt;
	}

	static void pro() {
		
		for (int t = 0; t < N; t++) {
			int L = 0, R = 4000000, ans = 0;
			// [L ... R] 범위 안에 정답이 존재
			while (L <= R) {
				int mid = (L + R) / 2;
				if (determination(mid, Q[t])) {
					ans = mid;
					L = mid + 1;
				} else {
					R = mid - 1;
				}
			}
			sb.append(ans).append('\n');
		}
		System.out.print(sb.toString());
	}

	public static void main(String[] args) throws IOException {
		input();
		pro();
	}
}
