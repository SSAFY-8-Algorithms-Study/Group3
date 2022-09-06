package week06;

import java.util.Scanner;
// 전자레인지
public class BOJ_10162_김지희 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int A=0, B=0, C=0;
		
		if(T>=300) {
			A = T/300;
			T-= 300*A;
		}
		if(T>=60) {
			B = T/60;
			T-= 60*B;
		}
		if(T%10!=0) { //C로도 안나뉘면
			System.out.println(-1);
			return;
		}
		else {
			C = T/10;
			System.out.println(A+" "+B+" "+C);
		}
	}
}
