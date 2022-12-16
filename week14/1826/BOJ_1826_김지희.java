package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//연료채우기
public class BOJ_1826_김지희 {
	static class Station implements Comparable<Station>{
		int dist, gas;
		
		Station(int dist, int gas){
			this.dist = dist;
			this.gas = gas;
		}

		@Override
		public int compareTo(Station o) {
			// TODO Auto-generated method stub
			return this.dist - o.dist;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int answer = 0;
		int N = Integer.parseInt(br.readLine()); // 주유소 개수
		PriorityQueue<Station> inputs = new PriorityQueue<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			inputs.add(new Station(d, g));
		} // 주유소까지 거리 기준, 오름차순 정렬 -> 가까운 주유소부터 체크하는데
		
		st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken()); // L km
		int P = Integer.parseInt(st.nextToken()); // P liter

		PriorityQueue<Integer> pQ = new PriorityQueue<>(Collections.reverseOrder());
		// 연료 내림차순 정렬, 갈 수 있는 주유소 중에 연료 많이 주는 곳부터 볼거야
		while(P < L) { // 연료(갈 수 있는 거리) < 마을까지 거리
			while(!inputs.isEmpty() && P>= inputs.peek().dist) {
				Station cur = inputs.poll();
				pQ.add(cur.gas);
			}
			
			if(pQ.isEmpty()) { // 갈 수 있는 주유소가 없음 . 근데 목표 거리는 아직 도달을 못했지 while문에 있으니까
				answer = -1;
				break;
			}
			P+= pQ.poll(); // 연료 충전
			answer++;
		}
		System.out.println(answer);
		
	}
}
