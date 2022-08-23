package week4;

import java.util.Scanner;

public class BOJ_2960_김아영 {

	public static boolean isPrime(int n) {
		
		for(int i = 2; i < n; i++) {
			if (n % i == 0)return false;
		}
		return true;
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		boolean arr[] = new boolean[N + 1];
		int cnt = 0;
		
		for(int i = 2;i <= N; i++) {
			if (!arr[i] && isPrime(i)) {
				for(int j = i; j <= N; j = j + i) {
					if (!arr[j]) {
						arr[j] = true;
						cnt++;
						if (cnt == K) {
							System.out.println(j);
							return;
						}	
					} 
				}
			}
		}
	}
}
