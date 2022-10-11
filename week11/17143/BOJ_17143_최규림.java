package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 낚시왕
public class BOJ_17143_최규림 {
	static int R, C, M, ans;
	static int[][] board;
	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, 1, -1 };

	static class Shark {
		int r, c, s, d, z;

		public Shark(int r, int c, int s, int d, int z) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}

	static Shark[] sharks;
	static boolean[] died;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new int[R + 1][C + 1];
		sharks = new Shark[M + 1];
		died = new boolean[M + 1];

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			// (r, c) 상어 위치
			// s: 속력, d: 방향, z:크기
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			sharks[i] = new Shark(r, c, s, d, z);
			if (board[r][c] != 0) {
				Shark before = sharks[board[r][c]];
				if (before.z > z) {
					died[i] = true;
				} else {
					died[board[r][c]] = true;
					board[r][c] = i;
				}
			} else {
				board[r][c] = i;
			}
		}

		for (int i = 1; i <= C; i++) {
			// 낚시왕 한 칸 이동
			go(i);
		}

		System.out.println(ans);

	}

	static void go(int c) {
		// 열에 있는 상어 중, 땅에 제일 가까운 상어 잡기
		for (int r = 1; r <= R; r++) {
			if (board[r][c] != 0) {
				ans += sharks[board[r][c]].z;
				died[board[r][c]] = true;
				board[r][c] = 0;
				break;
			}
		}

		// 상어 이동
		move();
	}

	static void move() {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 1; i <= M; i++) {
			if (died[i])
				continue;

			Shark shark = sharks[i];
			int r = shark.r;
			int c = shark.c;
			int s = shark.s;
			int d = shark.d;
			board[r][c] = 0;

			// 상
			if (shark.d == 1) {
				s %= (R - 1) * 2;
				while (s-- != 0) {
					if (r == 1) {
						d = 2;
					} else if (r == R) {
						d = 1;
					}
					r += dr[d];
				}

			}
			// 하
			else if (shark.d == 2) {
				s %= (R - 1) * 2;
				while (s-- != 0) {
					if (r == 1) {
						d = 2;
					} else if (r == R) {
						d = 1;
					}
					r += dr[d];
				}

			}
			// 우
			else if (shark.d == 3) {
				s %= (C - 1) * 2;
				while (s-- != 0) {
					if (c == 1) {
						d = 3;
					} else if (c == C) {
						d = 4;
					}
					c += dc[d];
				}

			}
			// 좌
			else {
				s %= (C - 1) * 2;
				while (s-- != 0) {
					if (c == 1) {
						d = 3;
					} else if (c == C) {
						d = 4;
					}
					c += dc[d];
				}
			}

			shark.r = r;
			shark.c = c;
			shark.d = d;

			list.add(i);
		}

		// 상어 재배치
		for (int idx : list) {
			Shark shark = sharks[idx];
			int r = shark.r;
			int c = shark.c;

			if (board[r][c] == 0) {
				board[r][c] = idx;
			} else {
				Shark before = sharks[board[r][c]];
				if (before.z > shark.z) {
					died[idx] = true;
				} else {
					died[board[r][c]] = true;
					board[r][c] = idx;
				}
			}
		}
	}

	static void print() {
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				System.out.print(board[r][c] + " ");
			}
			System.out.println();
		}
	}
}
