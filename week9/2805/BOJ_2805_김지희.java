package week09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2805_김지희 {
	static int N, M;  
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		
		int start=0, end=0;
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			
			if(end<arr[i]) {
				end = arr[i];
			}
		}
		
		while(start<=end) {
			int mid = (start+end)/2;
			long sum = 0;
			for(int i=0; i<N; i++) {
				if(arr[i]-mid>0) {
					sum += (arr[i]-mid);
				}
			}
			
			//원하는 나무 길이보다 적으면 H를 낮춰서 얻어가는양을 늘리기
			if(sum<M) end = mid-1;
			else start = mid+1;
		}
		
		
		System.out.println(start-1);
	}
}
