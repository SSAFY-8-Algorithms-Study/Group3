package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 나무 자르기
public class BOJ_2805_최규림 {

	static int N, M, answer, left, right;
	static int[] trees;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		trees = new int[N];
		st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			right = Math.max(right, trees[i]);
		}

	
		binarySearch(left, right);
		System.out.println(answer);

	}

	static void binarySearch(int left, int right) {

		if (left > right) {
			return;
		}

		int mid = (left + right) / 2;
		double temp = 0;
		for (int i = 0; i < N; i++) {
			temp += Math.max(trees[i] - mid, 0);
		}

		if (temp >= M) {
			answer = Math.max(answer, mid);
			binarySearch(mid + 1, right);
		} else {
			binarySearch(left, mid - 1);
		}
	}
}
