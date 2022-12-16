package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2003_김지희 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N+1];
		int sum = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int left=0, right=0;
		int answer = 0;
		while(right <= N){
			if(sum < M){
				sum += arr[right++];
			}
			else if(sum > M){
				sum -= arr[left++];
			}
			else{
				answer++;
				sum += arr[right++];
			}
		}
		System.out.println(answer);
		
	}
}
