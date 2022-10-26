package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BOJ_2607_김아영 {

	static int N, result;
	static int onearr[], twoarr[];
	static int onesize, twosize;

	static boolean check(int smallarr[], int bigarr[], int samesize) {

		int chance = 0;
		int keep = 0;
		for (int i = 'A'; i <= 'Z'; i++) {
			int compareSize = Math.abs(bigarr[i] - smallarr[i]);
			if (compareSize == 0) continue;
			else if (compareSize == 1) {
				if (samesize != 1 || keep != 1) chance++;
				keep++;
			} else return false;
			if (chance > 1) return false;
		}
		return true;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		// 첫번째 단어
		onearr = new int[200];
		String str = br.readLine();
		onesize = str.length();
		for (int j = 0; j < str.length(); j++) {
			char c = str.charAt(j);
			onearr[c]++;
		}
		// 첫번쨰 단어 끝

		int cnt = 0;
		for (int i = 0; i < N - 1; i++) {
			str = br.readLine();
			twosize = str.length();
			twoarr = new int[200];
			for (int j = 0; j < str.length(); j++) {
				char c = str.charAt(j);
				twoarr[c]++;
			}
			// 두번쨰까지
			int size = 0;
			if (onesize == twosize) size = 1;
			if (check(twoarr, onearr, size)) cnt++;
		}
		System.out.println(cnt);
	}

}
