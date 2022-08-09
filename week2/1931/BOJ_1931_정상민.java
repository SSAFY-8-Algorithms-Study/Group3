package baek1931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] time = new int[N][2];
		int cnt = 0;
		int prev_end_time = 0;
		StringTokenizer st;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<2;j++) {
				time[i][j] = Integer.parseInt(st.nextToken());
			}			
		}// 회의 시간 time 배열에 싹다 정리
		Arrays.sort(time, new Comparator<int[]>() {//회의 끝나는시간이 빠른 순으로 정렬
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] == o2[1]) {
					if(o1[0]>o2[0]) return 1;
					else if(o1[0] < o2[0]) return -1;
				}// 만약 끝나는 시간 같으면 시작시간으로 정렬
				else {
					if(o1[1] > o2[1]) return 1;
					else return -1;
				}
				return 0;
			}
		}); 
		for(int i=0;i<N;i++) {
			if(prev_end_time <= time[i][0]) {
				prev_end_time = time[i][1];
				cnt++;
			}
		}
		System.out.println(cnt);
	}//이문제는 결국 끝나는시간 정렬 후 시작시간이 짧은 놈우선으로 하면 된다는 그리디

}
