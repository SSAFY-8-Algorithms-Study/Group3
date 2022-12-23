package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2688_김아영 {

	static long arr[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		arr = new long[66][10];

		for (int i = 0; i < 10; i++) {
			arr[1][i] = 1;
		}

		for (int i = 2; i <= 65; i++) {
			arr[i][0] = arr[i - 1][0];
			for (int j = 1; j < 10; j++) {
				arr[i][j] = arr[i][j - 1] + arr[i - 1][j];
			}
		}

//		for (int i = 0; i < 4; i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}

		for (int tc = 0; tc < TC; tc++) {
			int num = Integer.parseInt(br.readLine());
			long sum = 0;
			for (int i = 0; i < 10; i++) {
				sum = sum + arr[num][i];
			}
			System.out.println(sum);
		}

	}

}
