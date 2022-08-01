package week1;

import java.util.Scanner;

public class test2468 {

	static int map[][];
	static int direct[][] = {{0,1},{0,-1},{1,0},{-1,0}};
	static int N;
	static int check[][];
	
	
	public static void dfs(int level, int x, int y, int cur) {
		
		for(int i =0 ;i< 4;i++) {
			int dx = x + direct[i][0];
			int dy = y + direct[i][1];
			if (dx < 0 || dy < 0 || dx >= N || dy >= N)continue;
			if (check[dx][dy] != cur) {
				check[dx][dy]=cur;
				dfs (level + 1, dx, dy, cur);
			}
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int MIN = Integer.MAX_VALUE;
		int MAX = Integer.MIN_VALUE;
		map = new int[N+1][N+1];
		for(int i=0;i<N;i++) {
			for(int j =0;j<N;j++) {
				map[i][j]=sc.nextInt();
				if (map[i][j]<MIN) {
					MIN = map[i][j];
				}
				if (map[i][j]>MAX) {
					MAX = map[i][j];
				}
			}
		}
		if (MIN == MAX) {
			System.out.println(1);
			return;
		}
		
		int result = 0;
		for(int k = MIN;k<=MAX;k++) {
			check = new int[N+1][N+1];
			for(int i=0;i<N;i++) {
				for(int j =0;j<N;j++) {
					if (map[i][j] <= k) {
						check[i][j] = k;
					}
				}
			}
			
			int cnt = 0;
			for(int i=0;i<N;i++) {
				for(int j =0;j<N;j++) {
					if (check[i][j] != k) {					
						dfs(0,i,j,k);
						cnt++;
					}
				}
			}

			if (result < cnt) {
				result = cnt;
			}
		}
		System.out.println(result);
		
		
	}

}
