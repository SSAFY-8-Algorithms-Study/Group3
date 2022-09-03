package week6;

import java.util.Scanner;

// 전자레인지
public class BOJ_10162_최규림 {

	static int T;
	static int[] arr = { 300, 60, 10 };
	static int[] cntArr = { 0, 0, 0 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		for (int i = 0; i < 3; i++) {
			int temp = T / arr[i];
			cntArr[i] += temp;
			T -= arr[i] * temp;
		}

		if (T != 0) {
			System.out.println(-1);
		} else {
			for (int cnt : cntArr) {
				System.out.print(cnt + " ");
			}
		}
	}
}
