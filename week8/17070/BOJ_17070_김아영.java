package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17070_김아영 {

	static int N;
	static int map[][];
	static int visit[][];
	static int direct[][] = {{0,1},{1,0},{1,1}};
	static int arr1[] = {0,2};
	static int arr2[] = {1,2};
	static int arr3[] = {0, 1, 2};
	static int cnt;
	

	static void checkno(int x, int y, int level) {
		
		if (level == 0) {
			visit[x][y] = 1;
		}
		else if (level == 1) {
			visit[x][y] = 1;
		}else if (level == 2) {
			visit[x][y] = 1;
			int dx = x + -1;
			int dy = y + 0;
			if (!(dx <0 || dy <0 || dx >= N || dy >= N)) {
				visit[dx][dy] = 1;
			}
			dx = x + 0;
			dy = y + -1;
			if (!(dx <0 || dy <0 || dx >= N || dy >= N)) {
				visit[dx][dy] = 1;
			}
		}
		
	}

	static void checkyes(int x, int y, int level) {
		
		if (level == 0) {
			visit[x][y] = 0;
		}
		else if (level == 1) {
			visit[x][y] = 0;
		}else if (level == 2) {
			visit[x][y] = 0;
			int dx = x + -1;
			int dy = y + 0;
			if (!(dx <0 || dy <0 || dx >= N || dy >= N)) {
				visit[dx][dy] = 0;
			}
			dx = x + 0;
			dy = y + -1;
			if (!(dx <0 || dy <0 || dx >= N || dy >= N)) {
				visit[dx][dy] = 0;
			}
		}
		
	}
	
	static void dfs(int x, int y, int level) {
		
		if (x == N - 1 && y == N- 1 ) {
			cnt++;
			return;
		}
		// System.out.println(x + " , "+ y + " : " + level);
		if (level == 0) {
			for(int i=0;i<2;i++) {
				int dx = x + direct[arr1[i]][0];
				int dy = y + direct[arr1[i]][1];
				if (dx <0 || dy <0 || dx >= N || dy >= N) continue;
				if (visit[dx][dy] == 1)continue;
				if (map[dx][dy] == 1)continue;
				checkno(dx, dy, level);
				dfs(dx, dy, arr1[i]);
				checkyes(dx, dy, level);
				
			}
		} else if (level == 1) {
			for(int i=0;i<2;i++) {
				int dx = x + direct[arr2[i]][0];
				int dy = y + direct[arr2[i]][1];
				if (dx <0 || dy <0 || dx >= N || dy >= N) continue;
				if (visit[dx][dy] == 1)continue;
				if (map[dx][dy] == 1)continue;
				checkno(dx, dy, level);
				dfs(dx, dy, arr2[i]);
				checkyes(dx, dy, level);
			}
		} else if (level == 2) {
			for(int i=0;i<3;i++) {
				int dx = x + direct[arr3[i]][0];
				int dy = y + direct[arr3[i]][1];
				if (dx <0 || dy <0 || dx >= N || dy >= N) continue;
				if (visit[dx][dy] == 1)continue;
				if (map[dx][dy] == 1)continue;
				checkno(dx, dy, level);
				dfs(dx, dy, arr3[i]);
				checkyes(dx, dy, level);
			}
		}
		
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visit = new int[N][N];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visit[0][0] = 1;
		visit[0][1] = 1;
		dfs (0,1,0);
		System.out.println(cnt);

	}

}
