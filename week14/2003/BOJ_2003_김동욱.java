package boj;

import java.util.Scanner;

public class BOJ_2003_김동욱 {
	public static void main(String[] args) {
		int answer = 0;
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();

		int[] num = new int[10001];
		for (int i = 0; i < N; i++) {
			num[i] = sc.nextInt();
		}
		int start = 0;
		int end = 0;
		int sum = 0;
		while (true) {
			if (sum >= M) { // 구간 안에 더한 값이 M이거나 크면
				sum -= num[start]; // start 값 빼고 start 이동
				start++;
			} else if (end == N) // end가 N 이면 break;
				break;
			else {
				sum += num[end];
				end++;
			}
			if (sum == M)
				answer++;
		}
		System.out.println(answer);
	}
}
