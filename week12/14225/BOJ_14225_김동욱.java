package boj;

import java.util.Scanner;

public class BOJ_14225_김동욱 {
	static int[] arr;
	static int N;
	static boolean[] num;
	static int sum;
	static boolean[] visited;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		arr = new int[N];
		visited = new boolean[N];
		for(int i=0;i<N;i++) {
			int temp = sc.nextInt();
			
			arr[i]=temp;
		} //  end input
		num = new boolean[2000001];
		
		pow(0);
		for(int i=1;i<num.length;i++) {
			if(!num[i]) {
				System.out.println(i);
				return;
			}
		}
	}

	public static void pow(int idx) {
		if(idx==N) {
			int tempSum=0;
			for(int i=0;i<visited.length;i++) 
			{
				if(visited[i]) {
					tempSum+= arr[i];
				}
			}
			num[tempSum]=true;
			return;
		}
		visited[idx]= true;
		pow(idx+1);
		visited[idx]= false;
		pow(idx+1);
	}

}
