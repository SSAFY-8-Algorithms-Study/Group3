package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1826_김동욱 {
	public static class Station implements Comparable<Station> {
		int dist;
		int gas;

		public Station(int dist, int gas) {
			this.dist = dist;
			this.gas = gas;
		}

		@Override
		public int compareTo(Station o) {
			return this.dist - o.dist;
		}

		@Override
		public String toString() {
			return "Station [dist=" + dist + ", gas=" + gas + "]";
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int answer=0;
		PriorityQueue<Station> distPq = new PriorityQueue<>();
		PriorityQueue<Integer> gasPq = new PriorityQueue<>(Collections.reverseOrder());
		for (int i = 0; i < N; i++) {
			st= new StringTokenizer(br.readLine());
			distPq.add(new Station(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
			
		}
		st= new StringTokenizer(br.readLine());
		int totalDistance = Integer.parseInt(st.nextToken());
		distPq.add(new Station(totalDistance,0)); // 종점 추가
		int gas = Integer.parseInt(st.nextToken());
		// end input
		
		int distance = 0;
		while(!distPq.isEmpty()) {
			if(distPq.peek().dist <= gas+distance) { // 이동
				Station tempStation = distPq.poll();
				gas -= tempStation.dist-distance; // 주유소 위치에서 현재 위치 만큼 빼면 이동거리 , 기름 만큼 빼줌
				distance= tempStation.dist;
				gasPq.add(tempStation.gas);
			}
			else if(!gasPq.isEmpty()) { // 주유
				gas+= gasPq.poll();
				answer++;
			}
			else { // 도달하지 못 한다면
				answer=-1;
				break;
			}
		}
		System.out.println(answer);
	}

}
