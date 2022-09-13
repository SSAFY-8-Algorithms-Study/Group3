package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 어드벤처 게임
public class BOJ_2310_최규림 {

	static int n;
	static boolean flag;
	static StringBuilder sb = new StringBuilder();

	static class Room {
		char alphabet;
		int price;
		List<Integer> list = new ArrayList<Integer>();
	}

	static Room[] rooms;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 재귀문제 같음
		while (true) {
			n = Integer.parseInt(br.readLine());
			if (n == 0) {
				break;
			}

			flag = false;
			rooms = new Room[n + 1];
			visited = new boolean[n + 1];

			for (int i = 1; i <= n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				rooms[i] = new Room();
				rooms[i].alphabet = st.nextToken().charAt(0);
				rooms[i].price = Integer.parseInt(st.nextToken());

				while (true) {
					int r = Integer.parseInt(st.nextToken());
					if (r == 0)
						break;

					rooms[i].list.add(r);
				}

			}

			dfs(1, 0);

			sb.append(flag ? "YES" : "NO").append('\n');
		}
		System.out.println(sb.toString());
	}

	static void dfs(int idx, int price) {
		Room target = rooms[idx];

		// 레프리콘
		if (target.alphabet == 'L') {
			price = Math.max(price, target.price);
		}
		// 트롤
		else if (target.alphabet == 'T') {
			price -= target.price;
			if (price < 0) {
				return;
			}
		}

		if (idx == n) {
			flag = true;
			return;
		}

		visited[idx] = true;
		for (int nxt : target.list) {
			if (visited[nxt]) {
				continue;
			}
			dfs(nxt, price);
		}
		visited[idx] = false;
	}
}