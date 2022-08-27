package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 근손실
public class BOJ_18429_최규림 {
	static int N, K, answer;
	static int[] kits;
	static int[] result;
	static boolean[] used;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		kits = new int[N];
		result = new int[N];
		used = new boolean[N];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			kits[i] = Integer.parseInt(st.nextToken());
		}

		perm(0);
		System.out.println(answer);
	}

	static void perm(int idx) {
		if (idx == N) {

			int status = 500;
			for (int i = 0; i < N; i++) {
				status = status - K + result[i];
				if (status < 500) {
					break;
				}
			}

			if (status >= 500) {
				answer++;
			}

			return;
		}

		for (int i = 0; i < N; i++) {
			if (used[i])
				continue;

			result[idx] = kits[i];
			used[i] = true;
			perm(idx + 1);
			used[i] = false;
		}
	}
}
