package boj;

import java.util.Scanner;

public class BOJ_18353_kdw {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] num = new int[N];
		int[] d = new int[N];
		int answer=0;
		for(int i=N-1;i>=0;i--) {
			num[i]= sc.nextInt();
			d[i]=1;
		}
		for(int i=1;i<N;i++) {
			for(int j=0;j<i;j++) {
				if(num[i]>num[j]) {
					d[i]=Math.max(d[i], d[j]+1);
				}
			}
		}
		
		for(int i=1;i<d.length;i++) {
			if(d[i]>answer) {
				answer= d[i];
			}
		}
		if(N==1) {
			System.out.println(0);
			return;
		}
		System.out.println(N-answer);
	}

}
