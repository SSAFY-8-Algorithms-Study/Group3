package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 걷기
public class BOJ_1459_최규림 {
	static long X, Y, W, S, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		X = Integer.parseInt(st.nextToken()); // 집 위치
		Y = Integer.parseInt(st.nextToken()); // 집 위치
		W = Integer.parseInt(st.nextToken()); // 한 블록 이동 걸리는 시간
		S = Integer.parseInt(st.nextToken()); // 대각선 가로지르는 시간

		long D1 = Math.min(S, W * 2); // 대각선 최소 시간
		long D2 = Math.min(W * 2, S * 2); // 2블록 최소 시간
		long min = Math.min(X, Y);
		long max = Math.max(X, Y);
		long rest = max - min;

		ans += min * D1; // 대각선 이동
		ans += (rest / 2) * D2; // 2블록 이동
		ans += (rest % 2) * W; // 1블록 이동
		System.out.println(ans);
	}
}
