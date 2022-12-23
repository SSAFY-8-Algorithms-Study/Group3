package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 걷기
public class BOJ_1459_김지희 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long X = Integer.parseInt(st.nextToken());
		long Y = Integer.parseInt(st.nextToken());
		long W = Integer.parseInt(st.nextToken());
		long S = Integer.parseInt(st.nextToken());
		
		long max = Math.max(X, Y);
		long min = Math.min(X, Y);
		
		//1. 평행이동
		long m1 = (X+Y) * W;
		
		//2. 대각선
		long m2 = 0;
		if((X+Y)%2==0) {
			m2 = max * S; 
		}else {
			m2 = (max-1) * S + W;
		}

		//3. 대각선 + 평행
		long m3 = min*S + Math.abs(X-Y) * W;
		
		System.out.println(Math.min(m1, (Math.min(m2, m3))));
	}
}
