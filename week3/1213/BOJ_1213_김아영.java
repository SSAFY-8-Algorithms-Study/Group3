package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_1213_김아영 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 65 ~ 90
		int map[] = new int[200];
		String st = sc.nextLine();
		for(int i=0;i<st.length();i++) {
			map[st.charAt(i)]++;
		}
		String s = "";
		int f = 0;
		char flag = '0';
		for(int i=65; i<=90; i++) {
			for(int j=0;j<map[i]/2;j++) s = s + (char)i;	
			if (map[i] % 2 == 1)  {flag = (char)i; f++;}
		}
		if (f == 1) {
			s = s + flag;
		}
		else if (f > 1) {
			System.out.println("I'm Sorry Hansoo");
			return;
		}
		
		for(int i=90; i>=65; i--) {
			for(int j=0;j<map[i]/2;j++) s = s + (char)i;
		}
		System.out.println(s);
	}

}

