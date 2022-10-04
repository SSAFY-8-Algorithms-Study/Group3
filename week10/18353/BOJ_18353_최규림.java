package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 병사 배치하기
public class BOJ_18353_최규림 {

	static int N, answer;
	static int[] arr, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		dp = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		calc();
		System.out.println(N - answer - 1);

//		print();
	}

	static void calc() {
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				// 내림차순인 경우
				if (arr[i] < arr[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
					answer = Math.max(answer, dp[i]);
				}
			}
		}
	}

	static void print() {
		for (int i = 0; i < N; i++) {
			System.out.print(dp[i] + " ");
		}
		System.out.println();
	}
}
