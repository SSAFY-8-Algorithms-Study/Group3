package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class test2573 {

	static int N,M;
	static int map[][];
	static int check[][];
	static int direct[][] = {{0,1},{0,-1},{1,0},{-1,0}};
	
	static void dfs(int level, int x, int y) {
		
		for(int i =0 ;i< 4;i++) {
			int dx = x + direct[i][0];
			int dy = y + direct[i][1];
			if (dx < 0 || dy < 0 || dx >= N || dy >= M)continue;
			if (check[dx][dy] != 0) {
				check[dx][dy] = 0;
				dfs(level + 1,dx, dy);
			}
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i =0;i<N;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int result = 0;
		while (true) {
			// 1년
			int ifice = 0;
			check = new int[N][M];
			for(int i =0;i<N;i++) {
				for(int j=0;j<M;j++) {
					int cnt_zero=0;
					if (map[i][j] != 0) {
						for(int k =0 ;k< 4;k++) {
							int dx = i + direct[k][0];
							int dy = j + direct[k][1];
							if (dx < 0 || dy < 0 || dx >= N || dy >= M)continue;
							if (map[dx][dy]==0) {
								cnt_zero++;
							}
						}
						int a = map[i][j];
						check[i][j]= a - cnt_zero;
						if (check[i][j] < 0) {
							check[i][j] = 0;
						}
						ifice++;
					}
				}
			}
			if (ifice == 0 ) {
				System.out.println(0);
				return;
			}
		
			for(int i =0;i<N;i++) {
				for(int j=0;j<M;j++) {
					map[i][j]=check[i][j];
				}
			}

			// 년수
			result++;
			// 붙어 있는지 체크
			int cnt = 0;		
			for(int i =0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if (check[i][j] != 0) {
						dfs(0,i,j);
						cnt++;
					}
					if (cnt >= 2) {
						System.out.println(result);
						return;
					}
				}
			}
			
			if (cnt >= 2) {
				System.out.println(result);
				return;
			}
				
		}
		
	}

}
