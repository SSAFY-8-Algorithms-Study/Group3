package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 바닥 장식
public class BOJ_1388_김지희 {
	static class Point{
		int i, j;
		Point(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
	
	static int N, M, answer, visit[][];
	static char[][] board;
	
	static int[] di = {0,1}; //오른쪽, 아래
	static int[] dj = {1,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][M];
		visit = new int[N][M];
		
		for(int i=0; i<N; i++) {
			board[i] = br.readLine().toCharArray();
		}
		answer = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(visit[i][j] == 0) {
					bfs(i, j);
					answer++;
				}
			}
		}
		
		System.out.println(answer);
	}
	
	private static void bfs(int nowi, int nowj) {
		Queue<Point> Q = new LinkedList<>();
		Q.add(new Point(nowi, nowj));
		char start = board[nowi][nowj];
		visit[nowi][nowj] = 1;
		int d = 0;
		if(start == '-') d = 0;
		else if(start=='|') d = 1;
		
		while(!Q.isEmpty()) {
			Point cur = Q.poll();
		
			int ni = cur.i + di[d];
			int nj = cur.j + dj[d];
			
			if(ni>=N || nj>=M) break;
			
			if(visit[ni][nj]==0 && start == board[ni][nj]) {
				Q.add(new Point(ni, nj));
				visit[ni][nj] = 1;
			}
		}
	}
}
