package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16235_김동욱 {
	static int N;
	static int M;
	static int K;
	static int[][] board;
	static int[][] seedBoard;
	static PriorityQueue<Tree> tq;
	static PriorityQueue<Tree> deadQueue;
	static int[] dx= {-1,-1,0,1,1,1,0,-1};
	static int[] dy= {0,1,1,1,0,-1,-1,-1};
	public static class Tree implements Comparable<Tree>{
		int x; 
		int y;
		int age;
		public Tree(int x, int y, int age) {
			this.x=x;
			this.y=y;
			this.age=age;
		}
		@Override
		public int compareTo(Tree o) {
			return this.age-o.age;
				
		}
		@Override
		public String toString() {
			return this.x+","+this.y+","+this.age;
		}
	}
	public static boolean isValid(int x,int y) {
		if(x<0|| x>=N|| y<0|| y>=N)
			return false;
		return true;
	}
	public static Queue<Tree> spring() {
		PriorityQueue <Tree> tempQueue = new PriorityQueue<>();
		while(!tq.isEmpty()) {
			Tree t = tq.poll(); // 영양분 먹을 나무
			if(board[t.x][t.y]>=t.age) {
				board[t.x][t.y]-=t.age;
				t.age++;
				tempQueue.add(t);
			}
			else {
				deadQueue.add(t);
			}
		}
		//System.out.println("Spring "+tempQueue);
		return tempQueue;
	}
	public static void summer() {
		//System.out.println("Dead Tree "+deadQueue);
		while(!deadQueue.isEmpty()) {
			Tree t= deadQueue.poll();
			board[t.x][t.y]+= t.age/2;
		}
	}
	public static Queue<Tree> fall() {
		PriorityQueue <Tree> tempQueue = new PriorityQueue<>();
		while(!tq.isEmpty()) {
			Tree t =tq.poll();
			if(t.age%5==0) {
				for(int d=0;d<8;d++) {
					int nx = t.x+dx[d];
					int ny = t.y+dy[d];
					if(isValid(nx,ny)) {
						tempQueue.add(new Tree(nx,ny,1));
					}
				}
			}
			tempQueue.add(t);
		}
		//System.out.println("Fall "+tempQueue);
		return tempQueue;
	}
	public static void winter() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				board[i][j]+=seedBoard[i][j];
			}
		}
	}
	public static void print(int[][]arr) {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				System.out.printf("%d ",arr[i][j]);
			}
			System.out.println();
		}
		System.out.println("_________________");
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		tq = new PriorityQueue<>();
		seedBoard= new int[N][N];
		board= new int [N][N];
		deadQueue = new PriorityQueue<>();
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				seedBoard[i][j]+= Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				board[i][j]=5;
			}
		}
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			tq.add(new Tree(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())));
		}
		//System.out.println(tq); // tq 나이별로 내림차순까지 완료
		for(int i=0;i<K;i++) {
		tq = (PriorityQueue<Tree>) spring();
		summer();
		tq =(PriorityQueue<Tree>) fall();
		winter();
		}
		System.out.println(tq.size());
	}

}
