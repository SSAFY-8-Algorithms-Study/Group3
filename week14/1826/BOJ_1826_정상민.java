package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1826연료채우기 {
	static int N, L, P, result;
	static PriorityQueue<point> que = new PriorityQueue<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		PriorityQueue<point> que = new PriorityQueue<>(); //주유소 큐
		PriorityQueue<Integer> fque = new PriorityQueue<>(Collections.reverseOrder()); //연료 큐
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int dis = Integer.parseInt(st.nextToken());
			int amount = Integer.parseInt(st.nextToken());
			que.add(new point(dis, amount));
		}
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		que.add(new point(L,P));
		///////////////////////////////입력
		int nowf = P;
		int nowd = 0;
		if (L <= P) {
			System.out.println(0);
			return;
		} //혹시 한번에 갈 수 있는 경우
		while(!que.isEmpty()) {
			if(que.peek().dis <= nowf + nowd) {
				////가까운 주유소로 갈수있으면 이동하고 아직 방문처리는 안함
				point now = que.poll();
				nowf -= (now.dis - nowd); // 연료양 갱신
				nowd = now.dis; // 현재위치 갱신
				fque.add(now.amount); //연료큐에 넣어놓기
			}
			else if(!fque.isEmpty()) {
				// 더이상 못갈 때 연료양 가장 많은곳 방문
				nowf += fque.poll();
				result++;
			}
			else { //갈 수 없다
				result = -1;
				break;
			}
		}
		System.out.println(result);

	}

	static class point implements Comparable<point> {
		int dis, amount;

		public point(int dis, int amount) {
			this.dis = dis;
			this.amount = amount;
		}

		@Override
		public String toString() {
			return "point [dis=" + dis + ", amount=" + amount + "]";
		}

		@Override
		public int compareTo(point o) { //가장 가까운놈
			return this.dis - o.dis;
		}
	}
}
