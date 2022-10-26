package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14267_김아영 {

	static int N, M;
	static int[] list;
	static int arr[];
	static int cnt;

//	static void dfs(int level, int sum) {
//
//		if (level == 1) {
//			cnt = sum;
//			return;
//		}
//		//System.out.print(level + " : " + sum + " / ");
//		dfs(list[level], sum + arr[level]);
//	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		list = new int[N + 2];
		arr = new int[N + 2];
		for (int i = 1; i <= N; i++) {
			int a = Integer.parseInt(st.nextToken());
			list[i] = a;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a] = arr[a] + b;
		}
		list[1] = 0;
		for (int i = 1; i <= N; i++) {
			// dfs(i, 0);
			arr[i] = arr[i] + arr[list[i]];
			System.out.print(arr[i] + " ");
		}
	}

}
