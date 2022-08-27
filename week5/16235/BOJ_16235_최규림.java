package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 나무 재테크
public class BOJ_16235_최규림 {

	static int N, M, K;
	static int[][] map;
	static int[][] robot;
	static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class Tree implements Comparable<Tree> {
		int r, c, age;

		Tree(int r, int c, int age) {
			this.r = r;
			this.c = c;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}
	}

	static PriorityQueue<Tree> treePQ = new PriorityQueue<>();
	static Queue<Tree> deadQ = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		robot = new int[N][N];

		// 초기값(모든 칸에 5만큼 양분)
		for (int r = 0; r < N; r++) {
			Arrays.fill(map[r], 5);
		}
		
		// 각 좌표별 양분
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				robot[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int z = Integer.parseInt(st.nextToken());

			Tree tree = new Tree(x, y, z);
			treePQ.add(tree);
		}

		while (K-- > 0) {
			spring();
			summer();
			autumn();
			winter();
		}

		System.out.println(treePQ.size());
	}

	static void spring() {
		//기존의 나무 처리 후(우선순위 큐 사용), 다시 나무 데이터를 저장하기 위한 임시 우선순위큐
		PriorityQueue<Tree> temp = new PriorityQueue<>();
		while (!treePQ.isEmpty()) {
			Tree tree = treePQ.poll();
			if (map[tree.r][tree.c] < tree.age) {
				deadQ.add(tree);
			} else {
				map[tree.r][tree.c] -= tree.age;
				tree.age += 1;
				temp.add(tree);
			}
		}

		treePQ = temp;
	}

	static void summer() {
		while (!deadQ.isEmpty()) {
			Tree tree = deadQ.poll();
			map[tree.r][tree.c] += tree.age / 2;
		}
	}

	static void autumn() {
		PriorityQueue<Tree> temp = new PriorityQueue<>();
		while (!treePQ.isEmpty()) {
			Tree tree = treePQ.poll();
			temp.add(tree);

			if (tree.age % 5 == 0) {
				for (int i = 0; i < 8; i++) {
					int nr = tree.r + dr[i];
					int nc = tree.c + dc[i];

					if (checkRange(nr, nc)) {
						temp.add(new Tree(nr, nc, 1));
					}
				}
			}
		}
		treePQ = temp;
	}

	static void winter() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] += robot[r][c];
			}
		}
	}

	static int[][] deepcopy() {
		int[][] copy = new int[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				copy[r][c] = map[r][c];
			}
		}
		return copy;
	}

	static boolean checkRange(int r, int c) {
		return (r >= 0) && (r < N) && (c >= 0) && (c < N);
	}

}
