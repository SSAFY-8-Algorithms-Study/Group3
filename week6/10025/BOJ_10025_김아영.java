package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10025_김아영 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int arr[] = new int[1000001];
		int index_max = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[b] = a;
			if (b > index_max) {
				index_max = b;
			}
		}
		
		int sum = 0;
		for (int i = 0; i < K * 2 + 1; i++) {
			if (i > 1000000) break;
			sum = sum + arr[i];
		}
		
		int MAX = sum;
		for (int i = 0; i < index_max - ( K * 2 ); i++) {
			if (i > 1000000) break;
			sum = sum - arr[i];
			sum = sum + arr[i + (K * 2 + 1)];
			if (sum > MAX) {
				MAX = sum;
			}
//			System.out.println(i);
		}
		System.out.println(MAX);
	}

}
