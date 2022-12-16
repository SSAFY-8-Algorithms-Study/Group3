package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_11060점프점프 {
	static int N;
	static int[] map,result;
	static boolean[] visit; 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N];
		result = new int[N];
		visit = new boolean[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<N;i++) {
			map[i] = Integer.parseInt(st.nextToken());
			result[i] = Integer.MAX_VALUE;
		}
		/////////////////////////// 입력
		result[0] = 0;
		visit[0] = true;
		
		for(int i=0;i<N;i++) {
			int jump = map[i];
			if(!visit[i]) continue; //방문 못하는 곳이면 패스
			for(int j=1;j<=jump;j++) {
				if(i+j>=N) break;
				result[i+j] = Math.min(result[i+j], result[i]+1); //최소 점프 횟수 기록
				visit[i+j] = true; //방문처리
			}
		}
		System.out.println(result[N-1]==Integer.MAX_VALUE?-1:result[N-1]);
		
	}

}
