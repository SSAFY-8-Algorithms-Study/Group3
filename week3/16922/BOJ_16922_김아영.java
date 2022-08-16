package week3;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class BOJ_16922_김아영 {

	static int arr[] = {1,5,10,50};
	static HashSet<Integer> arrsum;
	static int N;
	
	public static void dfs(int level, int sum, int start) {
		
		if (level > N) return;

		if (level == N) {
			arrsum.add(sum);
			return ;
		}
		
		for(int i=start;i<4;i++) {
			dfs(level + 1, sum + arr[i], i);
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arrsum = new HashSet<>();
		dfs(0,0,0);
		System.out.println(arrsum.size());
	}

}
