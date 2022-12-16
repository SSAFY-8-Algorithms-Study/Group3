package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_21610_김지희 {
	static class Point {
		int i, j;

		Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	static int N, M, map[][], visit[][];
	static Point[] cmd;
	static Queue<Point> Q;
	static int answer;

	static int[] di = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dj = { -1, -1, 0, 1, 1, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];
		visit = new int[N + 1][N + 1];
		cmd = new Point[M];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Q = new LinkedList<>();
		Q.add(new Point(N, 1));
		Q.add(new Point(N, 2));
		Q.add(new Point(N - 1, 1));
		Q.add(new Point(N - 1, 2));
		

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());

			answer = 0;
			move(dir-1, dist);
			
		}
		
		System.out.println(answer);
	}
	
	private static void move(int dir, int dist) {
		// 1. 이동
		Queue<Point> list = new LinkedList<>();
		
		
		while(!Q.isEmpty()) {
			Point cur = Q.poll();
			
			int ni = cur.i + (di[dir] * dist);
			int nj = cur.j + (dj[dir] * dist);
			
			while(ni<=0 || ni>N) {
				if(ni<=0) ni+=N;
				else if(ni>N) ni-=N;
			}
			
			while(nj<=0 || nj>N) {
				if(nj<=0) nj+=N;
				else if(nj>N) nj-=N;
			}

			list.add(new Point(ni, nj));
			map[ni][nj]++;
			visit[ni][nj] = 1;
		}
		
		while(!list.isEmpty()) {
			Point cur = list.poll();
			
			int cnt=0; 
			
			for(int d=1; d<=7; d+=2) {
				int ni = cur.i + di[d];
				int nj = cur.j + dj[d];
				
				if(ni<=0 || nj<=0 || ni>N || nj>N) continue;
				if(map[ni][nj] <1) continue;
				
				cnt++;
			}
			map[cur.i][cur.j]+= cnt; 
		}
		cloud();
	}
	
	private static void cloud() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if((map[i][j]>=2) && visit[i][j] == 0) {
					Q.add(new Point(i, j));
					map[i][j] -= 2;
				}
				else visit[i][j] = 0;
				answer += map[i][j];
			}
		}
	}
	
}
