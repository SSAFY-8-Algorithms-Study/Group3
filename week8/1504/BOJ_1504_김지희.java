package week08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 특정한 최단 경로
public class BOJ_1504_김지희 {
	static class Edge implements Comparable<Edge>{
		int vertex, dis;
		Edge(int vertex, int dis){
			this.vertex = vertex;
			this.dis = dis;
		}
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.dis-o.dis;
		}
	}
	
	
	static int N, E;
	static ArrayList<Edge>[] list;
	static long[] dist;
	static long answer, tempAns;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N+1];
		
		for(int i=0; i<=N; i++) {
			list[i] = new ArrayList<>();
		}
		dist = new long[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			list[a].add(new Edge(b, c));
			list[b].add(new Edge(a, c));
		}
		
		st = new StringTokenizer(br.readLine());
		int vertex1 = Integer.parseInt(st.nextToken());
		int vertex2 = Integer.parseInt(st.nextToken());
		
		answer=0;
		tempAns =0;
		
		dijkstra(1, vertex1);
		dijkstra(vertex1, vertex2);
		dijkstra(vertex2, N);
		
//		System.out.println(answer);
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		tempAns = answer;
		answer=0;
		
		dijkstra(1, vertex2);
		dijkstra(vertex2, vertex1);
		dijkstra(vertex1, N);
		
		answer = Math.min(answer, tempAns);

		if(answer<0 || answer>=Integer.MAX_VALUE) System.out.println(-1); 
		else {
			System.out.println(answer);	
		}
		
	}
	
	private static void dijkstra(int start, int end) {
		Arrays.fill(dist, Integer.MAX_VALUE);

		
		PriorityQueue<Edge> pQ = new PriorityQueue<>();
		int[] visit = new int[N+1];
		pQ.offer(new Edge(start,0));
//		dist[start] = d;
		dist[start] = 0;
		
		while(!pQ.isEmpty()) {
			Edge cur = pQ.poll();

			if(cur.dis > dist[cur.vertex]) continue;
//			if(visit[cur.vertex]==1) continue;
//			visit[cur.vertex] = 1;
			
			for(Edge e : list[cur.vertex]) {
				if(dist[e.vertex] > cur.dis + e.dis) {
					dist[e.vertex] = cur.dis + e.dis;
					pQ.offer(new Edge(e.vertex, cur.dis + e.dis));
				}
			}
			
		}
		
		answer += dist[end];
//		System.out.print(dist[end] + " ");
	}
}
