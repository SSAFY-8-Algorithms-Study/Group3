package week15;

import java.io.*;
import java.util.*;

// 운동
public class BOJ_1956_최규림 {
	
	static int V, E;
	static int INF = Integer.MAX_VALUE;
	static int[][] graph;		

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		graph = new int[V+1][V+1];
		for(int i=1; i<=V; i++) {
			Arrays.fill(graph[i], Integer.MAX_VALUE);
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int a, b, c;
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			graph[a][b] = c;						
		}
				
		// 플로이드 워셜
		for(int k=1; k<=V; k++) {
			for(int i=1; i<=V; i++) {
				for(int j=1; j<=V; j++) {					
					if(graph[i][k] == INF || graph[k][j] == INF) continue;
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}
				
		int ans = INF;
		for(int i=1; i<=V; i++) {
			ans = Math.min(ans, graph[i][i]);
		}
		
		if(ans == INF) System.out.println(-1);
		else System.out.println(ans);
	}
			
}
