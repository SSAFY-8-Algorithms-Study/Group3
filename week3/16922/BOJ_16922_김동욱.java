package boj;

import java.util.HashSet;
import java.util.Scanner;

public class BOJ_16922_김동욱 {
	static int[] romeNum = {1,5,10,50};
	static int[] selected;
	static int N ;
	static HashSet<Integer> answer;
	public static void comb(int idx,int count) {
		if(count==N)
		{
			int sum=0;
			for(int i=0;i<selected.length;i++) {
				sum+=selected[i];
			}
			answer.add(sum);
		}
		if(count==N) return;
		for(int i=idx;i<romeNum.length;i++) {
			selected[count]=  romeNum[i];
			comb(i,count+1);
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		selected= new int[N];
		answer = new HashSet<Integer>();
		comb(0,0);
		System.out.println(answer.size());
	}

}
