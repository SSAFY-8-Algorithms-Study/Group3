package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_11403_김아영 {

	static int N;
	static int map[][];
	static ArrayList<Integer>[] list;
	static boolean v[];

	static boolean bfs(int s, int e) {

		Queue<Integer> q = new LinkedList<>();
		q.add(s);
		// v[s] = true;
		int flag = 0;
		
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int now = q.poll();
				if (now == e && flag == 1) {
					return true;
				}
				flag = 1;
				for (int idx : list[now]) {
					if (v[idx]) {
						continue;
					}
					q.add(idx);
					v[idx] = true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		list = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			list[i] = new ArrayList();
		}

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					list[i].add(j);
				}
			}
		}
		// 입력 끝

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				v = new boolean[N + 1];
				if (bfs(i, j))
					map[i][j] = 1;
				else
					map[i][j] = 0;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}

	}

}
