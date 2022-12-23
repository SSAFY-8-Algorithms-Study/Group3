package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2688_김지희 {
// 줄어들지 않아
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		long[][] dp = new long[65][10];
		// dp[i][j] => j로 끝나는 줄어들지 않는 i자리 수

		// 한 자리일 때, 경우의 수 -> 1
		Arrays.fill(dp[1], 1);
		// 두자리 수 ~ 64자리 수
		for (int i = 2; i <= 64; i++) {
			dp[i][0] = 1;
			int tmp = 0;
			for (int j = 1; j < 10; j++) { // j로 끝남.
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= TC; tc++) {
			int n = Integer.parseInt(br.readLine());
			long sum = 0;
			for(int i=0; i<10; i++) {
				sum+= dp[n][i];
			}
			sb.append(sum).append("\n");
		}
		System.out.println(sb.toString());
	}
}
