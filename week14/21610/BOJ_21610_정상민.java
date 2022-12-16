package baek;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_21610마법사상어비바라기 {
	static int N,M,D,S;
	static int[] di = {0,-1,-1,-1,0,1,1,1};
	static int[] dj = {-1,-1,0,1,1,1,0,-1};
	static int[] ci = {1,1,-1,-1};
	static int[] cj = {-1,1,-1,1};
	static int[][] map;
	static boolean[][] visit;
	static ArrayList<point> cloud = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
			////////////////////////////////맵 입력
		}
		cloud.add(new point(N, 1));
		cloud.add(new point(N, 2));
		cloud.add(new point(N-1, 1));
		cloud.add(new point(N-1, 2)); 
		//////////////////////////구름 초기 위치 
		for(int m=0;m<M;m++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken()) - 1;
			S = Integer.parseInt(st.nextToken()); 
			////////////////////이동 정보 입력받고
			step1();
			step2();
			step3_4();
			step5();
			/////////////////// 단계별로 실시
		}
		int result = 0;
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				result += map[i][j];
			}
		}/////////////////////////결과값 구하기
		System.out.println(result);
	}
	static private void step1() { 
		//////////////구름이동
		visit = new boolean[N+1][N+1];
		for(int i=0;i<cloud.size();i++) {
			int nowi = cloud.get(i).i;
			int nowj = cloud.get(i).j;
			for(int s=0;s<S;s++) {
				int nexti = nowi + di[D];
				int nextj = nowj + dj[D];
				if(nexti < 1) nexti = N;
				else if(nexti > N) nexti = 1;
				if(nextj < 1) nextj = N;
				else if(nextj > N) nextj = 1;
				nowi = nexti;
				nowj = nextj;
			}
			cloud.get(i).i = nowi;
			cloud.get(i).j = nowj;
			visit[nowi][nowj] = true; //구름이동칸 방문체크 step5 에서 제외시키려고
		}
	}
	static private void step2() {
		/////////////구름칸 바구니 물1증가
		for(int i=0;i<cloud.size();i++) {
			int nowi = cloud.get(i).i;
			int nowj = cloud.get(i).j;
			map[nowi][nowj]++;
		}
	}
	static private void step3_4() { 
		////////////////////// step4 물복사버그 
		for(int i=0;i<cloud.size();i++) {
			int nowi = cloud.get(i).i;
			int nowj = cloud.get(i).j;
			int cnt = 0;
			for(int d=0;d<4;d++) {
				int nexti = nowi + ci[d];
				int nextj = nowj + cj[d];
				if(nexti<1 || nexti>N || nextj<1 || nextj>N) continue;
				if(map[nexti][nextj]!=0) cnt++;
			}
			map[nowi][nowj] += cnt;
		}
		/////////////////// step3 구름 사라짐
		cloud = new ArrayList<>();
	}
	static private void step5() {
		/////////////////// 물의양 2이상 칸에 구름 생성 
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				if(!visit[i][j] && map[i][j] >=2) { // 이전에 사라진 칸 제외하여 체크
					cloud.add(new point(i, j));
					map[i][j] -= 2;
				}
			}
		}
	}
	static class point{
		int i,j;
		public point(int i, int j) {
			this.i = i;
			this.j = j;
		}
		@Override
		public String toString() {
			return "point [i=" + i + ", j=" + j + "]";
		}
		
	}
}
