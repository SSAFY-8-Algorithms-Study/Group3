package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3187_김아영 {

	static int R, C;
	static char map[][];
	static boolean visit[][];
	static int sheep, wolf;
	static int direct[][] = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	public static void bfs(int x, int y, int check) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		visit[x][y] = true;
		int sh =0;
		int wo =0;
		if (check == 1)wo++;
		else sh++;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int s = 0; s < size; s++) {
				Point now = q.poll();
				for(int d=0;d<4;d++) {
					int dx = now.x + direct[d][0];
					int dy = now.y + direct[d][1];
					if (dx <0 || dy<0 || dx>=R || dy>=C)continue;
					if (visit[dx][dy])continue;
					if (map[dx][dy] =='#')continue;
					if (map[dx][dy] =='v') { // 늑대
						wo++;
					}else if (map[dx][dy] =='k') {
						sh++;
					}
					q.add(new Point(dx, dy));
					visit[dx][dy] = true;
				}
			}
		}
		//System.out.println("time");
		if (wo >= sh) {
			wolf = wolf + wo;
		}else {
			sheep = sheep + sh;
		}
		
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visit = new boolean[R][C];
		
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (!visit[i][j] && map[i][j] == 'v') {
					bfs(i,j, 1);
				}else if (!visit[i][j] && map[i][j] == 'k') {
					bfs(i,j, 0);
				}
			}
		}
		System.out.println(sheep + " " + wolf);
	}

}
