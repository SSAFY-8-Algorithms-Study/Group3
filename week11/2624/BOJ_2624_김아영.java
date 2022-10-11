package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2624_김아영 {

	static int T, k;
	static ArrayList<Integer>[] arr;
	static int result;
	static int path[];

	static void dfs(int level, int sum) {

		if (sum > T)
			return;
		if (level == k) {
			if (sum == T) {
				result++;
			}
			return;
		}
		if (level > k) return;
		
		for (int j = 0; j < arr[level].size(); j++) {
			dfs(level + 1, sum + arr[level].get(j));
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		arr = new ArrayList[k];
		path = new int[k];
		for (int i = 0; i < k; i++) {
			arr[i] = new ArrayList();
		}

		for (int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			for (int j = 0; j < n + 1; j++) {
				arr[i].add(p * j);
			}
		}
//		 System.out.println(T + " " + k);
//		for (int i = 0; i < k; i++) {
//			for(int j=0;j<arr[i].size();j++) {
//				System.out.print(arr[i].get(j) + " ");
//			}
//			System.out.println();
//		}
		dfs(0, 0);
		System.out.println(result);

	}

}
