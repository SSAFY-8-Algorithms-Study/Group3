package boj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_9372_김동욱 {
	static int N;
	static int M;
	static ArrayList<Integer>[] arr;
	public static void bfs(int start) {
		boolean[] visited = new boolean[N+1];
		Queue<Integer> queue= new LinkedList<>();
		
		queue.add(start);
		int vCount =0;
		while(!queue.isEmpty()) {
			int tmp = queue.poll();
			if(visited[tmp]) continue;
			visited[tmp]=true;
			vCount++;
			if(vCount==N) {System.out.println(vCount-1);break;} // 맨처음에 자기 자신 방문하면서 1더한걸 빼줌
			for(int t : arr[tmp]) {
				if(!visited[t])
					queue.add(t);
			}
		}
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for(int tc=0;tc<TC;tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			arr= new ArrayList[N+1];
			for(int i=0;i<=N;i++) {
				arr[i] = new ArrayList<Integer>();
			}
			for(int i=0;i<M;i++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				arr[from].add(to);
				arr[to].add(from);
			}
			bfs(1);
			
		}

	}

}
