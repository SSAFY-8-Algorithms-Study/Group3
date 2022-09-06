package week06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//상근이의 여행
public class BOJ_9372_김지희 {
	static ArrayList<Integer>[] list;
	static int[] visit;
	static int N, M, result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			result = 0;
			
			list = new ArrayList[N+1];
			visit = new int[N+1];
			
			for(int i=0; i<=N; i++) {
				list[i] = new ArrayList<>();
			}
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				
				list[u].add(v);
				list[v].add(u);
			}
			
			Queue<Integer> Q = new LinkedList<>();
			Q.add(1);
			result = 0; 
			while(!Q.isEmpty()){
				int now = Q.poll();
				for(Integer next : list[now]) {
					if(visit[next] == 0) {
						visit[next] =1;
						result++;
						Q.add(next);
					}
				}
			}
			
			System.out.println(result-1);
		}
	}
	
	
}
