package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ_17143_김지희 {
	static class Shark {
		int r, c, speed, direction, size;
		boolean catched;

		public Shark(int r, int c, int speed, int direction, int size) {

			this.r = r;
			this.c = c;
			this.speed = speed;
			this.direction = direction;
			this.size = size;

		}
		
		public boolean find(Object obj) {
			Shark o = (Shark) obj;
			if(this.size == o.size) {
				return true;
			}
			return false;
		}
	}

	static class Point {
		int i, j;

		Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	static int R, C, M; // R x C , M마리의 상어
	static Shark[][] map;

	static int[] di = { -1, 1, 0, 0 }; // 상하우좌
	static int[] dj = { 0, 0, 1, -1 };

	static ArrayList<Shark> catchedList;
	static ArrayList<Shark> sharks;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
//		if (M == 0) {
//			System.out.println(0);
//			System.exit(0);
//		}

		map = new Shark[R + 1][C + 1];
		sharks = new ArrayList<>();
		catchedList = new ArrayList<>();
		answer = 0;
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());

//			sharks.add(new Shark(r, c, speed, direction - 1, size));
			map[r][c] = new Shark(r, c, speed, direction - 1, size); //size는 고유값
		} //input end
		
		//1. 낚시왕 이동
		for(int j=1; j<=C; j++) {
			// 2. 가장 가까운 상어 잡기
			for(int i=1; i<=R; i++) {
				if(map[i][j] != null) {
					answer += map[i][j].size;
					map[i][j] = null;
					break;
				}
			} 
			
			// 3. 상어 이동
			move();
		}
		
		System.out.println(answer);
	}
	
	private static void move() {
		Queue<Shark> Q = new LinkedList<>();
		
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				if(map[i][j] != null)
					Q.add(map[i][j]);
			}
		}
		
		map = new Shark[R+1][C+1];
		
		while(!Q.isEmpty()) {
			Shark cur = Q.poll();
			
			int nowi = cur.r;
			int nowj = cur.c;
			int dir = cur.direction;
			int spd = cur.speed;
			
			if (dir == 0 || dir == 1) {
				if(cur.speed>(R-1)*2) {
					spd = cur.speed %(R-1) * 2 ;
				}
				
			} else if (dir == 2 || dir == 3) {
				
				if(cur.speed>(C-1)*2) {
					spd =  cur.speed%(C-1) * 2;
				}
			}
			int nexti = nowi;
			int nextj = nowj;
			for(int s=0; s<spd; s++) {
				nexti += di[dir];
				nextj += dj[dir];
				
				if (nexti < 1 || nextj < 1 || nexti > R || nextj > C) {
					nexti -= di[dir];
					nextj -= dj[dir];
					// 방향 바꿔주기 dir이 홀수면 -1 짝수면 +1
					dir = dir % 2 == 0 ? dir + 1 : dir - 1;
					cur.direction = dir;

					s--;
				}
			}
			
			if(map[nexti][nextj]!=null) {
				//상어가 이미 있으면 크기 비교하기
				if(map[nexti][nextj].size < cur.size) {
					//기존 상어가 더 작으면 갱신
					map[nexti][nextj] = new Shark(nexti, nextj, cur.speed, cur.direction, cur.size);
				}
			}else {
				//없으면 바로 넣기
				map[nexti][nextj] = new Shark(nexti, nextj, cur.speed, cur.direction, cur.size);
			}
		}
	}
}
