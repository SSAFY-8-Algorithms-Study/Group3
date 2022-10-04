package week09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_18428_김지희 {
	static class Point{
		int x, y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	static int[] dx = {1,-1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	
	static int N;
	static char[][] map;
	static ArrayList<Point> teachers;
//	static Queue<Point> Q;
	static int[][] visit;
	static boolean studentFind;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visit = new int[N][N];
		
//		Q = new LinkedList<>();
		teachers = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = st.nextToken().charAt(0);
				if(map[i][j]=='T') {
					teachers.add(new Point(i, j));
					visit[i][j] = 1;
				}
			}
		}
		studentFind = false;
		comb(0);
		System.out.println("NO");
		
	}
	
	private static void comb(int cnt) {
		if(cnt==3) {
			//여기서 탐색해보기
			bfs();
			if(studentFind) {//가능한 조합이 하나면 충분해
				System.out.println("YES");
				System.exit(0);//바로 종료시켜버려
			}
			return;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]=='X') {
					map[i][j]='O';
					comb(cnt+1);
					map[i][j]='X';
				}
			}
		}
	}
	
	private static void bfs() {
		
		for(Point p : teachers) {
			
			for(int i=0; i<4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
//				if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
				while(!((nx<0 || ny<0 || nx>=N || ny>=N))) {
					if(map[nx][ny]=='S') {// 학생을 만났으면 이번 조합으론 실패
						studentFind = false;
						return;
					}else if(map[nx][ny]=='O') {
						break; //장애물이면 일단 while문 멈추고 다음 탐색
					}
					nx+=dx[i];
					ny+=dy[i];
				}
				
			}
		}
		studentFind = true;

	}
}
