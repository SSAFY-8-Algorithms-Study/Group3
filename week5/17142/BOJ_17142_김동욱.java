package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17142_김동욱 {
	static int N;
	static int M;
	static int[][] board;
	static ArrayList<Point> vq;
	static boolean[] visitedVirus;
	static int toCount = 0;
	static int answer = Integer.MAX_VALUE;
	static int numOfTwo = 0;
	static boolean isFilled = false;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static boolean isValid(int x, int y, boolean[][] visited) {
		if (x < 0 || x >= N || y < 0 || y >= N)
			return false;
		if (visited[x][y])
			return false;
		return true;
	}

	public static void comb(int idx, int count) {
		if (count == M) {
			Queue<Point> queue = new LinkedList<>();
			boolean[][] visited = new boolean[N][N];
			for (int i = 0; i < vq.size(); i++) {
				if (visitedVirus[i]) {
					Point p = vq.get(i);
					// System.out.printf("(%d, %d), ",p.x,p.y);
					visited[p.x][p.y] = true; // 미리 virus visit Check
					queue.add(p);
				}

			}
			int time=0;
			int countVirus=0;
			while (!queue.isEmpty()) {
				int size = queue.size();
				for (int s = 0; s < size; s++) {
					Point tempPoint = queue.poll();
					visited[tempPoint.x][tempPoint.y] = true; // visit Check
					for (int d = 0; d < 4; d++) {
						int nx = tempPoint.x + dx[d];
						int ny = tempPoint.y + dy[d];
						if (isValid(nx, ny, visited)) {
							if (board[nx][ny] < 1) {
								queue.add(new Point(nx, ny));
								visited[nx][ny] = true;
								countVirus++;
							} else if (board[nx][ny] == 2) {
								queue.add(new Point(nx, ny));
								visited[nx][ny] = true;
							}
						}
					}
				}
				time++;
				if(countVirus >=toCount ) {
					answer = Math.min(answer, time);
					isFilled=true;
					return;
				}
			}

			return;
		}

		if (idx == vq.size())
			return;

		visitedVirus[idx] = true;
		comb(idx + 1, count + 1);
		visitedVirus[idx] = false;
		comb(idx + 1, count);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		vq = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int input = Integer.parseInt(st.nextToken());
				if (input == 0)
					toCount++;
				if (input == 2) {
					vq.add(new Point(i, j));
				}
				board[i][j] = input;
			}
		} // end input
			// toCount += numOfTwo-M; //0 의 갯수 와 선택받지 못한 2의 갯수까지 추가
		visitedVirus = new boolean[vq.size()];
		if(toCount==0) {System.out.println(0); return;}
		comb(0, 0);
		if(isFilled) {System.out.println(answer);}
		else System.out.println(-1);
	}

}
