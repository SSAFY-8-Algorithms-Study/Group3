package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;

public class BOJ_2138_김아영 {

	static int N, result;
	static String start, end;
	static int arr[];
	static int direct[] = { -1, 0, 1 };

	static String change(int i, String c) {

		for (int d = 0; d < 3; d++) {
			int di = direct[d] + i;
			if (di < 0 || di >= N)
				continue;
			if (c.charAt(di) == '0') {
				 StringBuilder sb = new StringBuilder(c);
				 sb.setCharAt(di, '1');
				 c = sb.toString();
			}
			else {
				 StringBuilder sb = new StringBuilder(c);
				 sb.setCharAt(di, '0');
				 c = sb.toString();
			}
		}
		return c;
	}

	static void dfs(int level, String c) {

		if (c.equals(end)) {
			// System.out.println("----------------" + Arrays.toString(c));
			result = Math.min(result, level);
			return;
		}
		if (level == N)
			return;

		for (int i = 0; i < N; i++) {
			String origin = c;
			String changeS = change(i, c);
			dfs(level + 1, changeS);
			c = origin;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		start = br.readLine();
		end = br.readLine();
		result = Integer.MAX_VALUE;
		dfs(0, start);
		System.out.println(result);
	}

}
