package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_18429_김아영 {

	static int N, K;
	static int map[];
	static boolean visit[];
	static int path[];
	static int cnt;
	
	static boolean makesum() {
		
		int sum = 0;
		for(int i=0;i<N;i++) {
			sum = sum + path[i] - K;
			if(sum < 0) return false;
		}
		return true;
	}
	
	static void dfs(int level) {
		
		if (level == N) {
			if (makesum())cnt++;
			return;
		}
		
		for(int i=0;i<N;i++) {
			if (visit[i])continue;
			visit[i] = true;
			path[level] = map[i];
			dfs(level + 1);
			path[level] = 0;
			visit[i] = false;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N];
		path = new int[N];
		visit = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0);
		System.out.println(cnt);
	}

	
	
	
	
}
