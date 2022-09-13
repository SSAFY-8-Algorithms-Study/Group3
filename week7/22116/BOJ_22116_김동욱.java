package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class BOJ_22116_김동욱 {
	static int N;
	static int[][] board;
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int maxHeight;

	static class Point implements Comparable<Point> {
		int x;
		int y;
		int slope;

		public Point(int x, int y, int slope) {
			this.x = x;
			this.y = y;
			this.slope = slope;
		}

		@Override
		public int compareTo(Point o) {
			// TODO Auto-generated method stub
			return this.slope - o.slope; // pq에서 가장 높이가 낮은 경사 우선순위
		}
		public String toString() {
			return this.x +" "+ this.y+" " + this.slope;
		}
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.printf("%d ", board[i][j]);
			}
			System.out.println();
		}
		System.out.println("_______________");
	}

	public static boolean isValid(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N)
			return false;
		if (visited[x][y])
			return false;
		return true;
	}

	public static void shortestPath(Point p) {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.add(p);
		while (!pq.isEmpty()) {
			int size = pq.size();
			for (int s = 0; s < size; s++) {
				Point tempPoint = pq.poll();
				visited[tempPoint.x][tempPoint.y] = true;
				maxHeight = Math.max(maxHeight, tempPoint.slope);
				//System.out.println("현재 Point:" +tempPoint+"현재 최대 경사 :"+ maxHeight);
				if(tempPoint.x == N-1 && tempPoint.y== N-1) { // pq에 Point 가 남았음에도 목적지를 도달하면 return
					return;
				}
				for (int d = 0; d < 4; d++) {
					int nx = tempPoint.x + dx[d];
					int ny = tempPoint.y + dy[d];

					if (isValid(nx, ny)) {
						int tempSlope = Math.abs(board[tempPoint.x][tempPoint.y] - board[nx][ny]); // 다음 갈 경로의 x,y,높이차 pq에 저장
						pq.add(new Point(nx, ny, tempSlope));
					}
				}
			}
			//System.out.println(pq);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		maxHeight = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		} // end input

		visited = new boolean[N][N];
		shortestPath(new Point(0, 0, 0));
		System.out.println(maxHeight);
	}
}