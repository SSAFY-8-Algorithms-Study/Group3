package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 막대기
public class BOJ_17608_최규림 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		int answer = 1;
		int target = arr[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			if (arr[i] > target) {
				target = arr[i];
				answer++;
			}
		}

		System.out.println(answer);
	}
}
