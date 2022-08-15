package boj;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_1213_김동욱 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int alphabet[] = new int[26];
		String st = sc.nextLine();
		char answer[] = new char[st.length()];
		int start=0;
		int end=st.length()-1;
		int odd = 0;
		int ind=0;
		boolean isEven =true;
		for (int i = 0; i < st.length(); i++) {
			alphabet[Character.getNumericValue(st.charAt(i)) - 10]++; // 해당 alphabet index++
		} // end input

		if (st.length() % 2 == 0) { // 짝수개일 경우
			for (int i = 0; i < alphabet.length; i++) {
				if (alphabet[i] % 2 == 1) {
					System.out.println("I'm Sorry Hansoo");
					return;
				}
			}
		}
		if (st.length() % 2 == 1) { // 짝수일 경우
			for (int i = 0; i < alphabet.length; i++) {
				if (alphabet[i] % 2 == 1) {
					odd++; // 알파벳 갯수가 홀수개인것 카운트
				}
			}
			if (odd > 1) { // 알파벳 갯수가 홀수것인게 1개 이상이면
				System.out.println("I'm Sorry Hansoo");
				return;
			}
			isEven=false;
		}
	if(isEven) {
		for(int i = 0; i < alphabet.length; i++) {
			if(alphabet[i]!=0) {
				ind=i; // Get Odd ind
				while(alphabet[i]!=0) {
					answer[start++] = (char)(ind+65);
					answer[end--]= (char)(ind+65);
					alphabet[ind]-=2;
				}
			}
		}
		
	}
	if(!isEven) {
		for(int i = 0; i < alphabet.length; i++) {
			if(alphabet[i]%2==1) {
				odd=i;
				while(alphabet[i]>1) {
					answer[start++] = (char)(i+65);
					answer[end--]= (char)(i+65);
					alphabet[i]-=2;
				}
			}
			if(alphabet[i]%2==0) {
				ind=i;
				while(alphabet[ind]!=0) {
					answer[start++] = (char)(ind+65);
					answer[end--]= (char)(ind+65);
					alphabet[ind]-=2;
				}
			}
		}
		answer[start]=(char)(odd+65);
	}
	for(int i=0;i<answer.length;i++) {
		System.out.print(answer[i]);
	}
	}

}
