package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

// 회장뽑기
public class BOJ_2660_최규림 {

	static int N, answer;

	static class Member {
		int cnt;
		List<Integer> list = new ArrayList<>();
	}

	static Member[] members;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		members = new Member[N + 1];
		for (int i = 0; i <= N; i++) {
			members[i] = new Member();
		}

		while (true) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (a == -1 && b == -1) {
				break;
			}

			members[a].list.add(b);
			members[b].list.add(a);
		}

		answer = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			bfs(i);
		}

		List<Integer> temp = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			if (members[i].cnt == answer) {
				temp.add(i);
			}
		}

		System.out.println(answer + " " + temp.size());
		for (int t : temp) {
			System.out.print(t + " ");
		}
	}

	static void bfs(int idx) {
		Set<Integer> set = new HashSet<>();
		set.add(idx);

		Queue<Integer> q = new ArrayDeque<Integer>();
		q.add(idx);

		boolean visited[] = new boolean[N + 1];
		visited[idx] = true;

		int time = 0;
		while (!q.isEmpty()) {
			int loop = q.size();
			while (loop-- != 0) {
				int now = q.poll();
				for (int i = 0; i < members[now].list.size(); i++) {
					int target = members[now].list.get(i);

					if (visited[target])
						continue;
					visited[target] = true;
					q.add(target);
					set.add(target);
				}
			}
			time += 1;

			if (set.size() == N) {
				members[idx].cnt = time;
				answer = Math.min(answer, time);
				break;
			}
		}
	}
}
