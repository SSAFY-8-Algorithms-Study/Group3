package week6;

import java.util.Scanner;

public class BOJ_10162_김아영 {

	static int arr[] = {300,60,10};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int result[] = new int[3];
		result[0] = N / arr[0];
		N = N - (arr[0] * result[0]);
		result[1] = N / arr[1];
		N = N - (arr[1] * result[1]);
		result[2] = N / arr[2];
		N = N - (arr[2] * result[2]);
		if (N == 0) {
			for(int i=0;i<3;i++) {
				System.out.print(result[i] + " ");
			}
		}
		else {
			System.out.println(-1);
		}
	}

}
