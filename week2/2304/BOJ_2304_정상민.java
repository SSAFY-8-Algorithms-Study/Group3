package baek2304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.Comparator;

public class Main {
		
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[][] map = new int[N][2]; 
		int mx=0,my=0,midx=0; // 최대높이의 x,y
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken());// 밑변 값
			map[i][1] = Integer.parseInt(st.nextToken());// 높이 값
		}
		Arrays.sort(map, new Comparator<int[]>() {//x값 오름차순 정렬
			public int compare(int[] o1, int[] o2) {
				if(o1[0] > o2[0]) return 1;
				else return -1;
			}
		});
		for(int i=0;i<N;i++) {
			if(map[i][1] >= my) {
				my = map[i][1];
				mx = map[i][0];
				midx = i;
			}
		} // 가장 높은 기둥중 제일 멀리 있는놈 찾기
		int startx=map[0][0]; int endx = map[N-1][0]; int idx = 1; int height = map[0][1];
		int sum = map[0][1];
		for(int i=startx+1;i<=mx;i++) { // 가장멀리있는 가장높은 기둥까지 합구하기
			if(i == map[idx][0]) {
				if(height <= map[idx][1]) height = map[idx][1];
				sum += height;
				idx++;
			}
			else sum += height;
		}
		if(midx == N-1) {
			System.out.println(sum);
			return;
		} // 만약 가장 높은 기둥이 마지막 기둥이면 현재까지 합 출력하고 종료
		sum += map[N-1][1]; height = map[N-1][1]; idx = N-2;
		for(int i=endx-1;i>mx;i--) {
			if(i == map[idx][0]) {
				if(height <= map[idx][1]) height = map[idx][1];
				sum += height;
				idx--;
			}
			else sum += height;
		}
		System.out.println(sum);
//	처음 접근법 -  시작부터 가장높은 지점까지는 ok 근데 가장높은 지점부터 마지막까지 구할때 마지막이 예시처럼 그런줄
//  끝까지 배열로 풀려고 해서 애먹었는데..
	}
}
