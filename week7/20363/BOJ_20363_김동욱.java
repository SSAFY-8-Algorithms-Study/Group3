package boj;

import java.util.Scanner;

public class BOJ_20363_김동욱 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long x = sc.nextInt();
		long y= sc.nextInt();
		
		if(x<10 || y<10) { // 둘 중에 하나가 10보다 작으면
			System.out.println(x+y);
			return;
		}
		long max;
		long min;
		if(x>=y) {
			max= x;
			min = y;
		}
		else {
			max= y;
			min = x;
		}
		System.out.println((max+min)+(min/10)); 

	}

}
