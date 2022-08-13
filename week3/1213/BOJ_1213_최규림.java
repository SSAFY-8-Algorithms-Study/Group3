package week3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

// 팰린드롬 만들기
public class BOJ_1213_최규림 {
	static Map<Character, Integer> hm = new HashMap<Character, Integer>();
	static StringBuilder sb = new StringBuilder();
	static ArrayList<Character> list = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			hm.put(c, (hm.containsKey(c)) ? hm.get(c) + 1 : 1);
		}

		Set<Character> set = hm.keySet();

		boolean flag = true;
		char oddChar = 0;
		// 1. 문자열 길이가 홀수인 경우 -> 홀수개 문자1개만 존재, 나머지 짝수개
		if (str.length() % 2 == 1) {
			int odd = 0;
			for (char c : set) {
				int cnt = hm.get(c);
				if (cnt % 2 == 1) {
					odd++;
					oddChar = c;
					for (int i = 0; i < (cnt - 1) / 2; i++) {
						list.add(c);
					}
				} else {
					for (int i = 0; i < cnt / 2; i++) {
						list.add(c);
					}
				}

				if (odd > 1) {
					flag = false;
					break;
				}
			}
		}
		// 2. 문자열 길이가 짝수인 경우 -> 모든 문자의 개수 짝수개
		else {
			for (char c : set) {
				int cnt = hm.get(c);
				if (cnt % 2 == 1) {
					flag = false;
					break;
				} else {
					for (int i = 0; i < cnt / 2; i++) {
						list.add(c);
					}
				}
			}
		}

		if (flag) {
			Collections.sort(list);
			for (int i = 0; i < list.size(); i++) {
				sb.append(list.get(i));
			}
			if (str.length() % 2 == 1) {
				sb.append(oddChar);
			}
			for (int i = list.size() - 1; i >= 0; i--) {
				sb.append(list.get(i));
			}
			System.out.println(sb.toString());
		} else {
			System.out.println("I'm Sorry Hansoo");
		}

	}
}
