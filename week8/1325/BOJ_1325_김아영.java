package week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1325_김아영 {

	static int N,M, ans = Integer.MIN_VALUE;
	static ArrayList<Integer>[] list;
	static int[] cnt;
	static Queue<Integer> q = new LinkedList<>();
    static boolean[] visit;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList[N+1];
		cnt = new int[N+1];
		for (int i = 0; i <=N; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			list[B].add(A); 
		}
		
		for (int i = 1; i <=N; i++) {
			visit = new boolean[N+1]; 
			bfs(i);
		}
		
		for (int i = 1; i <=N; i++) {
			if(cnt[i] == ans) 
				sb.append(i).append(" ");
		}
		System.out.println(sb);
	}
	
	private static void bfs(int number) {
		q.offer(number);
		visit[number] = true;
		while(!q.isEmpty()) {
			int n = q.poll();
			for (int m : list[n]) {
				if(visit[m]) continue;
				cnt[number]++; 
				visit[m] = true;
				q.offer(m);
			}
		}
		ans = Math.max(ans, cnt[number]); 
	}
}
