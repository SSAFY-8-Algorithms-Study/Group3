package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3187_김동욱_bfs {
	static int R;
	static int C;
	static int wolfAnswer;
	static int sheepAnswer;

	static char[][] board;
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static class Point {
		int x;
		int y;

		public Point() {
		}

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		wolfAnswer = 0;
		sheepAnswer = 0;
		board = new char[R][C];
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String temp = br.readLine();
			for (int j = 0; j < C; j++) {
				board[i][j] = temp.charAt(j);
			}
		} // end input
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (board[i][j] != '#' && !visited[i][j]) {
					bfs(new Point(i, j));
				}
			}
		}
		System.out.println(sheepAnswer+" "+wolfAnswer);
	}
	public static boolean isValid(int x, int y) {
		if(x<0 || x>=R || y<0 || y>=C)
			return false;
		if(visited[x][y])
			return false;
		return true;
	}
	private static void bfs(Point p) {
		Queue<Point> queue = new LinkedList<>();
		int sheepCount=0;
		int wolfCount=0;
		visited[p.x][p.y] = true;
		if (board[p.x][p.y] == 'k') {
			sheepCount++;
		} else if (board[p.x][p.y] == 'v') {
			wolfCount++;
		}
		queue.add(p);
		
		while (!queue.isEmpty()) {
			Point tempPoint = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nx = tempPoint.x + dx[d];
				int ny = tempPoint.y + dy[d];
				if(isValid(nx,ny) && board[nx][ny]!='#') {
					Point ap = new Point(nx,ny);
					if(board[nx][ny]=='k') {
						sheepCount++;
					}
					if(board[nx][ny]=='v') {
						wolfCount++;
					}
					queue.add(ap);
					visited[nx][ny]=true;
				}
			}
		}
		if(sheepCount>wolfCount) {
			sheepAnswer+=sheepCount;
		}
		else {
			wolfAnswer+=wolfCount;
		}
	}

}
