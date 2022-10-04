package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.concurrent.BrokenBarrierException;

public class BOJ_1189_kdw {
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	static int R;
	static int C;
	static int K;
	static int answer;
	static char[][] board;
	static boolean[][] visited;
	public static boolean isValid(int r, int c) {
		if(r<0 || r>=R || c<0 || c>=C)
			return false;
		if(visited[r][c] || board[r][c]=='T')
			return false;
		return true;
	}
	public static void dfs(int r, int c, int steps) {
		if(r==0 && c==C-1) {
			if(steps==K) {
				answer++;
			}
			return;
		}
		for(int d=0;d<4;d++) {
			int nr = r+dx[d];
			int nc = c+dy[d];
			if(isValid(nr, nc)) {
				visited[nr][nc]= true;
				dfs(nr,nc,steps+1);
				visited[nr][nc]= false;
			}
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		visited = new boolean[R][C];
		answer= 0;
		
		for(int i=0;i<R;i++) {
			String temp = br.readLine();
			for(int j=0;j<C;j++) {
				board[i][j]= temp.charAt(j);
			}
		} // end input
		visited[R-1][0] = true;
		dfs(R-1,0,1);
		System.out.println(answer);
	}

}
