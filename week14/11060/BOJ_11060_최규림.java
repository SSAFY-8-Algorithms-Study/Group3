package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 점프 점프
public class BOJ_11060_최규림 {

	static int N, ans;
	static int[] A, dp;
	static int maxValue = 100 * 1000 + 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
			
		
		A = new int[N];
		dp = new int[N];
		Arrays.fill(dp, maxValue);

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		if(N == 1) {
			System.out.println(0);
			return;
		}
		
		dp[0] = 0;
		ans = maxValue;
		for (int i = 0; i < N; i++) {
			if (dp[i] == maxValue)
				continue;

			for (int j = 1; j <= A[i]; j++) {
				if (!checkRange(i + j)) {
					ans = Math.min(ans, dp[i] + 1);
					
				} else {
					dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
				}
			}
		}

		System.out.println(ans == maxValue ? -1 : ans);
	}

	static boolean checkRange(int idx) {
		return idx < N - 1;
	}
}
