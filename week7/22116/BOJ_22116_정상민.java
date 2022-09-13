import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	static int N, result = Integer.MAX_VALUE;
	static int min=Integer.MAX_VALUE,max=0;
	static int[][] map;
	static int[] di = {-1,+1,0,0};
	static int[] dj = {0,0,-1,+1};
	static boolean[][] visit;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] > max) max = map[i][j];
				if(map[i][j] < min) min = map[i][j];
			}
		}
		int L = 0;
		int R = max - min;
		while(L <= R) {
			int M = (L+R) / 2;
			visit = new boolean[N][N];
			if(bfs(M)) {
				R = M -1;
			}
			else {
				L = M + 1;
			}
		}
		System.out.println(L);
	}
	private static boolean bfs(int num) {
		Queue<Integer> que = new LinkedList<>();
		que.offer(0);
		que.offer(0);
		visit[0][0] = true;
		while(!que.isEmpty()) {
			int i = que.poll();
			int j = que.poll();
			if(i == N-1 && j == N-1) return true;
			for(int d=0;d<4;d++) {
				int ni = i + di[d];
				int nj = j + dj[d];
				if(ni<0 || nj<0 || ni>=N || nj>=N || visit[ni][nj]) continue;
				if(Math.abs(map[i][j] - map[ni][nj]) > num) continue;
				que.offer(ni);
				que.offer(nj);
				visit[ni][nj] = true;
			}
		}
		return false;
	}
}
