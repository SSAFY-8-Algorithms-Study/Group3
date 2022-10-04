package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_18353 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] map = new int[N+1];
		int[] result = new int[N+1];
		int max = 0;
		for(int i=1;i<=N;i++) {
			map[i] = Integer.parseInt(st.nextToken());
			result[i] = 1;
		}
		
		for(int i=1;i<=N;i++) {
			for(int j=1;j<i;j++) {
				if(map[i] < map[j]) {
					result[i] = Math.max(result[i], result[j]+1);
				}
			}
		}//최대로 몇개로 내림차순 될 수있는지 
		
		for(int i=1;i<=N;i++) {
			max = Math.max(max,  result[i]);
		}
		System.out.println(N-max);
		
	}

}
