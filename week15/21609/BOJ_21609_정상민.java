package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_21609상어중학교 {
	static int N,M,ans = 0,deletenum;
	static int map[][];
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	static boolean visit[][];
	static ArrayList<point> largegroup;
	static int largeRainbowCnt, largeBigi, largeBigj;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		deletenum = M+1;
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//////////////////////////////////input 
		while(true) {
			largegroup = new ArrayList<>();
			largeBigi=0;
			largeBigj=0;
			largeRainbowCnt=0;
			// 가장 큰 그룹 찾을 때 쓸 변수들
			findlargestgroup();
			if(largegroup.size() == 0) { // 큰그룹 사이즈가 0이면 더이상 
				break;
			}
			else {
				ans += largegroup.size() * largegroup.size();
			}
			deletelaregestgroup();
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=N;j++) {
					System.out.print(map[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println("----------------------------------");
			gravity();
			rotate();
			gravity();
		}
		System.out.println(ans);
	}
	private static void findlargestgroup() {
		for(int m=1;m<=M;m++) {
			visit = new boolean[N+1][N+1];
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=N;j++) {
					if(map[i][j] != m || visit[i][j]) continue;
					bfs(i,j,m);
				}
			}
		}
	}
	private static void bfs(int i,int j, int m) {
		ArrayList<point> checkgroup = new ArrayList<>(); 
		int checkRainbowCnt = 0;
		int checkBigi = i;
		int checkBigj = j;
		visit[i][j] = true;
		Queue<point> que = new LinkedList<>();
		que.add(new point(i, j));
		checkgroup.add(new point(i, j));
		
		while(!que.isEmpty()) {
			point now = que.poll();
			int nowi = now.i;
			int nowj = now.j;
			for(int d=0;d<4;d++) {
				int nexti = nowi + di[d];
				int nextj = nowj + dj[d];
				if(nexti > N || nexti <= 0 || nextj>N || nextj<=0) continue;
				if(visit[nexti][nextj]) continue;
				if(map[nexti][nextj]==m || map[nexti][nextj]==0) {
					visit[nexti][nextj] = true;
					que.add(new point(nexti, nextj));
					checkgroup.add(new point(nexti, nextj));
					if(map[nexti][nextj] == 0) checkRainbowCnt++;
					if(nexti > checkBigi) {
						checkBigi = nexti;
						checkBigj = nextj;
					}
					else if(nexti == checkBigi) {
						if(nextj > checkBigj) {
							checkBigj = nextj;
						}
					}
				}
			}
		}
		if(checkgroup.size() >= 2) {
			if(checkgroup.size() > largegroup.size()) {
				largegroup = checkgroup;
				largeBigi = checkBigi;
				largeBigj = checkBigj;
				largeRainbowCnt = checkRainbowCnt;
			}
			else if(checkgroup.size() == largegroup.size()) {
				if(largeRainbowCnt > checkRainbowCnt) {
					largegroup = checkgroup;
					largeBigi = checkBigi;
					largeBigj = checkBigj;
					largeRainbowCnt = checkRainbowCnt;
				}
				else if(largeRainbowCnt == checkRainbowCnt) {
					if(checkBigi > largeBigi) {
						largegroup = checkgroup;
						largeBigi = checkBigi;
						largeBigj = checkBigj;
						largeRainbowCnt = checkRainbowCnt;
					}
					else if(checkBigi == largeBigi) {
						if(checkBigj > largeBigj) {
							largegroup = checkgroup;
							largeBigi = checkBigi;
							largeBigj = checkBigj;
							largeRainbowCnt = checkRainbowCnt;
						}
					}
				}
			}
		}
		
	}
	private static void deletelaregestgroup() {
		for(int i=0;i<largegroup.size();i++) {
			int di = largegroup.get(i).i;
			int dj = largegroup.get(i).j;
			map[di][dj] = deletenum;
		}
	}
	private static void gravity() {
		
	}
	private static void rotate() {
		
	}
	static class point{
		int i,j;
		public point(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		@Override
		public String toString() {
			return "point [i=" + i + ", j=" + j + "]";
		}
	}
}
