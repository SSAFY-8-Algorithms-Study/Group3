package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11403_김지희 {
	static int N;
	static int[][] graph, visit, answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		graph = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = new int[N+1][N+1];
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(graph[i][k] ==1 && graph[k][j]==1)
						graph[i][j] = 1;
				}
			}
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				System.out.print(graph[i][j]+ " ");
			}System.out.println();
		}
	}
}
