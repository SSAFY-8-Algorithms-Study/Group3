package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ_11403_김동욱 {
	static int N;
	static ArrayList[] adjList;
	static int temp;
	static int[][] board;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		adjList = new ArrayList[N];
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int temp = sc.nextInt();
				if (temp == 1) {
					adjList[i].add(j);

				}
			}
		}

		for (int i = 0; i < N; i++) {
			temp = i;
			boolean[] visited = new boolean[N];
			dfs(i, visited,-1);
			for(int j=0;j<visited.length;j++) {
				if(visited[j]) {
					board[i][j]=1;
				}
			}

		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void dfs(int from, boolean[] visited,int end) { //시작점 과 지금까지 visit
		
		if (from == end) {
			//System.out.println(from + " time to go!");
			//board[from][from] = 1;
			return;
		}
		ArrayList<Integer> a = adjList[from];
		for (int s : a) { // 해당 from만큼 돔
			//System.out.printf("from : %d to %d \n", from,s);
			if (visited[s])
				continue;
			visited[s] = true;
			//board[from][s] = 1;
			//System.out.printf("visited from : %d to %d \n", from, s);
			dfs(s, visited,temp);
		}

	}

}
