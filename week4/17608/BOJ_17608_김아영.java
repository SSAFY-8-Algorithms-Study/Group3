package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_17608_김아영 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		int MAX = Integer.MIN_VALUE;
		int cnt = 0;
		for(int i = N - 1; i >= 0; i--) {
			if (arr[i] > MAX) {
				MAX = arr[i];
				cnt++;
			}
		}
		System.out.println(cnt);
	}

}
