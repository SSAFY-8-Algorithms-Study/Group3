package baek2960_에라토스테네스체;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int P=0, cnt = 0, num = 0;
		int[] arr = new int[N+1];
		for(int i=0;i<N+1;i++) arr[i] = i;
		while(true) {
			if(cnt == K) break;
			for(int i = 2;i < N+1 ; i++) {
				if(arr[i] != 0) {
					P = arr[i]; //아직 지우지 않은 수 중 가장 작은 값 P
					break;
				}
			}
			for(int i = P;i<N+1;i+=P) { //P부터 P의 배수 다 지움
				if(arr[i] != 0) {
					num = arr[i];
					arr[i] = 0;
					cnt++; // 카운트하면서  K번째 찾기
				}
				if(cnt == K) break; //K번째 왔으면 탈출해서 num 출력
			}
		}
		System.out.println(num);
	}

}
