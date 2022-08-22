package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class BOJ_11286_김동욱 {
	static Queue<Long> pqueue;
	static StringBuilder sb;
	public static void add(long x) {
		pqueue.add(x);
		}
	public static long poll() {
		return pqueue.poll();
		}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		pqueue = new PriorityQueue<>(new Comparator<Long>() {

			@Override
			public int compare(Long o1, Long o2) {
				if(Math.abs(o1)==Math.abs(o2)) {
					if(o1>o2)
						return 1;
					else if(o1<o2)
						return -1;
				}
				else if(Math.abs(o1)>Math.abs(o2)) {
					return 1;
				}
				else if(Math.abs(o1)<Math.abs(o2)) {
					return -1;
				}
				
				return 0;
					
			}
			
		});
		
		for(int i=0;i<N;i++) {
			long temp = Long.parseLong(br.readLine());
			if(temp==0) {
				if(pqueue.isEmpty()) {
					sb.append(0);
					sb.append("\n");
				}
				else {
					sb.append(poll());
					sb.append("\n");
				}
			}
			else {
				add(temp);
			}
		}
		System.out.println(sb);
	}

}
