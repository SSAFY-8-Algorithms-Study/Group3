package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_17608_김동욱 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int pillars[] = new int[N];
		int count=1;
		for(int i=0;i<N;i++) {
			pillars[i]= Integer.parseInt(br.readLine());
		}
		int max = pillars[N-1];
		for(int i=N-1;i>=0;i--) {
			if(pillars[i]>max) {
				count++;
				max= pillars[i];
			}
		}
		System.out.println(count);
	}

}
