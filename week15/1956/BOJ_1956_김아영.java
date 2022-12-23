package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1956_김아영 {

	static int V, E;
	static int arr[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		arr = new int[V + 1][V + 1];
		int INF = 5000000;

		for (int i = 0; i <= V; i++) {
			Arrays.fill(arr[i], INF);
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			arr[a][b] = Math.min(arr[a][b], c);
		}

		for (int k = 1; k <= V; k++) {
			for (int i = 1; i <= V; i++) {
				for (int j = 1; j <= V; j++) {
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
				}
			}
		}

		int ans = INF;
		for (int i = 1; i <= V; i++) {
			for (int j = i + 1; j <= V; j++) {
				if (arr[i][j] == INF || arr[j][i] == INF)
					continue;
				ans = Math.min(ans, arr[i][j] + arr[j][i]);
			}
		}

		if (ans == INF)
			System.out.println(-1);
		else
			System.out.println(ans);

	}

}
