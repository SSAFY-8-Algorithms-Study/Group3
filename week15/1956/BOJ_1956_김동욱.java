package boj;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_1956_김동욱 {
	static ArrayList<Node>[] adjList;
	static int answer;
	public static class Node{
		int to;
		int val;
		public Node() {}
		public Node(int to, int val) {
			this.to =to;
			this.val=val;
		}
		@Override
		public String toString() {
			return "Node [to=" + to + ", val=" + val + "]";
		}
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E= sc.nextInt();
		answer =Integer.MAX_VALUE;
		adjList = new ArrayList[V+1];
		for(int i=0;i<adjList.length;i++) {
			adjList[i]= new ArrayList<Node>();
		} // 인접리스트 생성
		for(int i=0;i<E;i++) {
			adjList[sc.nextInt()].add(new Node(sc.nextInt(),sc.nextInt()));
		} // end input
//		for(ArrayList<Node> s: adjList) {
//			for(Node n :s) {
//				System.out.print(n);
//			}
//			System.out.println();
//		}
		for(int i=0;i<adjList.length;i++) {
			if(adjList[i].size()>0) { // 비어있지 않으면 실행
			dfs(""+i,i,0);
			}
		}
		if(answer==Integer.MAX_VALUE) {
			System.out.println(-1);
			
		}
		else {
			System.out.println(answer);
		}
		
	}
	public static void dfs(String visited, int idx, int sum) { //1 1 0
		if(sum>=answer) 
			return;
		if(visited.contains(idx+"") && sum>0) {
			answer = Math.min(answer, sum);
			return;
		}
		for(Node n : adjList[idx]) {
			dfs(visited+ (idx+""),n.to,sum+n.val); // 1 2 1 , 1,3,5
		}
	}

}
