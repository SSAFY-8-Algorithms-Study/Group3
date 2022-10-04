package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//케이크 자르기
public class BOJ_17179_최규림 {

	static int N, M, L, answer;
	static int[] cuts;
	static boolean[] used;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		cuts = new int[M];
		for (int i = 0; i < M; i++) {
			cuts[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 0; i < N; i++) {
			answer = 0;
			int q = Integer.parseInt(br.readLine());

			int left = 0, right = 4000000;
			
			while (left <= right) {
				int mid = (left + right) / 2;
				if (calc(mid, q)) {
					answer = mid;
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}

			System.out.println(answer);
		}

	}

	static boolean calc(int value, int q) {
		int cnt = 0;
		int slice = value;

		for (int i = 0; i < M; i++) {
			if (cuts[i] < slice)
				continue;
			if (cuts[i] + value > L)
				break;

			slice = cuts[i] + value;
			cnt++;
		}
		return q <= cnt;
	}

}
