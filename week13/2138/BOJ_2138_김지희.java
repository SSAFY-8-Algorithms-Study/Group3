package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//전구와 스위치;ㅣ
public class BOJ_2138_김지희 {
	
	static int N, answer;
	static char[] start, temp, goal;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		start = new char[N+1];
		temp = new char[N+1];
		goal = new char[N+1];
		
		start = br.readLine().toCharArray();
		temp = start.clone();
		goal = br.readLine().toCharArray();

		answer = Integer.MAX_VALUE;

		//첫번째 스위치 켰을 때 와...
		temp[0] = temp[0]=='0'? '1':'0';
		temp[1] = temp[1]=='0'? '1':'0';
		int cnt = 1;
		for(int i=1; i<N; i++) {
			//goal[i-1]이랑 temp[i-1]이 다를 때만 switch on
			if(temp[i-1] != goal[i-1]) {
				switchOn(i);
				cnt++;
			}
		}
		
		if(temp[N-1]==goal[N-1] && temp[N-2]==goal[N-2]) {
			answer = Math.min(answer, cnt);
		}
		
		temp = start.clone();
		cnt = 0;
		//첫번째 스위치 안 켰을 때
		for(int i=1; i<N; i++) {
			//goal[i-1]이랑 temp[i-1]이 다를 때만 switch
			if(temp[i-1] != goal[i-1]) {
				switchOn(i);
				cnt++;
			}
		}				

		if(temp[N-1]==goal[N-1] && temp[N-2]==goal[N-2]) {
			answer = Math.min(answer, cnt);
		}
		
		System.out.println(answer==Integer.MAX_VALUE? -1 : answer);
	}
	
	private static void switchOn(int idx) {
		for(int i=idx-1; i<=idx+1; i++) {
			if(i>=0 && i<N) temp[i] = temp[i]=='0'? '1':'0';
		}
	}
}
