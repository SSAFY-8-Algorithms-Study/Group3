package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 비슷한 단어
public class BOJ_2607_최규림 {

	static int N, ans;
	static String[] words;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		words = new String[N];
		for (int i = 0; i < N; i++) {
			words[i] = br.readLine();
		}

		isSimilar();
		System.out.println(ans);
	}

	static void isSimilar() {
		String s1 = words[0];
		for (int i = 1; i < N; i++) {
			String s2 = words[i];
			if (Math.abs(s1.length() - s2.length()) <= 1 && cond(s1, s2)) {
				ans++;
			}
		}

	}

	// 알파벳별 개수 차이 누적
	static boolean cond(String s1, String s2) {
		// 2개의 단어 같은 종류의 문자로 이뤄짐
		// 같은 문자는 같은 개수만큼 존재
		int diff = 0;
		int diff_length = Math.abs(s1.length() - s2.length());

		int[] visited1 = new int[43];
		int[] visited2 = new int[43];
		for (int i = 0; i < s1.length(); i++) {
			visited1[s1.charAt(i) - '0']++;
		}

		for (int i = 0; i < s2.length(); i++) {
			visited2[s2.charAt(i) - '0']++;
		}

		// A~Z -> 17~42
		for (int i = 17; i <= 42; i++) {
			diff += Math.abs(visited1[i] - visited2[i]);
		}

		// 하나의 문자를 다른 문자로 바꾸는 경우
		if (diff_length == 0 && diff == 2) {
			return true;
		}

		// 한 문자를 더하거나, 빼도 불가능한 경우
		if (diff > 1) {
			return false;
		}
		return true;
	}
}
