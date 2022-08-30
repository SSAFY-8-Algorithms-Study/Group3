package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16918_김동욱 {
	static int R;
	static int C;
	static int N;
	static char[][] board;
	static int[][] bombMap;
	static int time;
	static int[] dx = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] dy = { 0, 0, -1, 1 };

//	public static int[][] deepCopy(int[][] origin) {
//		int[][] copy = new int[R][C];
//		for (int i = 0; i < R; i++) {
//			for (int j = 0; j < C; j++) {
//				copy[i][j] = bombMap[i][j];
//			}
//		}
//		return copy;
//	}

	public static void addTime() { // 시간을 늘려주는 함수
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (bombMap[i][j] >= 1)
					bombMap[i][j] += 1;
			}
		}
	}

	public static boolean isValid(int x, int y) {
		if (x < 0 || x >= R || y < 0 || y >= C) {
			return false;
		}
		return true;
	}

	public static void bombCheck(int[][] origin) {
		//int[][] copy = deepCopy(origin); // 원래 보드 복사떠서 작업
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				int temp = origin[i][j];
				if (temp >= 3) {
					origin[i][j] = 0;
					for (int d = 0; d < 4; d++) {
						int ni = i + dx[d];
						int nj = j + dy[d];
						if (isValid(ni, nj) && origin[ni][nj] >= 1 && origin[ni][nj] < 3) {
							origin[ni][nj] = 0;
						}
					}
				}
			}

		}

	}

	public static void bombFill(int[][] origin) {
		//int[][] copy = deepCopy(origin);
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (origin[i][j] < 1) {
					origin[i][j] = 1;
				}
			}
		}
		//return copy;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		bombMap = new int[R][C];
		time = 1;
		for (int i = 0; i < R; i++) {
			String temp = br.readLine();
			for (int j = 0; j < C; j++) {
				char input = temp.charAt(j);
				if (input == 'O') {
					bombMap[i][j] = 1;
				} else
					bombMap[i][j] = 0;
			}
		} // end input
		for(int t=0;t<N;t++) {
			addTime();
			
		}
		while (true) {
			addTime();
			bombFill(bombMap);
			time++;
			if (time == N)
				break;

			// 2초

			addTime();
			bombCheck(bombMap);
			time++;
			if (time == N)
				break;

			// 3초
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				int tempNum = bombMap[i][j];
				if (tempNum == 0) {
					System.out.printf(".");
				} else
					System.out.printf("O");
			}
			System.out.println();
		}
	}

}