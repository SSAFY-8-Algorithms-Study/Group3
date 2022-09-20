package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// 듣보잡
public class BOJ_1764_최규림 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Set<String> set = new HashSet<>();
		for (int i = 0; i < N; i++) {
			set.add(br.readLine());
		}
		
		ArrayList<String> list = new ArrayList<>();
		int cnt = 0;
		for (int i = 0; i < M; i++) {
			String name = br.readLine();
			if (set.contains(name)) {
				cnt++;
				list.add(name);
			}
		}
		Collections.sort(list);
		System.out.println(cnt);
		for(String name:list) {
			System.out.println(name);
		}
	}
}
