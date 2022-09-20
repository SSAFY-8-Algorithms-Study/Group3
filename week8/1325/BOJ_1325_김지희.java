package week08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1325_김지희 {
	static int N, M, maxValue;
	static ArrayList<Integer> arr[];
	static int[] count;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new ArrayList[N+1];
		count = new int[N+1];
		
		for(int i=0; i<=N; i++) {
			arr[i] = new ArrayList<>();
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			arr[b].add(a);
		}
		maxValue = Integer.MIN_VALUE;
		for(int i=1; i<N+1; i++) {
			bfs(i);
		}
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<N+1; i++) {
			if(maxValue==count[i]) {
				sb.append(i+" ");
			}
		}
		System.out.println(sb);
		
	}
	
	private static void bfs(int start) {
		Queue<Integer> Q = new LinkedList<>();
		int[] visit = new int[N+1];
		Q.add(start);
		visit[start]=1;
		
		while(!Q.isEmpty()) {
			int cur = Q.poll();
			for(int next : arr[cur]) {
				if(visit[next]==1) continue;
				
				visit[next] = 1;
				count[start]++;
				Q.add(next);
			}
		}
		maxValue = Math.max(maxValue, count[start]);
	}
}
