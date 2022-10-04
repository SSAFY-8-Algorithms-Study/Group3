package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_18353_김지희 {
	static int N;
	static int[] arr, dp; // arr : 병사 전투력 담을 배열
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		dp = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		//input end
		
		dp[0] = 1;
		int answer =0;
		for(int i=1; i<N; i++) {
			int max = 0;
			for(int j=i-1; j>=0; j--) {
				if(arr[j]>arr[i] &&dp[j]>max) max = dp[j];
			} 
			dp[i] = max+1;
			answer = Math.max(answer, dp[i]);
		}
		
        answer = N!=1 ? N-answer : 0;
		System.out.println(answer);
		
		
	}
}
