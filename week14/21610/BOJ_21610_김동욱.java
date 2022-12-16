package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_21610_김동욱 {
	static int N;
	static int M;
	static int[][] board;
	static boolean[][] isCloud;
	static boolean[][] thisCloud;
	static int[][] tempWater;
	static int answer;
	static int[] dx = { 0, 0, -1, -1, -1, 0, 1, 1, 1 }; // ←, ↖, ↑, ↗, →, ↘, ↓, ↙
	static int[] dy = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };

	static int[] diagX = { -1, -1, 1, 1 };
	static int[] diagY = { -1, 1, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		isCloud = new boolean[N][N];
		answer = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 초기 구름 설정
		isCloud[N - 1][0] = true;
		isCloud[N - 2][0] = true;
		isCloud[N - 1][1] = true;
		isCloud[N - 2][1] = true;
		for (int i = 0; i < M; i++) {
			tempWater = new int[N][N];
			st = new StringTokenizer(br.readLine());
			int tempDir = Integer.parseInt(st.nextToken());
			int tempAm = Integer.parseInt(st.nextToken());
			moveClouds(tempDir, tempAm); // 구름 이동
			thisCloud = copyClouds(isCloud);
			addWater(); // 구름에 있는 칸에 비 추가 후 구름 삭제
			copyWater(); // 대각선 방향에 물이 있는 수 만큼 물 추가
			makeClouds(); // 구름 설정 후 물의양 -2
			//printBoard();
			//printCloud();
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				answer+=board[i][j];
			}
		}
		System.out.println(answer);
		// end input
	}

	public static void printCloud() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(isCloud[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void printBoard() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static boolean[][] copyClouds(boolean[][] origin) {
		boolean[][] copy = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copy[i][j] = origin[i][j];
			}
		}
		return copy;
	}

	public static void addWater() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (isCloud[i][j]) {
					board[i][j]++;
					// isCloud[i][j]=false; // 구름 제거 아직 하지말자
				}
			}
		}
	}

	public static boolean isValid(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N)
			return false;
		if (board[x][y] == 0) {
			return false;
		}
		return true;
	}

	public static void add() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] += tempWater[i][j];
			}
		}
	}

	public static void makeClouds() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (thisCloud[i][j]) {
					thisCloud[i][j] = false;
					continue;
				}

				if (board[i][j] >= 2) {
					board[i][j] -= 2;
					isCloud[i][j] = true;
				}

			}
		}
	}

	public static void copyWater() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (isCloud[i][j]) {
					for (int d = 0; d < 4; d++) {
						int dx = i + diagX[d];
						int dy = j + diagY[d];
						if (isValid(dx, dy)) {
							tempWater[i][j]++;
						}
					}
					isCloud[i][j] = false;
				}
			}
		}
		add();
	}

	public static void moveClouds(int dir, int amount) {
		boolean[][] copy = new boolean[N][N];

		int dirX = dx[dir] * amount;
		int dirY = dy[dir] * amount;
		// System.out.println("dirx "+dirX+"diry "+dirY);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (isCloud[i][j]) { // 구름 일 때
					isCloud[i][j] = false;
					int tempX = i + dirX;
					int tempY = j + dirY;

					if (tempX < 0 || tempX >= N) {

						if (tempX < 0) {
							tempX = N - (Math.abs(tempX) % N);
						} else {
							tempX = 0 + (tempX % N);
						}
						if(tempX==N) {
							tempX=0;
						}
					}
					if (tempY < 0 || tempY >= N) {
						if (tempY < 0) {
							tempY = N - (Math.abs(tempY) % N);
						} else {
							tempY = 0 + (tempY % N);
						}
						if(tempY==N) {
							tempY=0;
						}
					}
					//System.out.println(tempX+" "+tempY);
					copy[tempX][tempY] = true;

				}
			}
		}
		isCloud = copyClouds(copy);
	}

}
