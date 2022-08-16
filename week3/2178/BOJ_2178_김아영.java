import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int map[][];
	static boolean visit[][];
	static int direct[][]= {{0,1},{0,-1},{1,0},{-1,0}};
	static int MIN;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		
		for(int i=0;i<N;i++) {
			String s = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		
		bfs(0,0);
		System.out.println(MIN);
	}
	
	static class Point{
		int i, j, cnt;
		Point(int i, int j, int cnt){
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}
	}
	
	static void bfs(int i, int j) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(i, j, 0));
		visit[i][j] = true;
		
		while (!queue.isEmpty()) {
			Point now = queue.poll();		
			if (now.i == N - 1 && now.j == M - 1) {
				MIN = now.cnt + 1;
				break;
			}
			
			for(int d=0;d<4;d++) {
	            int dx = now.i + direct[d][0];
	            int dy = now.j + direct[d][1];
	            if (dx <0 || dy <0 || dx >=N ||dy >= M)continue;
	            if (map[dx][dy] == 1 && !visit[dx][dy]) {
	            	visit[dx][dy] = true;
		            queue.add(new Point(dx, dy, now.cnt + 1));
				}
	        }	
		}	
	}
}
