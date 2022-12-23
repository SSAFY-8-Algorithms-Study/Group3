package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_20437_김아영 {

	static String str;
	static int k;
	static char arr[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < TC; tc++) {
			str = br.readLine();
			k = Integer.parseInt(br.readLine());
			if (k == 1) {
				System.out.println("1 1");
				continue;
			}
			int MIN = Integer.MAX_VALUE;
			int MAX = Integer.MIN_VALUE;

			for (int i = 0; i < str.length(); i++) {
				int sameword = 1;
				for (int j = i + 1; j < str.length(); j++) {
					if (str.charAt(i) == str.charAt(j))
						sameword++;
					if (sameword == k) {
						// System.out.println(str.charAt(i));
						MIN = Math.min(j - i + 1, MIN);
						MAX = Math.max(j - i + 1, MAX);
						break;
					}
				}
			}

			if (MIN == Integer.MAX_VALUE || MAX == Integer.MIN_VALUE) {
				System.out.println("-1");
			} else {
				System.out.println(MIN + " " + MAX);
			}

		}

	}

}
