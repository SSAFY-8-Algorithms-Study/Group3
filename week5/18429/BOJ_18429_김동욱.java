package boj;

import java.util.Scanner;

public class BOJ_18429_김동욱 {
	static int N;
	static int K;
	static int[] machines; //0번부터N-1번까지
	static boolean[] visited;
	static int[] selected;
	static int count;
	public static void perm(int idx) {
		if(idx== N) { // 뽑고나서 근손실 시뮬레이션 시작
			int weight =500;
			for(int i=0;i<selected.length;i++) {
				if(weight<500) {
					return;
				}
				weight+=selected[i];
				weight-=K;
			}
			count++;
			return;
		}
		
		for(int i=0;i<N;i++) {
			if(visited[i])continue;
			selected[idx] = machines[i];
			visited[i]=true;
			perm(idx+1);
			visited[i]=false;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		machines = new int[N];
		selected = new int[N];
		visited = new boolean[N];
		for(int i=0;i<N;i++) {
			machines[i]=sc.nextInt();
		}
		perm(0);
		System.out.println(count);
	}

}
