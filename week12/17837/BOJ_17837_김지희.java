package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 새로운 게임2
public class BOJ_17837_김지희 {
	static class Chess {
		int i, j;
		int dir;

		Chess(int i, int j, int dir) {
			this.i = i;
			this.j = j;
			this.dir = dir;
		}
	}

	static int N, K, answer;
	static ArrayList<Integer>[][] board;
	static int[][] map;
	static Chess[] chessman;

	static int[] di = { 0, 0, -1, 1 };
	static int[] dj = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		board = new ArrayList[N + 1][N + 1];
		map = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				board[i][j] = new ArrayList<>();
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		chessman = new Chess[K + 1];
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			chessman[i] = new Chess(r, c, dir-1);
			board[r][c].add(i); // 보드 위치에 체스말 번호 넣어주기
		}
		// input end

		int turn = 0;
		while (true) {
			if (turn > 1000) {
				System.out.println(-1);
				System.exit(0);
			}

			turn++;

			for (int k = 1; k <= K; k++) {// 1번부터 K번 말 이동

				Chess cur = chessman[k];

				int ni = cur.i + di[cur.dir];
				int nj = cur.j + dj[cur.dir];

				// 범위를 벗어난 경우 = 파란색일 경우
				if (ni <= 0 || nj <= 0 || ni > N || nj > N || map[ni][nj] == 2) {
					int d = cur.dir % 2 == 0 ? cur.dir + 1 : cur.dir - 1;
					ni = cur.i + di[d];
					nj = cur.j + dj[d]; // 방향 바꿔준 방향으로 한칸 이동
					cur.dir = d;
					chessman[k].dir = d;

				}

				if ((ni > 0 && nj > 0 && ni <= N && nj <= N) && map[ni][nj] == 0) {// 흰색인 경우에는 그 칸으로 이동한다!!!! 이미 말이 존재하면
																					// 가장 위에 말을 올려버린다
					ArrayList<Integer> movelist = new ArrayList();

					int idx = board[cur.i][cur.j].indexOf(k);

					for (int i = idx; i < board[cur.i][cur.j].size(); i++) {
						movelist.add(board[cur.i][cur.j].get(i));
						board[ni][nj].add(board[cur.i][cur.j].get(i));
					}

					for (int move : movelist) {
						board[chessman[move].i][chessman[move].j].remove((Integer) move);

						chessman[move].i = ni;
						chessman[move].j = nj;
					}
				}

				else if ((ni > 0 && nj > 0 && ni <= N && nj <= N) && map[ni][nj] == 1) {// 빨간색인 경우에는 이동한 후에 순서가 reverse 돼서 추가된다.
					ArrayList<Integer> movelist = new ArrayList<>();

					int idx = board[cur.i][cur.j].indexOf(k);

					for (int i = idx; i < board[cur.i][cur.j].size(); i++) {
						movelist.add(board[cur.i][cur.j].get(i));
					}

					Collections.reverse(movelist);

					for (int move : movelist) {
						board[chessman[move].i][chessman[move].j].remove((Integer) move);
						chessman[move].i = ni;
						chessman[move].j = nj;
					}

					for (int move : movelist) {
						board[ni][nj].add(move);
					}
				}
				
				for(int i=1; i<=K; i++) {
					int r = chessman[i].i;
					int c = chessman[i].j;
					if(board[r][c].size()>=4) {
						System.out.println(turn);
						System.exit(0);
					}
				}

			}
		}
	}
}
