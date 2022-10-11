package day1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3187_양치기꿍 {
	static class Point{
		int i,j;
		Point(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
	
	static int R, C;
	static char[][] map;
	static int[][] visit;
	static int[] di = {1,-1,0,0};
	static int[] dj = {0,0,1,-1};
	
	static int sheep, wolf;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); //세로
		C = Integer.parseInt(st.nextToken()); //가로
		
		map = new char[R][C];
		visit = new int[R][C];
		
		sheep = 0;
		wolf = 0;
		
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				if(map[i][j] == 'v') {
					wolf++;
				}else if(map[i][j] == 'k') sheep++;
			}
		}
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] == '#') {
					bfs(i, j);
				}
			}
		}
		
		System.out.println(sheep + " " + wolf);
	}
	
	private static void bfs(int nowi, int nowj) {
		Queue<Point> Q = new LinkedList<>();
		int cntSheep=0, cntWolf=0;
		
		Q.add(new Point(nowi, nowj));
		
		while(!Q.isEmpty()) {
			Point cur = Q.poll();
			
			for(int d=0; d<4; d++) {
				int nexti = cur.i + di[d];
				int nextj = cur.j + dj[d];
				
				if(nexti<0 || nextj<0 || nexti>=R || nextj>=C) continue;
				if(map[nexti][nextj] == '#') continue;
				if(visit[nexti][nextj] == 1) continue;
				

				if(map[nexti][nextj] == 'k') {
					cntSheep++;
				}else if(map[nexti][nextj] == 'v') {
					cntWolf++;
				}
				visit[nexti][nextj] = 1;
				Q.add(new Point(nexti, nextj));
			}
		}
		if(cntSheep== 0 || cntWolf==0) return;
		if(cntSheep>cntWolf) {
			wolf -= cntWolf;
		}else sheep -= cntSheep;
	}
}
