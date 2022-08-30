package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2596_김아영 {

	static String apl[] = {"000000","001111","010011","011100","100110","101001","110101","111010"};
	static String result = "";
	
	static boolean check(int x, String str) {
		
		for(int j= 0;j<8;j++) {
			int flag = 0;
			for(int i= 0;i<6;i++) {
				if (apl[j].charAt(i) != str.charAt((x*6) + i)) flag++;
			}
			if (flag <= 1) {
				char a = (char) ('A' + j);
				result = result + a;
				return true;
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());

		String str = br.readLine();
		for(int i=0;i<N;i++) {
			if (!check (i, str)) {
				System.out.println(i + 1);
				return;
			}
		}
		System.out.println(result);
	}

}
