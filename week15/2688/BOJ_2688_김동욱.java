package boj;

import java.util.Scanner;

public class BOJ_2688_김동욱 {
	static int[] nums;
	static long[][]d;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb =new StringBuilder();
		int testcase= sc.nextInt();
		nums = new int[testcase];
		
		for(int tc =0;tc<testcase;tc++) {
			nums[tc]=sc.nextInt();
			
		} // end input
		d = new long[10][65];
		for(int i=0;i<10;i++) {
			d[i][1]=1; // 일의 자리는 하나 밖에 없음
		}
		
		for(int i=2;i<65;i++) {
			for(int j=0;j<10;j++) {
				long add=0;
				for(int k=0;k<=j;k++) {
					add+= d[k][i-1];
				}
				d[j][i]=add;
			}
		}
		
		for(int s: nums) {
			long sum=0;
			for(int i=0;i<10;i++) {
				sum+=d[i][s];
			}
			sb.append(sum+"\n");
		}
		System.out.println(sb);
	}
}