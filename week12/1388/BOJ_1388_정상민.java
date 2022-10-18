import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,M,result;
	static int[] di = {1,-1,0,0};
	static int[] dj = {0,0,1,-1};
	static char[][] map;
	static boolean[][] visit;
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
				if(!visit[i][j]) { //
					bfs(i,j); //방문 안한 모든점에 대해서 bfs 돌리면서 이어져 있으면 visit체크
					result++;
				}
			}
		}
		System.out.println(result);
	}
	private static void bfs(int si, int sj) {
		Queue<Integer> que = new LinkedList<>();
		char type = map[si][sj]; //해당 위치 문자 type에 저장
		que.add(si);
		que.add(sj);
		visit[si][sj] = true;
		while(!que.isEmpty()) {
			int i = que.poll();
			int j = que.poll();
			for(int d=0;d<4;d++) {
				int ni = i + di[d];
				int nj = j + dj[d];
				if(ni<0 || nj<0 || ni>=N || nj>=M || visit[ni][nj]) continue;
				if(type == '-') {
					if(d == 0 || d == 1) continue; // 이모양일땐 좌우만 확인하면서 
					if(map[ni][nj] == '-') { // 모양 같은지 확인
						que.add(ni);
						que.add(nj);
						visit[ni][nj] = true;
					}
				}
				else if(type == '|') {
					if(d == 2 || d == 3) continue; //이 모양일땐 상하만 확인하면서
					if(map[ni][nj] == '|') { //모양 같으면 que add하고 visit체크
						que.add(ni);
						que.add(nj);
						visit[ni][nj] = true;
					}
				}
			}
		}
		
	}
}
