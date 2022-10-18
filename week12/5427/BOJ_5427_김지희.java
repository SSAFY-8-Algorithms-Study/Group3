package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 불~~~~~~~~~
public class BOJ_5427_김지희 {

	static class Point{
		int i, j;

		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	static int[] di = {1,-1,0,0};
	static int[] dj = {0,0,1,-1};
	static int R, C, answer;
	static char[][] map;
	static int[][] visit;
	static Point Start, Fire;
	static Queue<Point> Q, SangQ;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TC; tc++) {
			st = new StringTokenizer(br.readLine());
			
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			
			map = new char[R][C];
			visit = new int[R][C];
			Q = new LinkedList<>();

			boolean flag = false;
			answer = Integer.MAX_VALUE;
			
			for(int i=0; i<R; i++) {
				map[i] = br.readLine().toCharArray();
				for(int j=0; j<C; j++) {
					if(map[i][j] == '@') {
						Start = new Point(i, j);
						visit[i][j] = 1;
						if(i==0 || j==0 || i==R-1 || j==C-1) {//가장 자리이면
							flag = true;
							answer = 1;
						}

					}
					if(map[i][j] == '*') {
						Fire = new Point(i, j);
						Q.add(new Point(i, j));
					}
				}
			}
			// input end;
			if(!flag) {
				bfs();
			}
			System.out.println(answer==Integer.MAX_VALUE?"IMPOSSIBLE" : answer);
			
		}
		
	}
	
	private static void bfs() {
		SangQ = new LinkedList<>();
		SangQ.add(Start);
		
		while(!SangQ.isEmpty()){
			
			// 불부터 이동할게
			int size = Q.size();
			for(int s=0; s<size; s++) {
				Point cur = Q.poll();
				
				for(int d=0; d<4; d++) {
					int ni = cur.i + di[d];
					int nj = cur.j + dj[d];
					
					if(ni<0 || nj<0 || ni>=R || nj>=C) continue;
					
					if(map[ni][nj] == '.') {
						Q.add(new Point(ni, nj));
						map[ni][nj] = '*';
					}
				}
			}
			
			//이제 상근이 이동할게
			size = SangQ.size();
			for(int s=0; s<size; s++) {
				Point cur = SangQ.poll();
				
				for(int d=0; d<4; d++) {
					int ni = cur.i + di[d];
					int nj = cur.j + dj[d];
					
					if(ni<0 || nj<0 || ni>=R || nj>=C) continue;
					
					if(map[ni][nj] == '.' && visit[ni][nj]==0) {
						SangQ.add(new Point(ni, nj));
						visit[ni][nj] = visit[cur.i][cur.j]+1; 
					}
					if(ni==0 || nj==0 || ni==R-1 || nj==C-1) {//가장 자리이면
						if(map[ni][nj] == '.') {
							visit[ni][nj] = visit[cur.i][cur.j]+1;
							answer = Math.min(answer, visit[ni][nj]);
						}
					}
				}
				
			}
		
		}
	}
}
