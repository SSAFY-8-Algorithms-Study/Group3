package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2003_최규림 {
	static int N, M, ans;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		long result = 0;
		int left = 0, right = 0;
		while (left < N && right < N) {
			long temp = result + arr[right];

			if (temp < M) {
				result += arr[right];
				right++;
			} else if (temp > M) {
				result -= arr[left];
				left++;
			} else {
				ans++;
				result -= arr[left];
				left++;
			}

		}
		System.out.println(ans);
	}
}
