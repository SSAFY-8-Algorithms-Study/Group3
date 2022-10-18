package boj;

import java.util.Scanner;
import java.util.*;

public class BOJ_1043_김동욱 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> truth = new ArrayList<>();
		
		int answer;
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		answer = M;
		
		ArrayList<Integer> [] group = new ArrayList[M];
		
		int cnt = sc.nextInt();
		for(int i=0; i<cnt; i++) {
			int people = sc.nextInt();
			truth.add(people);
		}
		
		for(int i=0; i<M; i++) {
			cnt = sc.nextInt();
			group[i] = new ArrayList<>();
			int canLie = 1;
			
			for(int j=0; j<cnt; j++) {
				int people = sc.nextInt();
				group[i].add(people);
			}
		}
		
		Queue<Integer> q = new LinkedList<>();
		int[] groupCheck = new int[M];
		int[] peopleCheck = new int[N+1];
		
		for(int i=0; i<truth.size(); i++) {
			q.add(truth.get(i));
			peopleCheck[truth.get(i)] = 1;
			
		}
		
		while(!q.isEmpty()) {
			int now = q.poll();
			for(int i=0; i<M; i++) {
				if(groupCheck[i] == 1)
					continue;
				if(!group[i].contains(now))
					continue;
				for(int j=0; j<group[i].size(); j++) {
					int next = group[i].get(j);
					
					if(peopleCheck[next] == 1)
						continue;
					peopleCheck[next] = 1;
					q.add(next);
				}
				
				groupCheck[i] = 1;
				answer--;
			}
		}
		
		
		System.out.println(answer);
	}

}