package week7;

import java.util.Scanner;

// 돌 게임
public class BOJ_9655_최규림 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		System.out.println(N % 2 == 1 ? "SK" : "CY");
	}
}
