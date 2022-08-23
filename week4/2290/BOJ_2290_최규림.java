package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// LCD Test
public class BOJ_2290_최규림 {

	static int s, r, c;
	static int R, C;
	static String n;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		s = Integer.parseInt(st.nextToken()); // 크기
		n = st.nextToken(); // 모니터에 나타내야 할 수

		int numCnt = n.length();
		r = 2 * s + 3;
		c = s + 2;
		C = c * numCnt;
		map = new char[r][C];
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = ' ';
			}
		}

		for (int i = 0; i < n.length(); i++) {
			int sr = 0, sc = c * i, er = r, ec = c * (i + 1);
			switch (n.charAt(i) - '0') {
			case 0:
				case0(sr, sc, er, ec);
				break;
			case 1:
				case1(sr, sc, er, ec);
				break;
			case 2:
				case2(sr, sc, er, ec);
				break;
			case 3:
				case3(sr, sc, er, ec);
				break;
			case 4:
				case4(sr, sc, er, ec);
				break;
			case 5:
				case5(sr, sc, er, ec);
				break;
			case 6:
				case6(sr, sc, er, ec);
				break;
			case 7:
				case7(sr, sc, er, ec);
				break;
			case 8:
				case8(sr, sc, er, ec);
				break;
			case 9:
				case9(sr, sc, er, ec);
				break;
			}
		}
		print();
	}

	static void case0(int sr, int sc, int er, int ec) {

		for (int j = sc + 1; j < ec - 1; j++) {
			map[0][j] = '-';
		}

		for (int i = sr + 1; i < er - 1; i++) {
			map[i][sc] = '|';
			map[i][ec - 1] = '|';
		}
		map[r / 2][sc] = ' ';
		map[r / 2][ec - 1] = ' ';

		for (int j = sc + 1; j < ec - 1; j++) {
			map[er - 1][j] = '-';
		}
	}

	static void case1(int sr, int sc, int er, int ec) {

		for (int i = sr + 1; i < er - 1; i++) {
			map[i][ec - 1] = '|';
		}
		map[r / 2][ec - 1] = ' ';
	}

	static void case2(int sr, int sc, int er, int ec) {
		for (int j = sc + 1; j < ec - 1; j++) {
			map[sr][j] = '-';
			map[r / 2][j] = '-';
			map[er - 1][j] = '-';
		}

		for (int i = sr + 1; i < er - 1; i++) {
			if (i < r / 2) {
				map[i][ec - 1] = '|';
			} else if (i > r / 2) {
				map[i][sc] = '|';
			}
		}
	}

	static void case3(int sr, int sc, int er, int ec) {
		for (int j = sc + 1; j < ec - 1; j++) {
			map[sr][j] = '-';
			map[r / 2][j] = '-';
			map[er - 1][j] = '-';
		}

		for (int i = sr + 1; i < er - 1; i++) {
			if (i != r / 2) {
				map[i][ec - 1] = '|';
			}
		}
	}

	static void case4(int sr, int sc, int er, int ec) {
		for (int j = sc + 1; j < ec - 1; j++) {
			map[r / 2][j] = '-';
		}

		for (int i = sr + 1; i < er - 1; i++) {
			if (i < r / 2) {
				map[i][sc] = '|';
				map[i][ec - 1] = '|';
			} else if (i > r / 2) {
				map[i][ec - 1] = '|';
			}
		}
	}

	static void case5(int sr, int sc, int er, int ec) {
		for (int j = sc + 1; j < ec - 1; j++) {
			map[sr][j] = '-';
			map[r / 2][j] = '-';
			map[er - 1][j] = '-';
		}

		for (int i = sr + 1; i < er - 1; i++) {
			if (i < r / 2) {
				map[i][sc] = '|';
			} else if (i > r / 2) {
				map[i][ec - 1] = '|';
			}
		}
	}

	static void case6(int sr, int sc, int er, int ec) {
		for (int j = sc + 1; j < ec - 1; j++) {
			map[sr][j] = '-';
			map[r / 2][j] = '-';
			map[er - 1][j] = '-';
		}

		for (int i = sr + 1; i < er - 1; i++) {
			if (i < r / 2) {
				map[i][sc] = '|';
			} else if (i > r / 2) {
				map[i][sc] = '|';
				map[i][ec - 1] = '|';
			}
		}
	}

	static void case7(int sr, int sc, int er, int ec) {
		for (int j = sc + 1; j < ec - 1; j++) {
			map[sr][j] = '-';
		}

		for (int i = sr + 1; i < er - 1; i++) {
			if (i != r / 2) {
				map[i][ec - 1] = '|';
			}
		}
	}

	static void case8(int sr, int sc, int er, int ec) {
		for (int j = sc + 1; j < ec - 1; j++) {
			map[sr][j] = '-';
			map[r / 2][j] = '-';
			map[er - 1][j] = '-';
		}

		for (int i = sr + 1; i < er - 1; i++) {
			if (i != r / 2) {
				map[i][sc] = '|';
				map[i][ec - 1] = '|';
			}
		}
	}

	static void case9(int sr, int sc, int er, int ec) {
		for (int j = sc + 1; j < ec - 1; j++) {
			map[sr][j] = '-';
			map[r / 2][j] = '-';
			map[er - 1][j] = '-';
		}

		for (int i = sr + 1; i < er - 1; i++) {
			if (i < r / 2) {
				map[i][sc] = '|';
				map[i][ec - 1] = '|';
			} else if (i > r / 2) {
				map[i][ec - 1] = '|';
			}
		}
	}

	static void print() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < C; j++) {
				if (j % c == 0 && j != 0) {
					System.out.print(" ");
				}
				System.out.print(map[i][j]);
			}
			System.out.print(" ");
			System.out.println();
		}
	}
}
