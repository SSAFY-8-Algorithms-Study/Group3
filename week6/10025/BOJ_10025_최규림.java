package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10025_최규림 {

	static int N, K, answer;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new int[1000001];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int g = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());

			dp[x] = g;
		}

		// 좌우 -> 우
		K = K * 2 + 1;

		int result = 0;
		for (int d = 0; d <= 1000000; d++) {
			// 지속적으로 더해주고
			result += dp[d];
			
			// d-K 만큼의 범위 밖 값들을 빼줌, 위에서 지속적으로 더해왔기 때문
			if (d >= K) {
				result -= dp[d - K];
			}
			answer = Math.max(answer, result);
		}

		System.out.println(answer);
	}
}
