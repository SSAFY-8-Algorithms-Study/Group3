package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//운동
public class BOJ_1956_김지희 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		int INF = 987654321;
		
		int[][] map = new int[V + 1][V + 1];
		for (int i = 0; i <= V; i++) {
			for (int j = 0; j <= V; j++) {
				if (i != j) {
					map[i][j] = INF;
				}
			}
		}

		for (int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			map[a][b] = w;
		}
		// input end

		for (int k = 1; k <= V; k++) {
			for (int i = 1; i <= V; i++) {
				for (int j = 1; j <= V; j++) {
					if (i == j)
						continue;
					if (map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}

		int answer = INF;
		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				if (i == j)
					continue;

				if (map[i][j] != INF && map[j][i] != INF)
					answer = Math.min(answer, map[i][j] + map[j][i]);
			}
		}

		answer = (answer == INF) ? -1 : answer;
		
		System.out.println(answer);

	}
}
