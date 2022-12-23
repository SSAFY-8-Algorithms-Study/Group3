package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_20437_김동욱 {
	static int[] alphabets;
	static int min;
	static int max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			alphabets = new int[26];
			min = Integer.MAX_VALUE;
			max = 0;
			String W = br.readLine();
			int K = Integer.parseInt(br.readLine()); // end input
			
			if(K==1) {
				System.out.println(1+" "+1);
				continue;
			}
			for (int i = 0; i < W.length(); i++) {
				alphabets[W.charAt(i) - 97]++;
			}
			// System.out.println(Arrays.toString(alphabets));

			for (int start = 0; start < W.length(); start++) {
				if (alphabets[W.charAt(start) - 97] < K)
					continue; // K 개 이하면 검사할 필요 없

					int kCount = 1;
					for (int end = start + 1; end < W.length(); end++) {
						if (W.charAt(start) == W.charAt(end)) {
							kCount++;
						}
						if (kCount == K) {
							min = Math.min(min, end - start + 1);
							max = Math.max(max, end - start + 1);
//							for (int i = start; i <= end; i++) {
//								System.out.print(W.charAt(i) + " ");
//							}
//							System.out.println();
							break;
						}
					}
				
			}
			if (min == Integer.MAX_VALUE || max == 0) {
				System.out.println(-1);
			}
			else
				System.out.println(min + " " + max);
		}

	}

}