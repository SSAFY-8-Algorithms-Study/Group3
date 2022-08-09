package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2630_김동욱 {
	static int[][] paper;
	static int answer=0;
	static int answer1=0;
	public static boolean isSameColor(int sero,int garo, int size) { // partPaper에서 같은색상들인지 검사
		int temp = paper[sero][garo];
		//boolean flag = true;
		for(int i=sero;i<sero+size;i++) {
			for(int j=garo;j<garo+size;j++) {
				if(paper[i][j]!= temp)
				{ return false;}
			}
		}
		return true;
	}
	public static void checkColor(int sero,int garo, int size) {
		if(isSameColor(sero,garo,size)) {
		if(paper[sero][garo] ==0)  answer++; // White
		else answer1++; // Blue
		return;
		}
	
		// 같은 색상이 아니면 현재 size 다시 쪼개기
		int partPaper = size/2;
		
		checkColor(sero,garo,partPaper);
		checkColor(sero,garo+partPaper,partPaper);
		checkColor(sero+partPaper,garo,partPaper);
		checkColor(sero+partPaper,garo+partPaper,partPaper);

	}
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int N= Integer.parseInt(br.readLine());
		
		paper = new int[N][N];
		for(int i=0;i<N;i++) {
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int j=0;j<N;j++) {
			paper[i][j]= Integer.parseInt(st.nextToken());
		}
		} // end input
		
		checkColor(0,0,N);
		System.out.println(answer);
		System.out.println(answer1);
	}

}
