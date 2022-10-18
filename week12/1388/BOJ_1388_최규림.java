package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 바닥 장식
public class BOJ_1388_최규림 {
	static int R, C, ans;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int r = 0; r < R; r++) {
			char[] ch = br.readLine().toCharArray();
			for (int c = 0; c < C; c++) {
				map[r][c] = ch[c];
			}
		}

		for (int r = 0; r < R; r++) {
			boolean flag = false;
			for (int c = 0; c < C; c++) {
				if (flag) {
					if (map[r][c] == '|') {
						flag = false;
					}
				} else {
					if (map[r][c] == '-') {
						flag = true;
						ans++;
					}
				}

			}
		}

		for (int c = 0; c < C; c++) {
			boolean flag = false;
			for (int r = 0; r < R; r++) {
				if (flag) {
					if (map[r][c] == '-') {
						flag = false;
					}
				} else {
					if (map[r][c] == '|') {
						flag = true;
						ans++;
					}
				}
			}
		}

		System.out.println(ans);
	}
}
