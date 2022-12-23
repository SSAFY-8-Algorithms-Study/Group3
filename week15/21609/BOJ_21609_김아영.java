package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_21609_김아영 {

	static int N, M, result, MAXBolckSize, MAXrainBlock, colBlock, rowBlock; // 행 렬
	static int map[][];
	static boolean visit[][];
	static int direct[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	// bfs로 인접한 애들중 가장 큰 블록 찾기 그리고 갱신하기
	static void bfs(int startx, int starty) {

		Queue<Point> q = new LinkedList<>();
		visit[startx][starty] = true;
		boolean bfsvisit[][] = new boolean[N][N];
		bfsvisit[startx][starty] = true;
		q.add(new Point(startx, starty));
		int size = q.size();
		int blocksize = 0;
		int rainblock = 0;

		while (!q.isEmpty()) {
			size = q.size();
			for (int s = 0; s < size; s++) {
				Point now = q.poll();
				blocksize++;
				for (int d = 0; d < 4; d++) {
					int dx = now.x + direct[d][0];
					int dy = now.y + direct[d][1];
					if (dx < 0 || dy < 0 || dx >= N || dy >= N)
						continue;
					if (visit[dx][dy] || map[dx][dy] == -1 || bfsvisit[dx][dy])
						continue;
					if (map[dx][dy] == map[startx][starty] || map[dx][dy] == 0) {
						if (map[dx][dy] != 0) {
							visit[dx][dy] = true;
						} else {
							rainblock++;
						}
						q.add(new Point(dx, dy));
						bfsvisit[dx][dy] = true;
					}
				}
			}
		}
		// System.out.println(blocksize);
		// 가장 큰 블로 갱신 중
		if (MAXBolckSize < blocksize && blocksize > 1) {
			MAXBolckSize = blocksize;
			MAXrainBlock = rainblock;
			colBlock = startx;
			rowBlock = starty;
		} else if (MAXBolckSize == blocksize && blocksize > 1) {
			if (rainblock > MAXrainBlock) {
				MAXrainBlock = rainblock;
				colBlock = startx;
				rowBlock = starty;
			} else if (rainblock == MAXBolckSize) {
				if (startx > colBlock) {
					colBlock = startx;
					rowBlock = starty;
				} else if (startx == colBlock) {
					rowBlock = Math.max(rowBlock, starty);
				}
			}
		}

	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	// 처음부터 끝까지 모든 애들 한번씩 돌리기
	static boolean autoPlay1() {

		visit = new boolean[N][N];
		// 블록 크기들 초기화
		MAXBolckSize = 0;
		MAXrainBlock = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visit[i][j] || map[i][j] == -1 || map[i][j] == 0 || map[i][j] == M + 1)
					continue;
				// bfs로 인접한 애들중 가장 큰 블록 찾기
				bfs(i, j);
			}
		}
		if (MAXBolckSize <= 1) {
			return false; // 게임 종료
		} else {
			autoPlay2(rowBlock, colBlock);
			return true; // 게임 반복
		}

	}

	static void autoPlay2(int startx, int starty) {

		// 제거하기
		Queue<Point> q = new LinkedList<>();
		boolean bfsvisit[][] = new boolean[N][N];
		bfsvisit[startx][starty] = true;
		q.add(new Point(startx, starty));
		int size = q.size();

		while (!q.isEmpty()) {
			size = q.size();
			for (int s = 0; s < size; s++) {
				Point now = q.poll();
				map[now.x][now.y] = M + 1;
				for (int d = 0; d < 4; d++) {
					int dx = now.x + direct[d][0];
					int dy = now.y + direct[d][1];
					if (dx < 0 || dy < 0 || dx >= N || dy >= N)
						continue;
					if (map[dx][dy] == -1 || bfsvisit[dx][dy])
						continue;
					if (map[dx][dy] == map[startx][starty] || map[dx][dy] == 0) {
						q.add(new Point(dx, dy));
						bfsvisit[dx][dy] = true;
					}
				}
			}
		}
		// 제거 완료
		// 점수 계산
		result = result + MAXBolckSize * MAXBolckSize;

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // input end

		while (true) {
			boolean auto1 = autoPlay1(); // 1, 2번 끝
			if (!auto1)
				break;
			// 중력 작용하기
			
		}
		System.out.println(result);

	}

}
