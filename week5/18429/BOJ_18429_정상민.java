package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_18429근손실 {
	static int N,K,cnt,hap3;
	static int[] kg;
	static boolean[] used;
	static int[] routine;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		kg = new int[N];
		used = new boolean[N];
		routine = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			kg[i] = Integer.parseInt(st.nextToken());
			
		}
		perm(0);
		System.out.println(cnt);
	}
	private static void perm(int idx) {
		if(idx == N) {
			int flag = 0;
			hap3 = 500;
			System.out.println(Arrays.toString(routine));
			for(int i=0;i<routine.length;i++) {
				hap3 += kg[routine[i]];
				hap3 -= K;
				if(hap3 < 500) flag = 1;
			}
			if(flag == 0) cnt++;
			System.out.println(flag);
			return;
		}
		for(int i=0;i<N;i++) {
			if(used[i]) continue;
			used[i] = true;
			routine[idx] = i;
			perm(idx+1);
			used[i] = false;
		}
	}
}
