package baek;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class Main_1325효율적인해킹 {
	static ArrayList<Integer>[] list;
	static int N,M,max;
	static boolean[] visit;
	static int[] result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList[N+1];
		result = new int[N+1];
		for(int i=1;i<=N;i++) {
			list[i] = new ArrayList<>();
		}
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			list[from].add(to); //신뢰관계에 따른 해킹가능 리스트
		}
		for(int i=1;i<=N;i++) {
			visit = new boolean[N+1]; //1~N부터 bfs다돌려
			bfs(i);
		}
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<N+1;i++) { //최댓값인 애들 sb에 추가
			if(result[i] == max) sb.append(i+" ");
		}
		System.out.println(sb);
	}
	private static void bfs(int start) {
		int cnt = 0;
		Queue<Integer> que = new LinkedList<>();
		que.add(start);
		visit[start] = true;
		cnt++;
		while(!que.isEmpty()){
			int now = que.poll();
//			for(int i=0;i<list[now].size();i++) {
//				if(visit[list[now].get(i)]) continue; // 이미 감염됬는지 확인
//				//아니면 큐에넣고 감염
//				que.add(list[now].get(i));
//				visit[list[now].get(i)] = true;
//				cnt++;
//			}
			for(int next : list[now]) {
				if(visit[next]) continue;
				que.add(next);
				visit[next] = true;
				cnt++;
			}
		}
		result[start] = cnt; // 각 시작점에 따른 해킹 컴퓨터 수 배열에 저장
		if(max < cnt) max = cnt; // 해킹컴퓨터 수 최댓값 갱신
	}
}
