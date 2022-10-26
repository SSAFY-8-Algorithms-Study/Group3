package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 아 맞다 우산~~~~~~~~~~~~~~~~~~~~~~~~~~
// 하ㅏㅏㅏㅏㅏㅏㅏㅏ기싫어
public class BOJ_17244_김지희 {
	static class Point {
		int i, j;
		int cnt;

		Point(int i, int j, int cnt){
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}
	}

	static int[] di = { 1, -1, 0, 0 };
	static int[] dj = { 0, 0, 1, -1 };
	
	static int N, M, answer, listLen;
	static char[][] map;
	static Point start, end;
	static ArrayList<Point> list, copy;
	static Queue<Point> Q;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 가로 길이
		M = Integer.parseInt(st.nextToken()); // 세로 길이
		
		map = new char[M][N];
		list = new ArrayList<>();
		
		for(int i=0; i<M; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<N; j++) {
				if(map[i][j]=='S') {
					start = new Point(i, j, 0);
				}
				else if(map[i][j]=='E') {
					end = new Point(i, j, 0);
				}
				else if(map[i][j] == 'X') {
					list.add(new Point(i, j, 0));
				}
			}
		}
		
		listLen = list.size();
		if(listLen==0) {
			bfs2();
			System.out.println(answer);
			System.exit(0);
		}
		
		int[] select = new int[listLen];
		int[] visit = new int[listLen];
		answer = Integer.MAX_VALUE;
		perm(0, select, visit);
		System.out.println(answer);
	}
	
	private static void perm(int cnt, int[] select, int[] visit) {
		if(cnt==listLen) {
			
			// X 순서 정해서 BFS
			int dist = 0;
			for(int i=0; i<=listLen; i++) {
				dist += bfs(i, select);
				if(answer<dist) return;
			}
			answer = Math.min(answer, dist);
			return;
		}
		
		for(int i=0; i<listLen; i++) {
			if(visit[i]==1) continue;
			select[cnt] = i;
			visit[i] = 1;
			perm(cnt+1, select, visit);
			visit[i] = 0;
		}
		
	}
	
	private static int bfs(int idx, int[] select) { //포인트끼리 거리 구하기
		Queue<Point> Q = new LinkedList<>();
		int[][] check = new int[M][N];
		Point dest;
		if(idx==0) {
			Q.add(start);
			check[start.i][start.j] = 1;
			dest = list.get(select[idx]);
		}
		else {
			Point s = list.get(select[idx-1]);
			Q.add(s);
			check[s.i][s.j]= 1; 
			if(idx==listLen) {
				dest = end;
			}else {
				dest = list.get(select[idx]);
			}
		}
		
		while(!Q.isEmpty()) {
			Point cur = Q.poll();
			
			for(int d=0; d<4; d++) {
				int ni = cur.i + di[d];
				int nj = cur.j + dj[d];
				
				if(ni<0||nj<0||ni>=M||nj>=N) continue;
				if(map[ni][nj] == '#') continue;
				if(check[ni][nj]==1) continue;
				
				if(ni== dest.i && nj==dest.j) {
					//도착지점
					return cur.cnt+ 1; 
				}
				
				check[ni][nj] = 1;
				Q.add(new Point(ni, nj, cur.cnt+1));
			}
		}
		return 0;
	}
	
	private static void bfs2() {
		Queue<Point> Q = new LinkedList<>();
		int check[][] = new int[M][N];
		Q.add(start);
		check[start.i][start.j]= 1;
		answer = 0;
		while(!Q.isEmpty()) {
			Point cur = Q.poll();
			
			for(int d=0; d<4; d++) {
				int ni = cur.i + di[d];
				int nj = cur.j + dj[d];
				
				if(ni<0||nj<0||ni>=M||nj>=N) continue;
				if(map[ni][nj] == '#') continue;
				if(check[ni][nj]==1) continue;
				
				if(ni== end.i && nj==end.j) {
					//도착지점
					answer = cur.cnt+1;
					return;
				}
				
				check[ni][nj] = 1;
				Q.add(new Point(ni, nj, cur.cnt+1));
			}
		}
	}
}
