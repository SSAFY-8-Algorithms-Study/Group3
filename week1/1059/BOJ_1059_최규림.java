package study;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_1059_최규림 {
	static int l, n;
	static int[] arr;

	public static void main(String[] args) {
		// 좋은 구간
		Scanner sc = new Scanner(System.in);
		l = sc.nextInt();
		arr = new int[l];
		for (int i = 0; i < l; i++) {
			arr[i] = sc.nextInt();
		}
		n = sc.nextInt();

		Arrays.sort(arr);
		int a = 0, b = 0;
		for (int i = 0; i < l - 1; i++) {
			//n이 배열 내에 존재한 경우, 구간 없음
			if(arr[i] == n || arr[i+1] == n) {
				System.out.println(0);
				return;
			}
			// 범위 확인
			if (arr[i] < n && arr[i + 1] > n) {
				a = arr[i] + 1;
				b = arr[i + 1] - 1;
				break;
			}
		}
		
		//배열 내의 숫자들 사이에 n이 없다면 앞 구간 확인(1~arr[0])
		if(a == 0) {			
			a = 1;
			b = arr[0] - 1;
		}
		
		int answer = 0;
		// 구간 존재하지 않는 경우
		if (a != b) {
			// a~n, a~n+1, ... a~b
			// a+1~n, a+1~n+1, ... a+1~b
			answer += (n - a) * (b - n + 1);
			answer += b - n; // n~b

		}
		System.out.println(answer);

	}
}
