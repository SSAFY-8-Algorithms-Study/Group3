package week4;

import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_11286_김아영 {

	
	static class node implements Comparable<node>{
		int origin;
		int abs;

		public node(int origin, int abs) {
			this.origin = origin;
			this.abs = abs;
		}

		@Override
		public int compareTo(node o) {
			if (this.abs > o.abs)
	            return 1;
	        else if (this.abs == o.abs) {
	        	if (this.origin > o.origin) return 1;
	        }
	        return -1;

		}
				
	}
	
	public static void main(String[] args) {
		 PriorityQueue<node> pq = new PriorityQueue<>();
		 
		 Scanner sc = new Scanner(System.in);
		 int N = sc. nextInt();
		 
		 for(int i=0;i<N;i++) {
			 int a = sc.nextInt();
			 if (a == 0) {
				 if (pq.isEmpty()) System.out.println(0);
				 else System.out.println(pq.poll().origin);
			 }
			 else {
				 pq.add(new node(a, Math.abs(a)));
			 }
		 }	 
	}

}
