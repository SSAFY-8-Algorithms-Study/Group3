package week1;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_1059_김동욱 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int l = sc.nextInt();
		int S[] = new int[l];
		for(int i=0;i<l;i++) {
			S[i]= sc.nextInt();
		}
		int n = sc.nextInt();
		
		Arrays.sort(S); // Set 이므로 구간 순서대로 정렬
		if(n==1) { // n = 1
			System.out.println(S[0]-2); // 1* S[0]-2
			return ;
		}
		for(int i=0;i<S.length-1;i++) {
			
			if(S[0]>n) { // 1 < n < S[0]
				System.out.println((n-1+1)*(((S[0]-1)-n)+1)-1);
				break;
			}
			else if(n==S[i] || n==S[i+1]) // S에서 나온 구간 중 둘 중에 하나가 n 하고 같으면 
			{
				System.out.println(0);
				break;
			}
			else if(n > S[i] && n < S[i+1]) { // 범위 안에 들어갈 
				if(S[i+1]-S[i] <=2) { // 범위 차가 -2, 즉 범위에 들어갈 숫자가 하나일 때
					System.out.println(0);
					break;
				}
				// System.out.println(S[i]+" "+S[i+1]);
				System.out.println((n-(S[i]+1)+1)*(((S[i+1]-1)-n)+1)-1); // (A 집합갯수 *B 집합 갯수 )에서[n,n]하나 제거
				break;
			}

		}

	}

}
