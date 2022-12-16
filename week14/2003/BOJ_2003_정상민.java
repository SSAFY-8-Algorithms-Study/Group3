package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2003수들의합{
	static int N,result;
	static long M;
	static int[] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Long.parseLong(st.nextToken());
		map = new int[N];
		st =  new StringTokenizer(br.readLine());
		
		for(int i=0;i<N;i++) { 
			map[i] = Integer.parseInt(st.nextToken());
		}
		//////////////////////////////////////////////////입력
		for(int i=0;i<N;i++) {
			long sum = map[i];
			if(sum == M) {
				result++;
				continue;
				//수열 값이 M이랑 같을 때 카운트
			}
			for(int j=i+1;j<N;j++) { // i 다음 수부터 합 체크
				sum += map[j];
				if(sum < M) continue; // 아직 M보다 작으면 다음 수 체크
				else if(sum == M){ //M 이랑 같으면 카운트 후 탈출
					result++;
					break;
				}
				else break; // M 보다 크면 탈출
				
			}
		}
		System.out.println(result);
	}
}
