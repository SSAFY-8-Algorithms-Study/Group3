package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_18428_kimdongwook {
	static int N;
	static char[][] board;
	static ArrayList<Point> teachers;
	static HashSet<Point> tempTargets;
	static ArrayList<Point> students;
	static Point[] targets;
	static boolean visited[];
	static ArrayList<Integer> tests;
	static int[] dx= {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static class Point{
		int x;
		
		int y;
		public Point() {}
		public Point(int x, int y) {
			this.x=x;
			this.y=y;
		}
		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
		 @Override
		    public int hashCode() {
		      return x * 1000 + y;
		    }
		@Override
		public boolean equals(Object pointObject) {
		    if (this == pointObject) return true;
		    if (pointObject == null || getClass() != pointObject.getClass()) return false;
		    Point temp = (Point) pointObject;
		    return x == temp.x && y == temp.y;
		}
	}
	public static void printBoard(char[][] board) {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				System.out.printf("%c ",board[i][j]);
			}
			System.out.println();
		} // end input
		System.out.println("______________");
	}
	public static ArrayList<Point> watchStudents(){
		ArrayList<Point> r = new ArrayList<Point>();
		for(Point t : teachers) {
			for(Point s: students) {
				
				if(t.x==s.x) {
					
					int max =0;
					int min=0;
					if(t.y >s.y) {
						max = t.y;
						min = s.y;
					}
					else {
						max = s.y;
						min =t.y;
					}
					for(int i=max-1;i>min;i--) {
						if(board[t.x][i]=='S')continue;
					tempTargets.add(new Point(t.x,i));
					}
					
				}
				else if(t.y==s.y) {
					
					int max =0;
					int min=0;
					if(t.x >s.x) {
						max = t.x;
						min = s.x;
					}
					else {
						max = s.x;
						min =t.x;
					}
					for(int i=max-1;i>min;i--) {
						if(board[i][t.y]=='S')continue;
						
					tempTargets.add(new Point(i,t.y));
					}
				}
			}
		}
		System.out.println(tempTargets);
		r = new ArrayList<>(tempTargets);
		return r;
	}
	public static char[][] copyMap(char[][] board) {
		char[][] tempBoard = new char[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				tempBoard[i][j]= board[i][j];
			}
		}
		return tempBoard;
	}
	public static void comb(int idx, int count) {
		if(count==3) {
			ArrayList<Point> wall = new ArrayList<>();
			for(int i=0;i<targets.length;i++) {
				if(visited[i]) {
					wall.add(targets[i]);
				}
			}
			System.out.println(wall);
			char[][] tempBoard = copyMap(board);
			for(Point p: wall) {
				tempBoard[p.x][p.y]= 'O';
			}
			printBoard(tempBoard);
			boolean isDetected = false;
			for(Point t: teachers) {
				for(int i=t.x;i>=0;i--) {
					if(tempBoard[i][t.y]=='O')
						break;
					else if(tempBoard[i][t.y]=='S') {
						return;
					}
						
				}
				for(int i=t.y;i>=0;i--) {
					if(tempBoard[t.x][i]=='O')
						break;
					else if(tempBoard[t.x][i]=='S') {
						return;
					}
						
				}
				for(int i=0;i<t.x;i++) {
					if(tempBoard[i][t.y]=='O')
						break;
					else if(tempBoard[i][t.y]=='S') {
						return;
					}
						
				}
				for(int i=0;i<t.y;i++) {
					if(tempBoard[t.x][i]=='O')
						break;
					else if(tempBoard[t.x][i]=='S') {
						return;
					}
						
				}
			}
			if(!isDetected) {
				tests.add(1);
			}
			
			return;
		}
		if(idx== targets.length)
			return;
		
		visited[idx]=true;
		comb(idx+1,count+1);
		visited[idx]=false;
		comb(idx+1,count);
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		board= new char[N][N];
		teachers = new ArrayList<>();
		students = new ArrayList<>();
		tempTargets = new HashSet<>();
		tests= new ArrayList<>();
		for(int i=0;i<N;i++) {
			
			String temp = br.readLine().replace(" ", "");
			for(int j=0;j<N;j++) {
				char c = temp.charAt(j);
				if(c=='T') {
					teachers.add(new Point(i,j));
				}
				else if(c=='S') {
					students.add(new Point(i,j));
				}
				board[i][j]= c;
			}
		} // end input
		targets = watchStudents().toArray(new Point[tempTargets.size()]);
		visited = new boolean[tempTargets.size()];
		comb(0,0);
		System.out.println(tests);
		for(int s: tests) {
			
			if(s==1) {
				System.out.println("YES");
				return;
			}
		}
		System.out.println("NO");
	}

}
