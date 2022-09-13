package week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20363_김지희 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		int sun = Integer.parseInt(st.nextToken());
		int water = Integer.parseInt(st.nextToken());
		
		int answer = Math.min(solution(sun, water), solution(water, sun));
		
		System.out.println(answer);
	}
	
	private static int solution(int X, int Y) {
		int answer = X + Y + X/10;
		return answer;
	}

}
