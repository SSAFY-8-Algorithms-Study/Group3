package week3;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

// 로마 숫자 만들기
public class BOJ_16922_최규림 {

	static int n;
	static int answer;
	static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.add(0);

		while ((n--) != 0) {
			Set<Integer> set = new HashSet<>();
			while (!q.isEmpty()) {
				int target = q.poll();
				set.add(target + 1);
				set.add(target + 5);
				set.add(target + 10);
				set.add(target + 50);
			}

			Iterator<Integer> iter = set.iterator();
			while (iter.hasNext()) {
				q.add(iter.next());
			}
		}

		System.out.println(q.size());
	}
}
