package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1743_김지희 {
	static class Point {
		int i, j;

		Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	static int N, M, K;
	static int[][] map, visit;
	static int answer;
	static int[] di = { 1, -1, 0, 0 };
	static int[] dj = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];
		visit = new int[N + 1][M + 1];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 1;
		}

		answer = Integer.MIN_VALUE;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (map[i][j] == 1 && visit[i][j] == 0) {
					bfs(i, j);
//					print();
				}
			}
		}
		
		System.out.println(answer);
	}

	private static void bfs(int nowi, int nowj) {
		Queue<Point> Q = new LinkedList<>();
		Q.add(new Point(nowi, nowj));
		visit[nowi][nowj] = 1;
		int count = 1;
		
		while(!Q.isEmpty()) {
			Point cur = Q.poll();
			
			for(int d=0; d<4; d++) {
				int ni = cur.i + di[d];
				int nj = cur.j + dj[d];
				
				if(ni<=0 || nj<=0 || ni>N || nj>M) continue;
				
				if(visit[ni][nj] == 1) continue;
				if(map[ni][nj] == 0) continue; 
				
				visit[ni][nj] =1;
				count++;
				
				Q.add(new Point(ni, nj));
			}
		}
		if(count>answer) answer = count;
	}
	
	private static void print() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				System.out.print(visit[i][j]+ " ");
			}System.out.println();
		}
	}
}
