package week12;

import java.util.Scanner;

public class BOJ_14225_김지희 {
	static int N, arr[];
	static int[] visit, select;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N];
		int vsize = N*100000+1;
		visit = new int[vsize];
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
		}
		
		dfs(0,0);
		
		for(int i=1; i<vsize; i++) {
			if(visit[i]==0) {
				System.out.println(i);
				break;
			}
		}
	}
	
	private static void dfs(int idx, int sum) {
		
		if(idx==N) {
			if(sum>0) visit[sum]=1;
			return;
		}
		
		dfs(idx+1, sum+arr[idx]);
		dfs(idx+1, sum);
	}
}
