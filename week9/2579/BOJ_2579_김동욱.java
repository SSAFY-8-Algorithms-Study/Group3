package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2579_kimdongwook {
	static int N;
	static int[] stairs;
	static int[] d;
	static int answer;

	public static void dp() {
		d[1]= stairs[1];
		d[2]= stairs[1]+stairs[2];
		d[3]= Math.max(stairs[1]+stairs[3], stairs[2]+stairs[3]);
		
		for(int i=4;i<N+1;i++) {
			d[i]= Math.max(stairs[i-1]+d[i-3],d[i-2] )+stairs[i];
		}
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		stairs = new int[N + 1];
		d = new int[N + 1];
		answer = 0;
		for (int i = 1; i < N + 1; i++) {
			stairs[i] = Integer.parseInt(br.readLine()); // 0 번째 계단은 비움
		}
		// end input
		if (N <= 2) {
			for (int i = 1; i < N + 1; i++) {
				answer += stairs[i];
			}
			System.out.println(answer);
			return;
		} else if (N == 3) {
			answer += stairs[N];
			answer += Math.max(stairs[1], stairs[2]);
			System.out.println(answer);
			return;
		} else {
			dp();
			System.out.println(d[N]);
		}

	}

}
