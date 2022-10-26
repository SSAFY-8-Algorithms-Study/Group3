package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2607_김동욱 {
	static int N;
	static int[][] visitedArray;
	static int[] wordLength;
	static int originCount;
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		//0 번째하고 계속 비교하면 되겠다
		visitedArray = new int[N][26];
		wordLength = new int[N];
		originCount=0;
		int answer=0;
		for(int i=0;i<N;i++) {
			String temp =br.readLine();
			wordLength[i] = temp.length();
			for(int j=0;j<temp.length();j++) {
				visitedArray[i][Integer.parseInt((temp.charAt(j)-'A')+"")]++;
			}
		} // end input
		
		for(int i=1;i<N;i++) { // 문자열 비교 ( 비교하는 문자열 기준으로 더하고 , 빼주고 replace해주고 origin하고 비교)
			if(wordLength[0]<wordLength[i] || wordLength[0]>wordLength[i]) { // 더하거나 빼기 차가 1 
				int diff=0;
				for(int j=0;j<26;j++) {
					diff+= Math.abs(visitedArray[0][j]- visitedArray[i][j]);
				}
				if(diff==1) answer++;
			}
			else if(wordLength[0]== wordLength[i]) {
				int diff=0;
				for(int j=0;j<26;j++) {
					diff+= Math.abs(visitedArray[0][j]- visitedArray[i][j]);
				}
				if(diff==2 || diff==0) answer++;
			}
		}
		System.out.println(answer);
	}
	
}
