package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

// 승부 예측
public class BOJ_15997_최규림 {

	static class Team implements Comparable<Team> {
		int idx, score;
		String name;
		double prob;

		Team(int idx, int score, String name) {
			this.idx = idx;
			this.score = score;
			this.name = name;
		}

		@Override
		public int compareTo(Team o) {
			return o.score - this.score;
		}
	}

	static class Game {
		String team1, team2;
		double w, d, l;

		Game(String t1, String t2, double w, double d, double l) {
			this.team1 = t1;
			this.team2 = t2;
			this.w = w;
			this.d = d;
			this.l = l;
		}
	}

	static Team[] teams = new Team[4];
	static Game[] games = new Game[6];
	static HashMap<String, Integer> map = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < 4; i++) {
			String name = st.nextToken();
			teams[i] = new Team(i, 0, name);
			map.put(name, i);
		}

		for (int i = 0; i < 6; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			String t1 = st.nextToken();
			String t2 = st.nextToken();
			double w = Double.parseDouble(st.nextToken());
			double d = Double.parseDouble(st.nextToken());
			double l = Double.parseDouble(st.nextToken());
			games[i] = new Game(t1, t2, w, d, l);
		}

		Team[] answer = teams.clone();
		dfs(0, 1);
		for (int i = 0; i < 4; i++) {
			Team t = answer[i];
			System.out.println(teams[t.idx].prob);
		}
	}

	static void dfs(int idx, double prob) {
		if (idx == 6) {			
//			System.out.println("경기 종료");
			// 점수별로 정렬 -> 승점 순위
			Team[] answer = teams.clone();
			
			Arrays.sort(answer);
			Team t1 = answer[0];
			Team t2 = answer[1];
			Team t3 = answer[2];
			Team t4 = answer[3];
//			System.out.println(t1.score);
//			System.out.println(t2.score);
//			System.out.println(t3.score);
//			System.out.println(t4.score);
//			System.out.println(prob);

			// 4팀 전부 동률
			if (t1.score == t2.score && t2.score == t3.score && t3.score == t4.score) {
//				System.out.println("4team");
				t1.prob += prob / 2;
				t2.prob += prob / 2;
				t3.prob += prob / 2;
				t4.prob += prob / 2;
			}
			// 1등 3팀
			else if (t1.score == t2.score && t2.score == t3.score) {
				t1.prob += prob * 2 / 3;
				t2.prob += prob * 2 / 3;
				t3.prob += prob * 2 / 3;
			}
			// 1등 2팀
			else if (t1.score == t2.score) {
				t1.prob += prob;
				t2.prob += prob;
			}
			// 1등 1팀 -> 2등 3팀
			else if (t2.score == t3.score && t3.score == t4.score) {
//				System.out.println("here");
				t1.prob += prob;
				t2.prob += prob / 3;
				t3.prob += prob / 3;
				t4.prob += prob / 3;
			}
			// 1등 1팀 -> 2등 2팀
			else if (t2.score == t3.score) {
				t1.prob += prob;
				t2.prob += prob / 2;
				t3.prob += prob / 2;
			}
			// 1등 1팀 -> 2등 1팀
			else {
				t1.prob += prob;
				t2.prob += prob;
			}

			return;
		}

		// idx 번째 경기 데이터
		// t1 vs t2
		Game game = games[idx];
		Team t1 = teams[map.get(game.team1)];
		Team t2 = teams[map.get(game.team2)];

		// t1 이긴 경우
//		System.out.println("t1 win");
		t1.score += 3;
		dfs(idx + 1, prob * game.w);
		t1.score -= 3;
		// t1 비긴 경우
//		System.out.println("draw");
		t1.score += 1;
		t2.score += 1;
		dfs(idx + 1, prob * game.d);
		t1.score -= 1;
		t2.score -= 1;
		// t1 진 경우
//		System.out.println("t1 lose");
		t2.score += 3;		
		dfs(idx + 1, prob * game.l);
		t2.score -= 3;
	}
}
