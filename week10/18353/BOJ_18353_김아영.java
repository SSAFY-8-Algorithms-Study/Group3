package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_18353_김아영 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N];
		int memo[] = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int cnt = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			memo[i] = 1;
			for (int j = 0; j < i; j++) {
				if (arr[j] > arr[i]) {
					memo[i] = Math.max(memo[i], memo[j] + 1);
				}
				cnt = Math.min(cnt, N - memo[i]);
			}
		}
		// System.out.println(Arrays.toString(memo));
		if (cnt == Integer.MAX_VALUE) {
			System.out.println(0);
		}
		else System.out.println(cnt);
	}

}
