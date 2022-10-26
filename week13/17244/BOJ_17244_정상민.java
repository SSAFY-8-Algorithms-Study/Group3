package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17244아맞다우산 {
	static int N,M;
	static int[] di = {1,-1,0,0};
	static int[] dj = {0,0,1,-1};
	static char[][] map;
	static boolean[][][] visit;
	static boolean[] check;
	static point S,E;
	static ArrayList<point> Xlist =  new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visit = new boolean[N][M][1<<5];
		//비트마스킹 방문처리하기 위해 3차원으로 선언
		int cnt = 0;
		for(int i=0;i<N;i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0;j<M;j++) {
				if(map[i][j] == 'S') {
					S = new point(i, j,0);//시작점 위치 찾아놓고
					map[i][j] = '.';//빈칸처리
				}
				else if(map[i][j] == 'E') E = new point(i, j,0);
				else if(map[i][j] == 'X') {//X마다 번호 설정해주기
					Xlist.add(new point(i, j,++cnt));
				}
			}
		}
		check = new boolean[cnt+1];
		System.out.println(bfs());
	}
	private static int bfs() {
		Queue<point> que = new LinkedList<>();
		//X를 어떻게 방문 하냐 방문처리 어뜨케 처리하냐
		//하다가 이전에 달이차오른다가자 교수님풀이에 비트마스킹 방문처리하는거 보고 따라함
		que.add(S);
		visit[S.i][S.j][0] = true;
		int dist = 0;
		while(!que.isEmpty()) {
			int size = que.size();
			for(int s=0;s<size;s++) {
				point now = que.poll();
				if(map[now.i][now.j]=='E') {
					if(now.num == (int)Math.pow(2, Xlist.size())-1) {
						//이게 모든 X 지나고 난후 E방문한 경우
						return dist;
					}
				}
				for(int d=0;d<4;d++) {
					int nexti = now.i + di[d];
					int nextj = now.j + dj[d];
					if(nexti<0 || nextj<0 || nexti>=N || nextj>=M) continue;
					if(map[nexti][nextj]=='#' || visit[nexti][nextj][now.num]) continue;
					if(map[nexti][nextj]=='X') { //물건 만났을때 
						for(int i=0;i<Xlist.size();i++) {
							if(Xlist.get(i).i==nexti && Xlist.get(i).j==nextj) {
								//해당 물건 번호를 찾고 키 설정하고 
								int newkey = 1<<(Xlist.get(i).num-1);
								int addedKey = now.num | newkey;
								que.add(new point(nexti, nextj, addedKey));
								visit[nexti][nextj][addedKey] = true;
								check[Xlist.get(i).num] = true;//해당 물건 챙겼당
								break;
							}
						}
					}
					else { //평지 또는 E 만났을 때
						que.add(new point(nexti, nextj, now.num));
						visit[nexti][nextj][now.num] = true;
					}	
				}
			}
			dist++;
		}
		return -1;
	}
	static class point{
		int i,j,num;
		public point(int i, int j,int num) {
			this.i = i;
			this.j = j;
			this.num = num; // 1 ~ 5
		}
	}
}