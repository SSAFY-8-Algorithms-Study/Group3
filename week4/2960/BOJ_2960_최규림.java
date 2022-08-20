package week4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

// 에라토스테네스의 체
public class BOJ_2960_최규림 {

	static int n, k;
	static Queue<Integer> q = new LinkedList<>();
	static Set<Integer> set = new HashSet<>();
	static List<Integer> list = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();

		findPrime();
		while (!q.isEmpty()) {
			int target = q.poll();
			int cnt = 1;
			while (target * cnt <= n) {
				if (!set.contains(target * cnt)) {
					set.add(target * cnt);
					list.add(target * cnt);
				}
				cnt += 1;

			}
			if (list.size() >= k) {
				break;
			}
		}
		System.out.println(list.get(k - 1));
	}

	static void findPrime() {
		for (int i = 2; i <= n; i++) {
			if (isPrime(i)) {
				q.add(i);
			}
		}
	}

	static boolean isPrime(int num) {
		for (int i = 2; i < Math.sqrt(num); i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
}
