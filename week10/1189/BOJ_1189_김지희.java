package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1189_김지희 {
//컴백홈
	
	static int R, C, K, answer;
	static char[][] map;
	static int[][] visit;
	static int[] dx= {1,-1,0,0};
	static int[] dy= {0,0,1,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()); //집까지 도착하는 경우 중 거리가 K
		//경우의 수 출력
		
		map = new char[R][C];
		visit = new int[R][C];
		
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<C; j++) {
//				if(map[i][j] == 'T')
//					visit[i][j] =1;
			}
		} //input end
		
//		for(int i=0; i<R; i++) {
//			for(int j=0; j<C; j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}
		
		answer = 0;
		map[R-1][0] = '-';
		dfs(R-1,0, 1); //왼쪽 아래 (R-1, 0)에 위치
		System.out.println(answer);
		
	}
	
	private static void dfs(int x, int y, int cnt) {
		if(x==0 && y==C-1) {
			if(cnt==K) {
				answer++;
				return;
			}
		}
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx<0 || ny<0 || nx>=R || ny>=C || map[nx][ny]!='.') continue;
			
			map[nx][ny] = '-';
			dfs(nx, ny, cnt+1);
			map[nx][ny] = '.';
			
		}
	}
}
