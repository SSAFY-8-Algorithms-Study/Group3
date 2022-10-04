package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1189_김아영 {

	static int R, C, K;
	static char map[][];
	static int bat[];
	static boolean visit[][];
	static int direct[][] = {{1,0},{-1,0},{0,1},{0,-1}};
	
	static void dfs(int level, int x, int y) {
		
		if (x == 0 && y == C - 1) {
			bat[level]++;
			return;
		}
		
		for(int i=0;i<4;i++) {
			int dx = x + direct[i][0];
			int dy = y + direct[i][1];
			if (dx <0 || dy <0 || dx >= R || dy >= C)continue;
			if (visit[dx][dy]) continue;
			if (map[dx][dy] == 'T') continue;
			visit[dx][dy] = true;
			dfs(level + 1, dx, dy);
			visit[dx][dy] = false;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visit = new boolean[R + 1][C + 1];
		bat = new int[R*C + 1];
		for(int i=0;i<R;i++) {
			String str = br.readLine();
			for(int j=0;j<C;j++) {
				map[i][j] = str.charAt(j);
			}
		}
		visit[R-1][0] = true;
		dfs(1,R-1,0);
		System.out.println(bat[K]);
		
		
	}

}
