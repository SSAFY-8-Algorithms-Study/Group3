package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

// 문자열 게임2
public class BOJ_20437_최규림 {

	static int T, K, min, max;
	static char[] W;	
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			W = br.readLine().toCharArray();
			K = Integer.parseInt(br.readLine());
			min = Integer.MAX_VALUE;
			max = -1;

			// a~z 비교
			for (int i = 97; i <= 122; i++) {
				char alpha = (char) i; // a~z				
				Queue<Integer> q = new ArrayDeque<Integer>();

				for (int j = 0; j < W.length; j++) {
					// 비교 대상 알파벳인 경우, q에 삽입
					if (W[j] == alpha) {
						q.add(j);
					}

					// q의 길이가 K가 되면, 길이 계산 + 다음 길이를 위해 맨 앞 index poll
					if (q.size() == K) {
						int len = j - q.poll() + 1;
						min = Math.min(min, len);
						max = Math.max(max, len);
					}
				}
			}

			// 최소, 최대 길이 갱신된 적 없는 경우
			if (min == Integer.MAX_VALUE || max == -1) {
				sb.append(-1 + "\n");
			} else {
				sb.append(min + " " + max + "\n");
			}
		}
		System.out.println(sb.toString());
	}
}
