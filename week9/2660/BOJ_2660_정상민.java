package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2660회장뽑기 {
	static int N,min;
	static int[] score;
	static boolean[] visit;
	static ArrayList<Integer>[] list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		list = new ArrayList[N+1];
		for(int i=1;i<=N;i++) {
			list[i] = new ArrayList<Integer>();
		}
		min = N;
		score = new int[N+1];
		while(true) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(a==-1 && b==-1) break;
			list[a].add(b);
			list[b].add(a);
		}
		for(int i=1;i<=N;i++) {
			visit = new boolean[N+1];
			System.out.println(i+"ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			bfs(i);
		}
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		for(int i=1;i<=N;i++) {
			if(score[i] == min) {
				sb.append(i+" ");
				cnt++;
			}
		}
		System.out.println(min+" "+cnt);
		System.out.println(sb);
	}
	private static void bfs(int start) {
		Queue<Integer> que = new LinkedList<>();
		que.add(start);
		visit[start] = true;
		int cnt = -1;
		while(!que.isEmpty()) {
			System.out.println(que.toString());
			int size = que.size();
			for(int s=0;s<size;s++) {
				int next = que.poll();
				for(int i=0;i<list[next].size();i++) {
					int friend = list[next].get(i);
					if(visit[friend]) continue;
					que.add(friend);
					visit[friend] = true;
				}
			}
			cnt++;
		}
		min = Math.min(min, cnt);
		score[start] = cnt;
	}

}
