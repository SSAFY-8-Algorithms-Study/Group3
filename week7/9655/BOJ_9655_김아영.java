package week7;

import java.util.Scanner;

public class BOJ_9655_김아영 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		// 창영이 시작
		int result = 0;
		int arr[] = {3,1};
		int index = 0;
		
		while (true) {
			
			if (N == 0)break;
			if (N < arr[index]) {
				index = 1;
			}
			N = N - arr[index];
			result++;
			
		}

		if (result % 2 == 0) System.out.println("CY");
		else System.out.println("SK");
			
	}

}
