package week15;

import java.util.*;
import java.io.*;

// 상어 중학교
public class BOJ_21609_최규림 {
	
	static int N, M, score;
	static int[][] board;
	static boolean[][] visited;
	static int[]dr = {-1, 1, 0, 0};
	static int[]dc = {0, 0, -1, 1};
	
	static class Point{
		int r, c;
		Point(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	static class Group implements Comparable<Group>{
		int startR, startC, rainbowCnt;
		ArrayList<Point> list;
		
		Group(int startR, int startC){
			this.startR = startR;
			this.startC = startC;
			this.rainbowCnt = 0;
			list = new ArrayList<Point>();
		}

		@Override
		public int compareTo(Group o) {
			if(this.list.size() != o.list.size()) return o.list.size() - this.list.size();
			if(this.rainbowCnt != o.rainbowCnt) return o.rainbowCnt - this.rainbowCnt;
			if(this.startR != o.startR) return o.startR - this.startR;
			return o.startC - this.startC;			
		}				
	}		
	
	static PriorityQueue<Group> pq;

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		score = 0;
		
		board = new int[N][N];		
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}				
		
		while(true) {
			makeGroup();			
			if(pq.size() == 0) break;
			// 1. 가장 큰 그룹 찾기
			Group group = pq.poll();			
			
			// 2. 제거 및 점수 획득
			for(Point p:group.list) {				
				board[p.r][p.c] = 9; 
			}						
			score += Math.pow(group.list.size(), 2);
			
			// 3. 중력 작용
			useGravity();
			
			// 4. 회전
			rotate();
			
			// 5. 중력 작용
			useGravity();						

		}
		
		System.out.println(score);
	}
	
	// 1. 블록 그룹 만들기
		static void makeGroup() {
			visited = new boolean[N][N];
			pq = new PriorityQueue<Group>();
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					if(visited[r][c]) continue;
					if(board[r][c] <= 0 || board[r][c] == 9) continue;
					bfs(r, c, board[r][c]);
				}
			}
		}
	
	// 3. 5. 중력
	static void useGravity() {		
		for(int c=0; c<N; c++) {
			Queue<Integer> q = new ArrayDeque();
			for(int r=N-1; r>=0; r--) {
				if(board[r][c] != 9) {
					q.add(board[r][c]);
				}
			}
			
			for(int r=N-1; r>=0; r--) {				
				
				// 검은 블록 만난 경우
				if(board[r][c] == -1) {
					if(q.size() > 0 && q.peek() == -1)
						q.poll(); // 그대로 배치
				} 
				else {
					// 갱신 블록이 없거나, 다음번 블록이 검은 블록인 경우
					if(q.size() == 0 || q.peek() == -1) {
						board[r][c] = 9; // 갱신 불가
					}else {
						board[r][c] = q.poll(); // 갱신
					}
				}				
			}			
		}
	}
	
	// 4. 회전(반시계 90도)
	static void rotate() {
		Queue<Integer> q = new ArrayDeque();		
		for(int r=0; r<N; r++) {			
			for(int c=N-1; c>=0; c--) {
				q.add(board[r][c]);
			}
		}
		
		for(int c=0; c<N; c++) {
			for(int r=0; r<N; r++) {
				board[r][c] = q.poll();
			}
		}
	}
	
		
	static void bfs(int startR, int startC, int color) {
		Queue<Point> q = new ArrayDeque();
		q.add(new Point(startR, startC));
		visited[startR][startC] = true;
					
		Group group = new Group(startR, startC);
		group.list.add(new Point(startR, startC));
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			for(int i=0; i<4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];
				
				if(!checkRange(nr, nc)) continue;
				if(visited[nr][nc]) continue;
				if(board[nr][nc] == -1) continue;
				if(board[nr][nc] == 0 || board[nr][nc] == color) {
					if(board[nr][nc] == 0) group.rainbowCnt++;											
					visited[nr][nc] = true;
					group.list.add(new Point(nr, nc));
					q.add(new Point(nr, nc));
				}
			}
		}
		
		// 그룹 생성 여부 확인(블록 최소 2개)
		if(group.list.size() < 2) return;
		
		pq.add(group);
		
		// 무지개 블록 방문 여부 취소		
		for(Point p:group.list) {					
			if(board[p.r][p.c] == 0) {
				visited[p.r][p.c] = false; 
			}
		}
	}	
		
	static boolean checkRange(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}
	
	static void print() {
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				System.out.print(board[r][c] + " ");
			}
			System.out.println();
		}
	}
}
