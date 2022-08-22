package boj;

import java.util.Scanner;

public class BOJ_2960_김동욱 {
	static int N;
	static int K;
	static int count;
	static boolean[] visited;

	public static int primeNum() {
		boolean flag = false;
		// int prime=0;
		for (int i = 2; i < visited.length; i++) {
			flag = false;
			if (visited[i])
				continue;
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					flag = true;
					break;
				}
			}
			if (flag)
				continue;
			return i;
		}
		return 0;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		visited = new boolean[N + 2];
		count = 0;
		int answer=0;
		while (true) {
			int prime = primeNum();
			//System.out.println("Prime Number "+prime);
			for (int i = 2; i < visited.length-1; i++) {
				if (visited[i])
					continue;
				if (i % prime == 0) {
					visited[i] = true;
					//System.out.println("Erased "+ i);
					count++;
				}
				if(count==K) {answer= i;break;} // 소수가 마지막에 위치할 때 detect 하지 못 하고 count를 끝내고 count 검사
			}
			if(count==K) break;
		}
		System.out.println(answer);
	}

}
