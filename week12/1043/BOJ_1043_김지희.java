package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 거짓말
public class BOJ_1043_김지희 {
	static int N, M, answer;
	static ArrayList<Integer>[] partys;
	static ArrayList<Integer>[] persons;
	static Queue<Integer> Q;
	static int[] visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		partys = new ArrayList[M+1];
		persons = new ArrayList[N+1];
		
		for(int i=0; i<=M; i++) {
			partys[i] = new ArrayList<>();
		}
		for(int i=0; i<=N; i++) {
			persons[i] = new ArrayList<>();
		}
		
		visit = new int[N+1];
		Q = new LinkedList<Integer>();
		
		st = new StringTokenizer(br.readLine());
		int knows = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<=knows; i++) {
			int a = Integer.parseInt(st.nextToken());
//			System.out.print(a+" " );
			Q.add(a);
			visit[a] = 1;
		}
//		System.out.println();
		
		for(int m=1; m<=M; m++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); //파티에 참여하는 사람 
			for(int i=1; i<=n; i++) {
				partys[m].add(Integer.parseInt(st.nextToken()));
			}
			
			// 같은 파티인 사람들끼리 연결해주기
			for(int i : partys[m]) {
				for(int j : partys[m]) {
					if(i!=j) {
						if(!persons[i].contains(j)) {
							persons[i].add(j);
							persons[j].add(i);
						}
					}
				}
			}
		}
		
		answer = 0;
		check();
		System.out.println(answer);
	}
	
	private static void check() {
		
		while (!Q.isEmpty()) {
			int cur = Q.poll();
			for(int p : persons[cur]) {
				if(visit[p]==0) {
					visit[p] = 1;
					Q.add(p);
				}
			}
		}
		
		for(int i=1; i<partys.length; i++) {
			boolean flag = true;
			for(int num : partys[i]) {
				if(visit[num]==1) {
					flag = false;
					break;
				}
			}
			if(flag) answer++;
		}
	}
}
