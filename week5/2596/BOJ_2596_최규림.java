package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 비밀편지
public class BOJ_2596_최규림 {
	static String[] alphabets = { "000000", "001111", "010011", "011100", "100110", "101001", "110101", "111010" };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String input = br.readLine();
		String answer = "";
		for (int i = 0; i < n; i++) {
			String str = input.substring(i * 6, (i + 1) * 6);
			int result = check(str);

			if (result == -1) {
				System.out.println(i + 1);
				return;
			}

			answer += (char) result;
		}
		System.out.println(answer);
	}

	static int check(String x) {
		for (int i = 0; i < 8; i++) {
			if (x.equals(alphabets[i])) {
				return i + 65;
			}
		}

		for (int i = 0; i < 8; i++) {
			if (checkDiff(x, alphabets[i])) {
				return i + 65;
			}
		}

		return -1;

	}

	static boolean checkDiff(String x, String alpha) {
		int diff = 0;
		for (int i = 0; i < 6; i++) {
			if (x.charAt(i) != alpha.charAt(i)) {
				diff += 1;
			}
		}
		return (diff == 1) ? true : false;
	}
}
