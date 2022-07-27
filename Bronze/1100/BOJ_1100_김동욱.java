package week1;

import java.util.Scanner;

public class BOJ_1100_김동욱 {

	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		int sum=0;
		for(int i=0;i<8;i++) {
			String temp = sc.nextLine();
			for(int j=0;j<8;j++) {
				char coords = temp.charAt(j);
				if(coords == 'F' && ((i%2==0 && j%2==0) || (i%2!=0 && j%2!=0)))
					sum++;
			}
		}
		System.out.println(sum);
}
}
