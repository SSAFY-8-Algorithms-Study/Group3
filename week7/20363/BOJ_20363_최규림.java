package week7;

import java.util.Scanner;

// 당근 키우기
public class BOJ_20363_최규림 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt(); // 씨앗
		int y = sc.nextInt(); // 온기
		int max = Math.max(x, y);
		int min = Math.min(x, y);
		System.out.println(max + min / 10 + min);

	}
}
