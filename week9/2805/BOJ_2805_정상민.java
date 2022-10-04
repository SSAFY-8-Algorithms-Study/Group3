package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2805나무자르기 {
	static int N,M,Mtree=0,index,low,high;
	static int[] tree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		tree = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			tree[i] = Integer.parseInt(st.nextToken());
			if(tree[i]>Mtree) Mtree = tree[i];
		}
		low = Mtree - M;
		high = Mtree;
		index = (low + high) / 2;
		
		while(true) {
			long check = check();
			if(check >= M) { //조건만족하니까 더큰거 있는지 찾자
				low = index;
				index = (low + high) / 2;
			}
			else if(check < M) { //조건만족 안하니까 더 작아지자
				high = index;
				index = (low + high) / 2;
			}
			if(low == index) {
				break;
			}
		}
		System.out.println(index);
	}
	private static long check() {
		long cnt = 0;
		for(int i=0;i<N;i++) {
			int get = tree[i] - index;
			if(get > 0) cnt += get;
			if(cnt >= M) return cnt;
		}
		return cnt;
	}
}
