package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 연료 채우기
public class BOJ_1826_최규림 {

	static int N, L, P, ans;

	static class GasStation implements Comparable<GasStation> {
		int dist, gas;

		GasStation(int dist, int gas) {
			this.dist = dist;
			this.gas = gas;
		}

		@Override
		public int compareTo(GasStation o) {
			return this.dist - o.dist;
		}

	}

	static PriorityQueue<GasStation> pq = new PriorityQueue<>();
	static PriorityQueue<Integer> gasPq = new PriorityQueue<>(Collections.reverseOrder());

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			pq.add(new GasStation(a, b));
		}

		st = new StringTokenizer(br.readLine(), " ");
		L = Integer.parseInt(st.nextToken()); // 마을 위치
		P = Integer.parseInt(st.nextToken()); // 트럭 연료양

		while (P < L) { // 현재 연료양으로 마을 위치까지 가지 못하는 경우, 반복

			// 현재 연료양으로 갈 수 있는 모든 주유소 찾기
			while (!pq.isEmpty() && pq.peek().dist <= P) {
				gasPq.add(pq.poll().gas);
			}

			// 주유소 못찾은 경우 진행 불가
			if (gasPq.isEmpty()) {
				ans = -1;
				break;
			}

			// 주유소 방문 및 연료 충전
			ans++;
			P += gasPq.poll();			
		}
		System.out.println(ans);
	}
}
