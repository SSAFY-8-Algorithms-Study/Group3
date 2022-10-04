package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 계단 오르기
public class BOJ_2579_최규림 {
	static int N;
	static int[] steps;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		steps = new int[N + 1];
		dp = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			steps[i] = Integer.parseInt(br.readLine());
		}

		if (N == 1) {
			System.out.println(steps[1]);
		} else if (N == 2) {
			System.out.println(steps[1] + steps[2]);
		} else if (N == 3) {
			System.out.println(Math.max(steps[1] + steps[3], steps[2] + steps[3]));
		} else {
			dp[1] = steps[1];
			dp[2] = steps[1] + steps[2];
			dp[3] = Math.max(steps[1] + steps[3], steps[2] + steps[3]);

			for (int i = 4; i <= N; i++) {
				int result1 = dp[i - 3] + steps[i - 1] + steps[i];
				int result2 = dp[i - 2] + steps[i];
				dp[i] = Math.max(result1, result2);
			}

			System.out.println(dp[N]);
		}

	}
}
