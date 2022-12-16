package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11060_김동욱 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] num = new int[N + 1];
		int[] d = new int[10000];
		for (int i = 1; i <= N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		} // end input
		Arrays.fill(d, Integer.MAX_VALUE); // max 값으로 초기화

		d[1] = 0;
		for (int i = 1; i <= N; i++) { 
			if (d[i] >= Integer.MAX_VALUE)
				continue;
			for (int j = 1; j <= num[i]; j++) { // num[i] 이하 값만큼 점프 가능 
				d[i + j] = Math.min(d[i + j], d[i] + 1); // 전 값  +1 혹은 기존 값
			}
		}
		int answer = d[N];
		if (d[N] >= Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(answer);
	}

}
