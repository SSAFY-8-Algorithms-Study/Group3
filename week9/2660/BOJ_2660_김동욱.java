package boj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
	
public class BOJ_2660_kdw {
	static int N;
	static LinkedList<Integer>[] adjList;
	static int[] canList;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		adjList = new LinkedList[N+1];
		canList = new int[N];
		for(int i=1;i<=N;i++) {
			adjList[i] = new LinkedList<Integer>();
		}
		while(true) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			if(from==-1 && to== -1) {
				break;
			}
			adjList[from].add(to);
			adjList[to].add(from);
			
		}
	}
}
