package week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//돌 게임
public class BOJ_9655_김지희 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		if(N%2==0) {
			sb.append("CY");
		}else sb.append("SK");
		
		System.out.println(sb);
	}
}
