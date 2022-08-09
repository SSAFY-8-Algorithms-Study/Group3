package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//회의실 배정
public class BOJ_1931_최규림 {

	static class Room implements Comparable<Room> {
		int start, end;

		Room(int start, int end) {
			this.start = start;
			this.end = end;
		}

		// 우선순위 -> 끝나는 시간, 시작하는 시간 -> 빠른 순
		@Override
		public int compareTo(Room r) {
			if (this.end == r.end) {
				if (this.start > r.start) {
					return 1;
				} else if (this.start < r.start) {
					return -1;
				}
				return 0;
			} else {
				if (this.end > r.end) {
					return 1;
				} else if (this.end < r.end) {
					return -1;
				}
				return 0;
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		PriorityQueue<Room> pq = new PriorityQueue<>();

		int n = Integer.parseInt(br.readLine()); // 회의실 수
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			pq.add(new Room(s, e));
		}

		Room room = pq.poll();
		int endTime = room.end;
		int answer = 1; // 첫 번째 회의실

		while (!pq.isEmpty()) {
			Room target = pq.poll();
			if (endTime <= target.start) {
				answer++;
				endTime = target.end;
			}

		}
		System.out.println(answer);
	}

}
