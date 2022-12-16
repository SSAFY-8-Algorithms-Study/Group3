package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_11060_김지희 {
	static int N, arr[];
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		int dp[] = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] visit = new int[N+1];
		Queue<Integer> Q = new LinkedList<>();
		Arrays.fill(visit, Integer.MAX_VALUE);
		Q.add(1);
		visit[1] = 0;
		
		while(!Q.isEmpty()) {
			int cur = Q.poll();
			
			for(int i=1; i<=arr[cur]; i++) {
				if(cur+i>N) break;
				if(visit[cur+i]>visit[cur]+1) {
					visit[cur+i] = visit[cur]+1;
					Q.add(cur+i);
				}
			}
		}
		System.out.println(visit[N] ==Integer.MAX_VALUE? -1 : visit[N]);
		
	}
	
}
