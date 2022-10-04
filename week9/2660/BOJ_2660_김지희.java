package week09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2660_김지희 {

	static int N;
	static int[][] dis;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		dis = new int[N+1][N+1];
		
		for(int i=0; i<=N; i++) {
			for(int j=0; j<=N; j++) {
				if(i != j) {
					dis[i][j] = 987654321;
				}
			}
		} // map[i][i] 는 0
		
		while(true) {
			String str = br.readLine();
			if(str.equals("-1 -1")) break;
			st = new StringTokenizer(str);
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			dis[a][b] = 1;
			dis[b][a] = 1;
		}
		
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					dis[i][j] = Math.min(dis[i][j], dis[i][k]+dis[k][j]);
				}
			}
		}
		
//		for(int k=1; k<=N; k++) {
//			for(int i=1; i<=N; i++) {
//				System.out.print(dis[k][i] + " ");
//			}System.out.println();
//		}
		
		int answer = Integer.MAX_VALUE;
		int[] result = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				result[i] = Math.max(result[i], dis[i][j]);
			}
			answer = Math.min(answer, result[i]);
		}
		StringBuilder sb = new StringBuilder();
		
		int cnt = 0;
		for(int i=1; i<=N; i++) {
			if(answer==result[i]) {
				sb.append(i + " ");
				cnt++;
			}
		}
		
		System.out.println(answer + " " + cnt);
		System.out.println(sb.toString());
		
		
	}
}
