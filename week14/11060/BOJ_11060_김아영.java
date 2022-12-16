package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11060_김아영 {

	static int N;
	static int map[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}

		int i = 1;
		int cnt = 0;
		int flag = 0;
		while (true) {
			if (i >= N)
				break;
			int MAX = 0;
			int mindex = 0;
			// 갈수있는 제일 멀리 단, 비교 해야함
			for (int j = i + 1; j <= i + map[i]; j++) {
				if (j >= N) {
					flag = 1;
					break;
				}
				if (MAX < j + map[j]) {
					MAX = j + map[j];
					mindex = j; // 다음 위치
				}
			}
			
			if (map[i] == 0) {
				cnt = -1;
				break;
			}
			
			cnt++;
			i = mindex;
			if (flag == 1) break;
		}

		// System.out.println(flag);
		System.out.println(cnt);

	}

}
