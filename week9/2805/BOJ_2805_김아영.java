package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2805_김아영 {

	static int N;
	static long M;
	static long tree[];
	
	static boolean possible(long height) {
		long len = 0;
		for(int i=0;i<N;i++) {
			 if (tree[i] > height)len += tree[i] - height;
		}
		if (len >= M) return true;
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long low = 1, high = 0;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		tree = new long[1000000];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			tree[i] = Integer.parseInt(st.nextToken());
			high = Math.max(high, tree[i]);
		}
		long result = 0;
		while (low <= high) {
			long mid = (low + high) / 2;
	        if (possible(mid)){
	            result = Math.max(result, mid);
	            low = mid + 1;
	        }
	        else high = mid - 1;
		}
		System.out.println(result);
	}

}
