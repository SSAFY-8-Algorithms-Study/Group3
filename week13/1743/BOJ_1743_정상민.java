package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1743음식물피하기 {
	static int N,M,K,result;
	static int[][] map;
	static boolean[][] visit;
	static int[] di = {1,-1,0,0};
	static int[] dj = {0,0,1,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N+1][M+1];
		visit = new boolean[N+1][M+1];
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x][y]++; //음식물 있는 위치 1로 기록
		}
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=M;j++) {
				//방문 안했고 음식물 있는 곳이면 bfs 탐색
				if(!visit[i][j] && map[i][j]==1) bfs(i,j);
			}
		}
		System.out.println(result);
	}
	private static void bfs(int i,int j) {
		Queue<Integer> que = new LinkedList<>();
		que.add(i);
		que.add(j);
		visit[i][j] = true;
		int cnt = 1;
		while(!que.isEmpty()) {
			int nowi = que.poll();
			int nowj = que.poll();
			for(int d=0;d<4;d++) {
				int ni = nowi + di[d];
				int nj = nowj + dj[d];
				if(ni<1 || nj<1 || ni>N || nj>M || visit[ni][nj]) continue;
				if(map[ni][nj] == 0) continue;
				que.add(ni);
				que.add(nj); //bfs로 주변에 있는 음식물 모두 체크하면서
				visit[ni][nj] = true;
				cnt++;// cnt에 기록
			}
		}
		result = Math.max(result, cnt); //매 bfs방문바다 연속된 음식물 최댓값 갱신
	}
}