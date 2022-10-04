package week10;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_17179_김지희 {
// 케이크 자르기

	static int N, M, L; // 자르는 회수가 담긴 목록 길이N , 자를 수 있는 지점 개수 M, 롤케이크 길이 L

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		L = sc.nextInt();

		int[] arr = new int[M + 1];
		for (int i = 0; i < M; i++) {
			arr[i] = sc.nextInt();
		}

		arr[M] = L;
		Arrays.sort(arr);

		for (int n = 0; n < N; n++) {
			int cut = sc.nextInt();
			
			int answer = 0;
			int left =0, right=L;

			while(left<=right) {
				int mid = (left+right)/2;
				int cur = 0;
				int cnt = 0;
				for(int i=0; i<=M; i++) {
					if(arr[i] - cur >= mid) {
						cur = arr[i];
						cnt++;
					}
				}
				if(cnt>cut) {
					left = mid + 1;
					answer = Math.max(answer, mid);
				}
				else right = mid -1;
			}
			System.out.println(answer);
		}
		
	}
}
