package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class BOJ_2290_김아영 {

	static int row, col, N;
	
	// 위에 지붕 있고 없고
	static void topyes() {
		System.out.print(" ");
		for(int i = 1; i <= row - 1; i++)System.out.print("-");
		System.out.print(" ");
	}
	static void topno() {
		for(int i = 0; i <= row; i++)System.out.print(" ");
	}
	// 왼쪽
	static void front() {
		System.out.print("|");
		for(int i = 1; i <= row; i++)System.out.print(" ");
	}
	// 양 옆
	static void mid() {
		System.out.print("|");
		for(int i = 1; i <= row - 1; i++)System.out.print(" ");
		System.out.print("|");
	}
	// 오른쪽
	static void end() {
		for(int i = 0; i <= row - 1; i++)System.out.print(" ");
		System.out.print("|");
	}
	
	static void one(int c) {	
		if (c == 0 || c == col / 2 || c == col) topno();
		else end();	
	}
	
	static void two(int c) {
		if (c == 0 || c == col / 2 || c == col) topyes();
		else if (c > 0 && c < col/2) end();
		else front();
	}
	
	static void three(int c) {
		if (c == 0 || c == col / 2 || c == col) topyes();
		else end();
	}
	
	static void four(int c) {
		if (c == 0 || c == col) topno();
		else if (c == col / 2) topyes();
		else if (c > 0 && c < col/2) mid();
		else end();
	}
	
	static void five(int c) {
		if (c == 0 || c == col / 2 || c == col) topyes();
		else if (c > 0 && c < col/2) front();
		else end();
	}
	
	static void six(int c) {
		if (c == 0 || c == col / 2 || c == col) topyes();
		else if (c > 0 && c < col/2) front();
		else mid();
	}
	
	static void seven(int c) {
		if (c == 0 ) topyes();
		else if ( c == col / 2 || c == col) topno();
		else end();	
	}
	
	static void eight(int c) {
		if (c == 0 || c == col / 2 || c == col) topyes();
		else mid();	
	}
	
	static void nine(int c) {
		if (c == 0 || c == col / 2 || c == col) topyes();
		else if (c > 0 && c < col/2) mid();
		else end();	
	}
	
	static void zero(int c) {
		if (c == 0|| c == col ) topyes();
		else if ( c == col / 2 ) topno();
		else mid();	
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		String s = st.nextToken();
		row = N + 1;
		col = 2*N + 2;
		// 초기 세팅
		for(int c = 0; c <= col; c++) {
			for(int num = 0; num < s.length(); num++) {
				if (s.charAt(num) == '1') {
					one(c);
				}
				if (s.charAt(num) == '2') {
					two(c);
				}
				if (s.charAt(num) == '3') {
					three(c);
				}
				if (s.charAt(num) == '4') {
					four(c);
				}
				if (s.charAt(num) == '5') {
					five(c);
				}
				if (s.charAt(num) == '6') {
					six(c);
				}
				if (s.charAt(num) == '7') {
					seven(c);
				}
				if (s.charAt(num) == '8') {
					eight(c);
				}
				if (s.charAt(num) == '9') {
					nine(c);
				}
				if (s.charAt(num) == '0') {
					zero(c);
				}
				System.out.print(" ") ;
			} // num
			System.out.println();
		} //col
		

	}
}
