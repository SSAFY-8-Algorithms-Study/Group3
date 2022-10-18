import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static int[] di = {1,-1,0,0};
	static int[] dj = {0,0,1,-1};
	static char[][] map;
	static boolean[][] visit;
	static Point sang;//상근이 위치 저장할 변수
	static ArrayList<Point> fire; //불 위치 저장할 리스트
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			visit = new boolean[N][M];
			fire = new ArrayList<>();
			for(int i=0;i<N;i++) {
				map[i] = br.readLine().toCharArray();
				for(int j=0;j<M;j++) {
					if(map[i][j] == '@') sang = new Point(i, j); // 상근이 위치
					else if(map[i][j] == '*') fire.add(new Point(i, j)); // 불 위치
				}
			}
			long result = bfs(); //중간에 탈출하면 몇칸 움직였는지 리턴 해주며 값 저장
			System.out.println(result==-1?"IMPOSSIBLE":result);
		}
	}
	private static long bfs() {
		Queue<Point> Sque = new LinkedList<>();//상근이 위치 큐
		Sque.add(sang);
		visit[sang.i][sang.j] = true;
		Queue<Point> Fque = new LinkedList<>(); //불 위치 큐
		for(int i=0;i<fire.size();i++) {
			Fque.add(fire.get(i));
			visit[fire.get(i).i][fire.get(i).j] = true;
		}
		int cnt = 0;
		while(!Sque.isEmpty()) { //상근이 큐사이즈만 조건에 하면 됨
			int Fsize = Fque.size(); //불 먼저 이동하므로 불부터 시작
			for(int s=0;s<Fsize;s++) {
				Point now = Fque.poll();
				for(int d=0;d<4;d++) {
					int ni = now.i + di[d];
					int nj = now.j + dj[d];
					if(ni<0 || nj<0 || ni>=N || nj>=M || visit[ni][nj]) continue;
					if(map[ni][nj] == '#') continue;
					Fque.add(new Point(ni, nj));
					visit[ni][nj] = true;
				}
			}
			int Ssize = Sque.size(); // 불 한번 이동한 다음 상근이 한번 
            //불이동 -> 상근이 이동 -> 불이동 -> 상근이동 ~~~
			for(int s=0;s<Ssize;s++) {
				Point now = Sque.poll();
				if(now.i==0 || now.j==0 || now.i==N-1 || now.j==M-1) return cnt+1;
				for(int d=0;d<4;d++) {// 이동하다가 맵 테두리에 도착했으면 탈출 성공 리턴
					int ni = now.i + di[d];
					int nj = now.j + dj[d];
					if(ni<0 || nj<0 || ni>=N || nj>=M || visit[ni][nj]) continue;
					if(map[ni][nj] == '#') continue;
					Sque.add(new Point(ni, nj));
					visit[ni][nj] = true;
				}
			}
			cnt++;
		}
		return -1; // 다해도 맵 테두리 못갔으면 탈출 못함
	}
	static class Point{
		int i,j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
		
	}
}
