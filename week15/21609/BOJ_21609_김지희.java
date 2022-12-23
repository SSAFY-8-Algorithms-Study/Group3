package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 상어 중학교
public class BOJ_21609_김지희 {
	static class Block implements Comparable<Block> { // 블록 그룹을 나타내는 클래스
		int i, j; // 기준 블록 좌표
		int size; // 그룹 사이즈
		int rbCnt; // 무지개 블록 개수

		public Block(int i, int j, int size, int rbCnt) {
			super();
			this.i = i;
			this.j = j;
			this.size = size;
			this.rbCnt = rbCnt;
		}

		@Override
		public int compareTo(Block o) { // 우선순위
			if (this.size == o.size) {
				if (this.rbCnt == o.rbCnt) {
					if (this.i == o.i)
						return o.j - this.j;
					return o.i - this.i;
				}
				return o.rbCnt - this.rbCnt;
			}
			return o.size - this.size; // 내림차순
		}
	}

	static class Point implements Comparable<Point> {
		int i, j;

		Point(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public int compareTo(Point o) {
			if (this.i == o.i) {
				return this.j - o.j;
			}
			return this.i - o.i;
		}
	}

	static int N, M;
	static int[][] map;
	static int[][] visit;
	static ArrayList<Block> list;

	static int[] di = { 1, -1, 0, 0 };
	static int[] dj = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		visit = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // input end

		int answer = 0;

		while (true) {
			list = new ArrayList<>();
			visit = new int[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visit[i][j] == 0 && map[i][j] > 0) {
						bfs(i, j, map[i][j]);
					}
				}
			}
			Collections.sort(list);

			if(list.size() == 0) break;
			
			Block block = list.get(0);
			
			delete(block.i, block.j);
			int t = block.size * block.size;
			
			answer += t;
			
			gravity();
			rotate();
			gravity();
			
		}
		
		System.out.println(answer);

	}

	private static void bfs(int nowi, int nowj, int c) { // 블록그룹 구하기
		// (nowi, nowj) 는 기준블록이 될 것.
		int[][] check = new int[N][N]; // bfs 돌면서 확인할 visit 배열

		visit[nowi][nowj] = 1;
		check[nowi][nowj] = 1;

		int size = 1;
		int rbCnt = 0;

		Queue<Point> Q = new LinkedList<>();
		Q.add(new Point(nowi, nowj));

		while (!Q.isEmpty()) {
			Point cur = Q.poll();
			for (int d = 0; d < 4; d++) {
				int ni = cur.i + di[d];
				int nj = cur.j + dj[d];

				if (ni < 0 || ni >= N || nj < 0 || nj >= N)
					continue;
				if (map[ni][nj] == -1)
					continue;
				if (check[ni][nj] == 1)
					continue;

				if (map[ni][nj] == 0) {
					rbCnt++;
					size++;
					check[ni][nj] = 1;
					Q.add(new Point(ni, nj));
				} else if (map[ni][nj] == c) {
					size++;
					visit[ni][nj] = 1;
					check[ni][nj] = 1;
					Q.add(new Point(ni, nj));
				}

			}
		}

		if (size > 1)
			list.add(new Block(nowi, nowj, size, rbCnt)); // 기준 블록

	}

	private static void delete(int nowi, int nowj) { // 타겟 그룹 지우기
		Queue<Point> Q = new LinkedList<>();
		Q.add(new Point(nowi, nowj));
		
		int c = map[nowi][nowj];
		map[nowi][nowj] = -2; // 삭제하는 부분 -2로 표시
		
		while (!Q.isEmpty()) {
			
			Point cur = Q.poll();
			
			for (int d = 0; d < 4; d++) {
				int ni = cur.i + di[d];
				int nj = cur.j + dj[d];

				if (ni < 0 || nj < 0 || ni >= N || nj >= N)
					continue;

				if (map[ni][nj] == 0 || map[ni][nj] == c) {
					map[ni][nj] = -2;
					Q.add(new Point(ni, nj));
				}
			}
		}
	}

	private static void gravity() { // 중력 작용
		//열마다 일단
		for(int j=0; j<N; j++) {
			//행은 아래서부터 탐색
			for(int i=N-1; i>0; i--) {
				if(map[i][j]!=-2) continue;
				
				//빈칸을 만나면 내리기
				int ni = i;
				while(true) {
					ni--;
					if(ni<0) break;
					if(map[ni][j] == -1) break; // 검은색 블록을 만나면 멈춤
					if(map[ni][j] != -2) { // 빈칸이 아니면
						map[i][j] = map[ni][j]; //한칸 내리고
						map[ni][j] = -2; //빈칸으로 만들기
						break;
					}
					
				}
			}
		}
	}

	private static void rotate() { // 90도 회전
		int[][] copy = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				copy[i][j] = map[j][N-i-1];
			}
		}
		
		for(int i=0; i<N; i++) {
			map[i] = copy[i].clone();
		}
	}
}
