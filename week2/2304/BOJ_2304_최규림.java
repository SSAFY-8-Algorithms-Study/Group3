package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//창고 다각형
public class BOJ_2304_최규림 {

	// 기둥 정보 클래스
	static class Bar implements Comparable<Bar> {
		int idx, height; // 기둥의 위치와 높이

		Bar(int idx, int height) {
			this.idx = idx;
			this.height = height;
		}

		// 높이가 높은 순으로
		@Override
		public int compareTo(Bar bar) {
			if (this.height < bar.height) {
				return 1;
			} else if (this.height > bar.height) {
				return -1;
			}
			return 0;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Bar> pq = new PriorityQueue<>(); // 우선순위 큐(높이가 높은 순)
		int answer = 0;

		int n = Integer.parseInt(br.readLine()); // 기둥 개수

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int l = Integer.parseInt(st.nextToken()); // 왼쪽 면의 위치
			int h = Integer.parseInt(st.nextToken()); // 높이
			pq.add(new Bar(l, h));
		}

		Bar top = pq.poll();
		int topI = top.idx; // 최대 높이의 index
		int topH = top.height; // 최대 높이
		answer += topH; // 최대 높이 기둥은 해당 위치에서만 넓이 계산 -> 해당 높이만큼 지붕을 이어갈 수 없기 때문

		// 최대 높이 기둥 위치를 기준으로 좌우로 각각 최대 기둥 높이 갱신해가면서 계산
		int leftI = topI;
		int rightI = topI;
		while (!pq.isEmpty()) {
			Bar bar = pq.poll();
			int barI = bar.idx;
			int barH = bar.height;

			// 최대 높이 기둥 기준으로 왼쪽에 위치
			if (barI < topI) {
				// 왼쪽 기둥 중, 넓이 계산이 되지 않은 기둥이라면
				if (barI < leftI) {
					answer += (leftI - barI) * barH;
					leftI = barI;
				}
			}
			// 최대 높이 기둥 기준으로 오른쪽에 위치
			else {
				// 오른쪽 기둥 중, 넓이 계산이 되지 않은 기둥이라면
				if (barI > rightI) {
					answer += (barI - rightI) * barH;
					rightI = barI;
				}
			}
		}
		System.out.println(answer);

	}

}
