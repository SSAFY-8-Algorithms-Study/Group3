import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,M,wresult,sresult;
	static char[][] map;
	static boolean[][] visit;
	static int[] di = {1,-1,0,0};
	static int[] dj = {0,0,1,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visit = new boolean[N][M];
		
		for(int i=0;i<N;i++) {
			map[i] = br.readLine().toCharArray();
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j] != '#' && !visit[i][j]) {
					bfs(i,j);
				}
			}
		}
		System.out.println(sresult+" "+wresult);
	}
	private static void bfs(int si,int sj) {
		Queue<Integer> que = new LinkedList<>();
		int wcnt=0,scnt=0;
		que.add(si);
		que.add(sj);
		visit[si][sj] = true;
		if(map[si][sj] == 'v') wcnt++;
		else if(map[si][sj] == 'k') scnt++;
		while(!que.isEmpty()) {
			int i = que.poll();
			int j = que.poll();
			for(int d=0;d<4;d++) {
				int ni = i + di[d];
				int nj = j + dj[d];
				if(ni<0 || nj<0 || ni>=N || nj>=M) continue;
				if(map[ni][nj] == '#' || visit[ni][nj]) continue;
				
				if(map[ni][nj] == 'v') wcnt++;
				else if(map[ni][nj] == 'k') scnt++;
				
				que.add(ni);
				que.add(nj);
				visit[ni][nj] = true;
			}
		}
		if(scnt > wcnt) sresult += scnt;
		else wresult += wcnt;
	}

}
