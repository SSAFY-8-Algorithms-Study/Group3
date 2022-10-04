package week09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17086_김지희 {
// 17086 아기상어 2
	static class Point{
		int x, y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static int N, M;
	static int[][] map;
	
	static int[] dx = {-1, -1, -1, 0, +1, +1, +1, 0};
	static int[] dy = {-1, 0, +1, +1, +1, 0, -1, -1};
	
	static Queue<Point> Q;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M]; 
		Q = new LinkedList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) {
					Q.add(new Point(i, j));
				}
			}
		}
		
		while(!Q.isEmpty()) {
			Point cur = Q.poll();
			for(int i=0; i<8; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if(nx<0 || ny<0 || nx>=N || ny>=M) continue;
				
				if(map[nx][ny]==0) {
					map[nx][ny] = map[cur.x][cur.y]+1; 
					Q.add(new Point(nx, ny));
				}else if(map[nx][ny]>map[cur.x][cur.y]+1) {
					map[nx][ny] = map[cur.x][cur.y]+1; //더 작은 값으로 갱신
					Q.add(new Point(nx, ny));
				}
				
			}
		}
		
		int result = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(result<map[i][j]) result = map[i][j];
			}
		}
		System.out.println(result-1);
	}
}
