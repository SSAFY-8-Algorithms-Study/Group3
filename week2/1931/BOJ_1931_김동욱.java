package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1931_김동욱 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] schedules = new int[N][2];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			schedules[i][0] = Integer.parseInt(st.nextToken());
			schedules[i][1] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(schedules,(o1,o2)->{
			if(o1[1]==o2[1]) {
				return Integer.compare(o1[0], o2[0]);
			}
			else
				return Integer.compare(o1[1], o2[1]);
		});
		//int start = schedules[0][0];
		int end = schedules[0][1];
		int count=1;
		for(int i=1;i<N;i++) {
			if(schedules[i][0]>= end) {
				count++;
				end =schedules[i][1];
			}
		}
		System.out.println(count);
	}


}
