package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9655_김동욱 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		String answer = ((N % 2 == 0) ? "CY" : "SK"); // 홀수면 상근이 승, 짝수면 창영이 승
        System.out.println(answer);
		
	}

}
