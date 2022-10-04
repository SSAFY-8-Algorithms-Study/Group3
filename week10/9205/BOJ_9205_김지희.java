package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_9205_김지희 {
//맥주 마시면서 걸어가기
	static class Point{
		int x, y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;
	static 	Point[] arr;
	static ArrayList<Integer>[] list;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		ArrayList<Point> list;
		
		//좌표 입력순서 : 집, 편의점, 락페 좌표
		// 20병으로 갈 수 있는 거리 ... 20x50m = 1000m
		// 음................................
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TC; tc++) {
			N = Integer.parseInt(br.readLine());
			
			arr = new Point[N+2]; // 집1 편의점N 락페1
			for(int i=0; i<N+2; i++) {
				st = new StringTokenizer(br.readLine());
				int x= Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				arr[i] = new Point(x, y);
			} // input end
			
			list = new ArrayList[N+2];
			for(int i=0; i<N+2; i++) {
				list[i] = new ArrayList<>();
			}
			
			//조건을 만족하는 노드끼리 이어주기
			for(int i=0; i<N+2; i++) {
				for(int j=i+1; j<N+2; j++) {
					if(distance(arr[i].x, arr[i].y, arr[j].x, arr[j].y)<=1000) {
						list[i].add(j);
						list[j].add(i);
					}
				}
			}
			
			bfs();
			
		}
	}
	
	private static int distance(int x1, int y1, int x2, int y2) {
		
		return Math.abs(x1-x2) + Math.abs(y1-y2);
		
	}
	
	private static void bfs() {
		Queue<Integer> Q = new LinkedList<>();
		Q.add(0); //0번부터
		int[] visit = new int[N+2]; //방문체크
		visit[0] = 1;
		
		while(!Q.isEmpty()) {
			int cur = Q.poll();
			
			if(cur==N+1) { 
				System.out.println("happy");
				return;
			}
			
			for(int next : list[cur]) {
				if(visit[next]==1) continue;
				
				visit[next] = 1;
				Q.add(next);
			}
		}
		System.out.println("sad");
	}
}
