package week2;

import java.util.Scanner;

public class BOJ2563 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int map[][] = new int[101][101];
		int N = sc.nextInt();
		int sum = 0;
		
		for(int n = 0 ; n <N ; n++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			for(int i = x; i < x+10; i++) {
				for(int j = y; j < y+10; j++) {
					if (map[i][j] == 0) {
						sum = sum + 1;
						map[i][j] = 1;
					} 
				}
			}
		}
		System.out.println(sum);
	}
}
