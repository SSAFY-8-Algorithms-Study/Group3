package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1504특정한최단경로 {
	static class Node implements Comparable<Node>{
		int end;
		int weight;
		Node(int end, int weight){
			this.end = end;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node o) {
			return weight - o.weight;
		}
	}
	static int N,E,p1,p2;
	static ArrayList<Node>[] list;
	static int[] count;
	static int MAX = 200000000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		list = new ArrayList[N+1];
		for(int i=0;i<N+1;i++) {
			list[i] = new ArrayList<>();
		}
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list[start].add(new Node(end,weight));
			list[end].add(new Node(start,weight));
		}
		st = new StringTokenizer(br.readLine());
		p1 = Integer.parseInt(st.nextToken());
		p2 = Integer.parseInt(st.nextToken());
		//다익스트라 1 -> p1 -> p2 -> N
		// 1 -> p2 -> p1 -> N 두가지경우중 작은값
		int result1 = dij(1,p1) + dij(p1,p2) + dij(p2,N);
		int result2 = dij(1,p2) + dij(p2,p1) + dij(p1,N);
		if(result1 >= MAX && result2 >= MAX) System.out.println(-1);
		else {
			System.out.println(Math.min(result1, result2));
		}
	}
	private static int dij(int start, int end) {
		count = new int[N+1];
		for(int i=0;i<N+1;i++) {
			count[i] = 200000000;
		}
		PriorityQueue<Node> que = new PriorityQueue<>();
		que.add(new Node(start,0));
		count[start] = 0;
		while(!que.isEmpty()) {
			Node now = que.poll();
			if(count[now.end] < now.weight) continue;
			
			for(int i=0;i<list[now.end].size();i++) {
				Node next = list[now.end].get(i);
				if(count[next.end] > now.weight + next.weight) {
					count[next.end] = now.weight + next.weight;
					que.add(new Node(next.end, count[next.end]));
				}
			}
		}
		return count[end];
	}

}
