package boj;

import java.util.Scanner;

public class BOJ_1193_김동욱 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int a = 2, b = 3;
		int tempA=4, tempB=4;
		int ind = 0;
		int num=1;
		boolean flag = false;
		if(N==1) {
			System.out.println(1+"/"+1);
			return;
		}
		while (true) {
			
			if (ind%2==0 && N >= a && N <= b) {
				break;
			}
			if (ind%2==1 && N >= b && N <= a) {
				flag = true;
				break;
			}
			ind++;
			num++;
			if(ind==2) {
				b+=6;
				tempB+=6;
				a+=1;
				continue;
			}
			
			if(ind%2==1) {
				b+=1;
				a+=tempA;
				tempA+=4;
				
			}
			if(ind%2==0) {
				a+=1;
				b+=tempB;
				tempB+=4;
			}
		}
		int down = num+1;
		int up = 1;
		if(flag) {
			down= 1;
			up = num+1;
			for(int i=b;i<=a;i++) {
				if (i == N) {
					System.out.println(up + "/" + down);
					return;
				}
				up--;
				down++;
			}
		}
		for (int i = a; i <= b; i++) {
			if (i == N) {
				System.out.println(up + "/" + down);
			}
			down--;
			up++;
		}
	}
}
