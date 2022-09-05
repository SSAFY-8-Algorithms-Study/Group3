package week6;

import java.util.Scanner;

// 즐거운 단어
public class BOJ_2922_최규림 {

	static int answer, cnt;
	static char[] vowels = { 'A', 'E', 'I', 'O', 'U' };
	static char[] cArr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		cArr = sc.next().toCharArray();
		for (int i = 0; i < cArr.length; i++) {
			if (cArr[i] == '_' || cArr[i] == 'L') {
				continue;
			}
			if (isVowel(cArr[i])) {
				cArr[i] = 'A';
			} else {
				cArr[i] = 'B';
			}
		}

		System.out.println(dfs(0, false, 0, 0));
	}

	// vCnt, cCnt -> 단순 연속 확인 용도
	static long dfs(int idx, boolean isL, int vCnt, int cCnt) {
		// 모음 or 자음 3연속 -> 취급X
		if (vCnt == 3 || cCnt == 3) {
			return 0;
		}

		// 끝까지 진행된 경우 -> L 이 존재했다면 -> 계산 시작
		if (idx == cArr.length) {
			return isL ? 1 : 0;
		}

		long temp = 0;
		char target = cArr[idx];
		idx++;
		// 해당 문자가 _인 경우, 모든 경우 대입 -> 모음, 자음, L
		if (target == '_') {
			// 모음
			temp += dfs(idx, isL, vCnt + 1, 0) * 5; // 모음 5개
			// 자음
			temp += dfs(idx, isL, 0, cCnt + 1) * 20; // 자음 21-1(L)개
			// L
			temp += dfs(idx, true, 0, cCnt + 1); // L도 자음이기 떄문에 3연속 확인을 위해 cCnt + 1
		}
		// 문자 직접 확인 -> 정해진 문자이기 떄문에 따로 곱하지 않음
		else {
			// 모음
			if (target == 'A') {
				temp += dfs(idx, isL, vCnt + 1, 0);
			}
			// 자음
			else if (target == 'B') {
				temp += dfs(idx, isL, 0, cCnt + 1);
			}
			// L
			else {
				temp += dfs(idx, true, 0, cCnt + 1);
			}
		}

		return temp;
	}

	static boolean isVowel(char c) {
		for (char vowel : vowels) {
			if (c == vowel) {
				return true;
			}
		}
		return false;
	}
}
