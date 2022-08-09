package week2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BOJ2304 {

	public static class xy implements Comparable<xy>{
		private int x;
		private int y;
	
		public xy(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(xy other) {
			return this.x > other.x ? 1 : -1;
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		List<xy> map = new ArrayList<>();
		int centerMAXidx = 0, centerMAXy = 0;
		// 입력 받기
		for(int i=0;i<N;i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			map.add(new xy(a,b));
		}
		// 먼저 정렬을 한 수에 중심에 있는 값 구하기
		Collections.sort(map);
		
		for(int i=0;i<N;i++) {
			if (centerMAXy < map.get(i).y) {
				centerMAXidx = i;
				centerMAXy = map.get(i).y;
			}
		}
		// 중심과 같은 구간이 연속으로 여러개일 경우 구하기 어려워졌기 때문에 중심값을 하나 키워줌
		map.get(centerMAXidx).y++;
		
		// 가장 꼭대기는 한번 ++ 했으니깐 -1하고 계산
		// sum은 계산 결과, MAXx는 가장 왼쪽의 x값, MAXy는 그때의 y값
		int sum = map.get(centerMAXidx).y - 1, MAXx = map.get(0).x, MAXy = map.get(0).y; 
		for(int i = 0; i<=centerMAXidx;i++) {
			// 나보다 더 큰 구간을 발견하면 그때 계산하고 갱신하기
			if (MAXy < map.get(i).y) {
				sum = (map.get(i).x- MAXx) * (MAXy) + sum;
				MAXx = map.get(i).x;
				MAXy = map.get(i).y;
			}
		}
		// MAXx는 가장 오른쪽의 x값, MAXy는 그때의 y값
		MAXx = map.get(N-1).x; MAXy = map.get(N-1).y; 
		for(int i = N - 1; i >= centerMAXidx;i--) {
			if (MAXy < map.get(i).y) {
				sum = (MAXx - map.get(i).x) * (MAXy) + sum;
				MAXx = map.get(i).x;
				MAXy = map.get(i).y;
			}
		}
		System.out.println(sum);
	}
}
