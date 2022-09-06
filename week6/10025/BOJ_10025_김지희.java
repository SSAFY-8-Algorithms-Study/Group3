package week06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//게으른 백곰
public class BOJ_10025_김지희 {
	static int N, K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[1000001];
		int temp =0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int g = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());

			arr[x] = g;
		}
		int answer = 0;
		int sum =0;
		for(int i=0; i<=2*K && i < 10000001; i++) {
			sum +=arr[i];
		}
		answer = sum;
		int K2 = 2*K;
		for(int i=1+K2; i<1000001; i++) {
			sum = sum + arr[i] - arr[i-K2-1];
			answer = Math.max(answer, sum);
		}
		System.out.println(answer);
	}
}
