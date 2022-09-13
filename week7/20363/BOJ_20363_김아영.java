package week7;

import java.util.Scanner;

public class BOJ_20363_김아영 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int a = sc.nextInt();
		int b = sc.nextInt();
		int MIN = Math.min(a, b);
		
		int result = a + b + (MIN / 10);
		System.out.println(result);

	}

}
