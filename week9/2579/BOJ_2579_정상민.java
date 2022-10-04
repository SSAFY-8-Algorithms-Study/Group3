package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2579계단오르기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] map = new int[N+1];
		int[] result = new int[N+1];
		for(int i=1;i<=N;i++) {
			map[i] = Integer.parseInt(br.readLine());
			if(i==1) result[1] = map[1];
			else if(i==2) result[2] = map[1]+map[2];
		}
		
		for(int i=3;i<=N;i++) {
			result[i] = Math.max(result[i-2], result[i-3]+map[i-1]) + map[i];
		}
		System.out.println(result[N]);
	}

}
