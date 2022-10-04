package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_9205맥주마시며걷기 {
	static class node{
		int i,j,beer,count;
		public node(int i, int j) {
			this.i = i;
			this.j = j;
			this.beer = beer;
			this.count = count;
		}
		
	}
	static int N;
	static node home,target;
	static node[] cu;
	static ArrayList<ArrayList<Integer>> map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int tc=1;tc<=T;tc++) {
			N = Integer.parseInt(br.readLine());
//			st = new StringTokenizer(br.readLine());
//			home = new node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),20,0);
			cu = new node[N+2];
			for(int i=0;i<N+2;i++){
				st = new StringTokenizer(br.readLine());
				cu[i] = new node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
//			st= new StringTokenizer(br.readLine());
//			target = new node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),0,0);
			map = new ArrayList<>();
			for(int i=0; i<N+2; i++) map.add(new ArrayList<>());
			
            for(int i=0; i<N+1; i++){
                for(int j=i+1; j<N+2; j++){
                    int distance = Math.abs(cu[i].i - cu[j].i) + Math.abs(cu[i].j - cu[j].j);
                    if(distance <= 1000)
                    {
                        map.get(i).add(j);
                        map.get(j).add(i);
                    }
                }
            }
			System.out.println(bfs()?"happy":"sad");
		}
	}
	private static boolean bfs() {
	    {
	        Queue<Integer> que = new LinkedList<>();
	        que.add(0);

	        boolean [] visited = new boolean[N+2];
	        visited[0] = true;

	        while(!que.isEmpty()){
	            int now = que.poll();

	            if(now == N+1) return true;
	            for(int i : map.get(now)){
	                if(visited[i]) continue;
	                visited[i] = true;
	                que.add(i);
	            }
	        }
	        return false;
	    }        
	}
}	
