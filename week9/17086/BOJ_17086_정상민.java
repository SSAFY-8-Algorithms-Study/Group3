package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17086아기상어2 {
	static class node{
		int i,j;

		public node(int i, int j) {
			this.i = i;
			this.j = j;
		}		
	}
	static int N,M;
	static int MAX;
	static int[] di = {+1,-1,0,0,-1,-1,+1,+1};
	static int[] dj = {0,0,-1,+1,+1,-1,+1,-1};
	static int[][] map;
	static boolean[][] visit;
	static ArrayList<node> shark = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					shark.add(new node(i,j));
				}
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j] == 0) {
					visit = new boolean[N][M];
					bfs(i,j);
				}
			}
		}
		System.out.println(MAX);
	}
	private static void bfs(int x, int y) {
		Queue<node> que = new LinkedList<>();
		que.add(new node(x,y));
		visit[x][y] = true;
		int cnt = 1;
		while(!que.isEmpty()) {
			int size = que.size();
			for(int s=0;s<size;s++) {
				node n = que.poll();
				for(int d=0;d<8;d++) {
					int ni = n.i + di[d];
					int nj = n.j + dj[d];
					if(ni<0 || nj<0 || ni>=N || nj>=M || visit[ni][nj]) continue;
					if(map[ni][nj] == 1) {
						MAX = Math.max(MAX, cnt);
						return;
					}
					que.add(new node(ni,nj));
					visit[ni][nj] = true;
				}
			}
			cnt++;
		}
	}
}
