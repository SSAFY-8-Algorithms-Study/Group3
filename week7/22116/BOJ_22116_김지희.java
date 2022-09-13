package week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//창영이와 퇴근
public class BOJ_22116_김지희 {
	static class Point implements Comparable<Point>{
		int x, y, slope;
		Point(int x, int y, int slope){
			this.x = x;
			this.y = y;
			this.slope = slope;
		}
		@Override
		public int compareTo(Point o) {
			
			return this.slope - o.slope;
		}
	}
	
	static int N;
	static int[][] map, visit;
	static int answer;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = Integer.MIN_VALUE;
		PriorityQueue<Point> pQ = new PriorityQueue<Point>();
		pQ.offer(new Point(0, 0, 0));
		while(!pQ.isEmpty()) {
			Point cur = pQ.poll();
			visit[cur.x][cur.y] = 1;
			answer = Math.max(answer, cur.slope);
			if(cur.x==N-1 && cur.y==N-1) break;
			for(int i=0; i<4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if(nx<0 || ny<0 || nx>=N || ny>=N || visit[nx][ny]==1) continue;
				int s = Math.abs(map[cur.x][cur.y]-map[nx][ny]);
				pQ.add(new Point(nx, ny, s));
			}
		}
		System.out.println(answer);
		
	}
}
