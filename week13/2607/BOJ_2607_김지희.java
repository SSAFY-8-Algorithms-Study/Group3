package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2607_김지희 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String start = br.readLine(); // 시작 문자열
		int len = start.length(); //시작 문자열의 길이
		int[] alphabet = new int[26];

		for (int i = 0; i < len; i++) {
			alphabet[start.charAt(i) - 'A']++;
		} // 시작 문자열이 가진 알파벳 개수 세기

		int answer = 0;
		for (int i = 1; i < N; i++) {
			int[] copy = alphabet.clone();
			String cmp = br.readLine();

			int cmplen = cmp.length();
			int tmp = cmplen;
			for (int j = 0; j < cmplen; j++) {
				int idx = cmp.charAt(j) - 'A';
				if (copy[idx] > 0) {
					copy[idx]--;
					tmp--;
				}
			}

			if (len - 1 == cmplen && tmp == 0) { // 비교 대상 문자열 알파벳이 다 있고 한 글자 적으면
				answer++;
			} else if (len == cmplen) { // 길이가 같은데
				if (tmp == 0)
					answer++; // 문자열에 있는 알파벳도 같으면
				else if (tmp == 1)
					answer++; // 하나만 다르면 바꿀 수 있으니까
			} else if (len + 1 == cmplen && tmp == 1)
				answer++; // 그냥 한글자가 더 많으면 ㄱㅊㄱㅊ
		}

		System.out.println(answer);
	}
}
