package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 동전 바꿔주기
public class BOJ_2624_최규림 {

	static int T, k;

	static class Coin {
		int price, cnt;

		Coin(int price, int cnt) {
			this.price = price;
			this.cnt = cnt;
		}
	}

	static Coin[] coins;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());

		coins = new Coin[k];
		dp = new int[T + 1];

		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int price = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());
			coins[i] = new Coin(price, cnt);
		}

		dp[0] = 1;
		for (int i = 0; i < k; i++) {
			// money 값을 만들 수 있는 경우의 수 누적(i번째 동전으로 만들어 놓은 케이스의 경우의 수를 누적)
			for (int money = T; money >= coins[i].price; money--) {
				for (int cnt = 1; cnt <= coins[i].cnt; cnt++) {
					// i번째 동전을 cnt개 사용했을 때, money값 초과한 경우
					if (money - coins[i].price * cnt < 0) {
						break;
					}

					// i번째 동전 cnt개 사용하기 전의 경우의 수를 누적
					dp[money] += dp[money - coins[i].price * cnt];
				}
			}
		}
		System.out.println(dp[T]);
	}
}
