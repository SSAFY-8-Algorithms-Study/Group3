package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 줄어들지 않아
public class BOJ_2688_최규림 {

	static int N;
	static long ans;
	static long[][] dp;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			ans = 0;
			N = Integer.parseInt(br.readLine());

			dp = new long[N + 1][10];
			Arrays.fill(dp[1], 1);

			for (int i = 2; i <= N; i++) { // i번째 자리수, i-1번째 값들 참고할 예정
				for (int j = 0; j <= 9; j++) { // i번째 자리수의 값 j
					for (int k = j; k <= 9; k++) { // 줄어들지 않은 수, j~k가 대상
						dp[i][j] += dp[i - 1][k];
					}
				}
			}

			for (int i = 0; i <= 9; i++) {
				ans += dp[N][i];
			}

			sb.append(ans + "\n");
		}
		
		System.out.println(sb.toString());
	}
}
