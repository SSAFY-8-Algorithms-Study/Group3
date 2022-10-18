package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 불 이동 -> 상근 이동
// board를 N+2로 만들어서 탈출구(0) 를 마련
//변수 : board, 델타 , fq,sq 
// 상근 , 불 모두 벽으로 이동하지 못 함
// 상근 큐에서 꺼낼 때 좌표가 board 밖이면 시간 출력 후 return;
//sq가 비고 종료되면 IMPOSSIBLE
public class BOJ_5427_김동욱 {
	static int W;
	static int H;
	static char[][] board;
	static Queue<Point> fq;
	static Queue<Point> sq;
	static boolean fvisited[][];
	static boolean svisited[][];
	static StringBuilder sb;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static class Point {
		int x;
		int y;

		public Point() {
		}

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		int testcase = Integer.parseInt(br.readLine());
		 for (int tc = 1; tc <= testcase; tc++) {
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		board = new char[H][W];
		fq = new LinkedList<Point>();
		sq = new LinkedList<Point>();
		fvisited = new boolean[H][W];
		svisited = new boolean[H][W];

		for (int i = 0; i < H; i++) {
			String t = br.readLine();
			for (int j = 0; j < W; j++) {
				char c = t.charAt(j);
				if (c == '*') {
					fq.add(new Point(i, j)); // 불 위치 저장
					fvisited[i][j] = true;
				} else if (c == '@') {
					sq.add(new Point(i, j)); // 상근 위치저장
					svisited[i][j] = true;
				}
				
				board[i][j] = c;
			}
		} // end input
		//print();

		bfs();

		 }
		 System.out.println(sb);

	}

	public static void print() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(board[i][j]=='#') {
					System.out.print(8 + " ");
					continue;
				}
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("_______________");
	}

	public static boolean isFireValid(int x, int y) {
		if (x < 0 || x >= H || y < 0 || y >= W)
			return false;
		if (fvisited[x][y])
			return false;
		return true;
	}

	public static boolean isSValid(int x, int y) {
		if (x < 0 || x >= H || y < 0 || y >= W)
			return false;
		if (fvisited[x][y])
			return false;
		return true;
	}

	public static void bfs() {
		int time = 0;
		while (!sq.isEmpty()) {
			int fsize = fq.size();
			for (int fs = 0; fs < fsize; fs++) {
				Point tempPoint = fq.poll();
				for (int d = 0; d < 4; d++) {
					int nx = tempPoint.x + dx[d];
					int ny = tempPoint.y + dy[d];
					if (isFireValid(nx, ny) && board[nx][ny] != '#') {
						board[nx][ny] = '*';
						fvisited[nx][ny] = true;
						fq.add(new Point(nx, ny));
					}
				}
			}
			int size = sq.size();
			for (int s = 0; s < size; s++) {
				Point tempfPoint = sq.poll();
				//System.out.printf("[%d %d]\n",tempfPoint.x,tempfPoint.y);
				if (tempfPoint.x == 0 || tempfPoint.x == H - 1 || tempfPoint.y == 0 || tempfPoint.y == W - 1) {
					
					sb.append(++time+"\n");
					return;
				}
				for (int d = 0; d < 4; d++) {
					int nx = tempfPoint.x + dx[d];
					int ny = tempfPoint.y + dy[d];
					if (isSValid(nx, ny) && board[nx][ny] == '.' && board[nx][ny] != '*') {
						board[nx][ny] = '@';
						svisited[nx][ny] = true;
						sq.add(new Point(nx, ny));
					}
				}
			}
			//print();
			time++;

		}
		
		//System.out.println("IMPOSSIBLE");
		sb.append("IMPOSSIBLE\n");
	}

}