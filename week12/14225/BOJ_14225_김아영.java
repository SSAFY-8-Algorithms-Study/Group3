package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14225_김아영 {

	static int N;
	static int bat[];
	static boolean[] select;
	static int arr[];

	static void powerset(int idx) {

		if (idx == N) {
			int sum = 0;
			for (int i = 0; i < N; i++) {
				if (select[i])
					sum = sum + arr[i];
			}
			bat[sum] = 1;
			return;
		}
		select[idx] = true;
		powerset(idx + 1);
		select[idx] = false;
		powerset(idx + 1);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		bat = new int[3000000];
		select = new boolean[N];
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		} // input
		powerset(0);
		for (int i = 1; i <= 3000000; i++) {
			if (bat[i] != 1) {
				System.out.println(i);
				break;
			}
		}

	}

}
