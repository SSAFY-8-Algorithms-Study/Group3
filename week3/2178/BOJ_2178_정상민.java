package baek2178_미로탐색;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {
	static char[][] map;
	static boolean[][] visited;
	static int[] di = {1,-1,0,0};
	static int[] dj = {0,0,1,-1};
	static int cnt;
	static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cnt = N * M; //
		map = new char[N][M];
		visited = new boolean[N][M];
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			map[i] = str.toCharArray();
		}
		bfs(0,0,1);
		System.out.println(cnt);
	}
	public static void bfs(int i,int j, int len) { //len은 해당위치까지 시작점에서부터 최솟값
		Queue<Integer> que = new LinkedList<Integer>();
		que.add(i);
		que.add(j);
		que.add(len);
		visited[i][j] = true;
		while(!que.isEmpty()) {
			int x = que.poll();
			int y = que.poll();
			int num = que.poll();
			if(x == N-1 && y == M-1) { //목적지에 도착했다면 최소값 갱신
				if(num < cnt) cnt = num;
				continue;
			}
			for(int d=0;d<4;d++) { //4방탐색하면서 배열범위, 경로 탐색
				int nx = x + di[d];
				int ny = y + dj[d];
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if(map[nx][ny] == '1' && visited[nx][ny] == false) {
					visited[nx][ny] = true;
					que.add(nx);
					que.add(ny);
					que.add(num+1); //길 찾았으면 len값 +1해서 전달
				}				
			}
		}
		
	}
}
