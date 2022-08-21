package baek2290_LCDtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static String[] strarr;
	static int s; 
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		String str = st.nextToken();
		strarr = new String[2*s + 3];//s값에 따라 배열 높이 설정
		for(int i=0;i<strarr.length;i++) {
			strarr[i] = "";
		} //숫자 사이에 공백추가, 
		for(int i=0;i<str.length();i++) {
			char c = str.charAt(i);
			switch(c) {
			case '1': draw1(); break;
			case '2': draw2(); break;
			case '3': draw3(); break;
			case '4': draw4(); break;
			case '5': draw5(); break;
			case '6': draw6(); break;
			case '7': draw7(); break;
			case '8': draw8(); break;
			case '9': draw9(); break;
			case '0': draw0(); break;
			}//숫자 별로 메서드 생성, 숫자마다 문자열에 추가해서 한번에 저장
		}
		for(int i=0;i<strarr.length;i++) {
			System.out.println(strarr[i]); //한번에 출력
		}
	}
	private static void draw1() {
		for(int i=0;i<strarr.length;i++) {
			if(i==0 || i==s+1 || i==2*s+2) {
				for(int j=0;j<s+2;j++) strarr[i] += " ";
			}
			else {
				for(int j=0;j<s+1;j++) strarr[i] += " ";
				strarr[i] += "|";
			}
			strarr[i] += " ";
		}
	}
	private static void draw2() {
		for(int i=0;i<strarr.length;i++) {
			if(i==0 || i==s+1 || i==2*s+2) {
				strarr[i] += " ";
				for(int j=0;j<s;j++) strarr[i] += "-";
				strarr[i] += " ";

			}
			else if(i >= 1 && i<= s) {
				for(int j=0;j<s+1;j++) strarr[i] += " ";
				strarr[i] += "|";
			}
			else {
				strarr[i] += "|";
				for(int j=0;j<s+1;j++) strarr[i] += " ";
			}
			strarr[i] += " ";
		}
	}
	private static void draw3() {
		for(int i=0;i<strarr.length;i++) {
			if(i==0 || i==s+1 || i==2*s+2) {
				strarr[i] += " ";
				for(int j=0;j<s;j++) strarr[i] += "-";
				strarr[i] += " ";

			}
			else{
				for(int j=0;j<s+1;j++) strarr[i] += " ";
				strarr[i] += "|";
			}
			strarr[i] += " ";
		}
	}
	private static void draw4() {
		for(int i=0;i<strarr.length;i++) {
			if(i==0 || i==2*s+2) {
				for(int j=0;j<s+2;j++) strarr[i] += " ";
			}
			else if(i == s+1) {
				strarr[i] += " ";
				for(int j=0;j<s;j++) strarr[i] += "-";
				strarr[i] += " ";
			}
			else if(i >= 1 && i<= s) {
				strarr[i] += "|";
				for(int j=0;j<s;j++) strarr[i] += " ";
				strarr[i] += "|";
			}
			else {
				for(int j=0;j<s+1;j++) strarr[i] += " ";
				strarr[i] += "|";
			}
			strarr[i] += " ";
		}
	}
	private static void draw5() {
		for(int i=0;i<strarr.length;i++) {
			if(i==0 || i==s+1 || i==2*s+2) {
				strarr[i] += " ";
				for(int j=0;j<s;j++) strarr[i] += "-";
				strarr[i] += " ";

			}
			else if(i >= 1 && i<= s) {
				strarr[i] += "|";
				for(int j=0;j<s+1;j++) strarr[i] += " ";

			}
			else {
				for(int j=0;j<s+1;j++) strarr[i] += " ";
				strarr[i] += "|";
			}
			strarr[i] += " ";
		}
	}
	private static void draw6() {
		for(int i=0;i<strarr.length;i++) {
			if(i==0 || i==s+1 || i==2*s+2) {
				strarr[i] += " ";
				for(int j=0;j<s;j++) strarr[i] += "-";
				strarr[i] += " ";

			}
			else if(i >= 1 && i<= s) {
				strarr[i] += "|";
				for(int j=0;j<s+1;j++) strarr[i] += " ";

			}
			else {
				strarr[i] += "|";
				for(int j=0;j<s;j++) strarr[i] += " ";
				strarr[i] += "|";
			}
			strarr[i] += " ";
		}
	}
	private static void draw7() {
		for(int i=0;i<strarr.length;i++) {
			if(i==0) {
				strarr[i] += " ";
				for(int j=0;j<s;j++) strarr[i] += "-";
				strarr[i] += " ";

			}
			else if(i==s+1 || i==2*s+2) {
				for(int j=0;j<s+2;j++) strarr[i] += " ";
			}
			else {
				for(int j=0;j<s+1;j++) strarr[i] += " ";
				strarr[i] += "|";
			}
			strarr[i] += " ";
		}
	}
	private static void draw8() {
		for(int i=0;i<strarr.length;i++) {
			if(i==0 || i==s+1 || i==2*s+2) {
				strarr[i] += " ";
				for(int j=0;j<s;j++) strarr[i] += "-";
				strarr[i] += " ";

			}
			else {
				strarr[i] += "|";
				for(int j=0;j<s;j++) strarr[i] += " ";
				strarr[i] += "|";
			}
			strarr[i] += " ";
		}
	}
	private static void draw9() {
		for(int i=0;i<strarr.length;i++) {
			if(i==0 || i==s+1 || i==2*s+2) {
				strarr[i] += " ";
				for(int j=0;j<s;j++) strarr[i] += "-";
				strarr[i] += " ";

			}
			else if(i >= 1 && i<= s) {
				strarr[i] += "|";
				for(int j=0;j<s;j++) strarr[i] += " ";
				strarr[i] += "|";
			}
			else {
				for(int j=0;j<s+1;j++) strarr[i] += " ";
				strarr[i] += "|";
			}
			strarr[i] += " ";
		}
	}
	private static void draw0() {
		for(int i=0;i<strarr.length;i++) {
			if(i==0 || i==2*s+2) {
				strarr[i] += " ";
				for(int j=0;j<s;j++) strarr[i] += "-";
				strarr[i] += " ";

			}
			else if(i == s+1) {
				for(int j=0;j<s+2;j++) strarr[i] += " ";
			}
			else {
				strarr[i] += "|";
				for(int j=0;j<s;j++) strarr[i] += " ";
				strarr[i] += "|";
			}
			strarr[i] += " ";
		}
	}
}
