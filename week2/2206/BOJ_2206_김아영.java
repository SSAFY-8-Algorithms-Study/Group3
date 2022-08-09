package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2206 {
	static int [][]direct = {{0,1},{1,0},{0,-1},{-1,0}};
	static boolean[][] visited;
	static int N, M;
	
	public static class Node{
		int x;
		int y;
		int cost;
		int block;
		public Node(int x, int y, int cost, int block) {
			this.x = x;
			this.y = y;
			this.cost = cost;
			this.block = block;
		}
	}
	
	public static int bfs(int x, int y, int[][]maps) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(x,y, 1,0));
		visited[x][y] = true;
		
		while (!q.isEmpty()) {
			Node node = q.poll();
			if (node.x == N-1 && node.y == M-1)return node.cost;	
			for(int i = 0; i < 4; i++) {
                int dx = node.x + direct[i][0];
                int dy = node.y + direct[i][1];
                if (dx<0 || dx>=N || dy<0 || dy>=M)continue;
                if (visited[dx][dy])continue;
                if (maps[dx][dy] == 0) {
                	visited[dx][dy] = true;
                    q.offer(new Node(dx, dy, node.cost + 1, node.block));
                }
                else if (maps[dx][dy] == 1 && node.block == 0) {
                    visited[dx][dy] = true;
                    q.offer(new Node(dx, dy, node.cost + 1, node.block + 1));
                }                    
            }
		}
		return -1;
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int [][]map = new int[N][M];
		visited = new boolean[N][M];
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		System.out.println(bfs(0,0,map));	
	}

}
