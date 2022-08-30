package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2596_김동욱 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		String letter = br.readLine();
		int strange=0;
		String[] code = {"000000","001111","010011","011100","100110","101001","110101","111010"};
		for(int i=0;i<letter.length();i+=6) {
			String c = letter.substring(i,i+6);
			int count=0;
			boolean isLetter= false;
			for(int j=0;j<code.length;j++) {
				String alphabet = code[j];
				count=0;
				for(int k=0;k<6;k++) {
					if(count>1) {strange = i/6 +1;}
					if(c.charAt(k)!= alphabet.charAt(k)) {
						count++;
					}
				}
				if(count<=1) {sb.append((char)(j+65)); isLetter=true;}
			}
			if(!isLetter) {
				System.out.println(strange); return;
			}
		}
		System.out.println(sb);
	}

}
