package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_14267_김지희 {
	static int n, m;
	static ArrayList<Integer>[] graph;
	static int[] praise;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		graph = new ArrayList[n + 1];
		for (int i = 0; i < n + 1; i++) {
			graph[i] = new ArrayList<>();
		}
		praise = new int[n + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			int sup = Integer.parseInt(st.nextToken());
			if (sup != -1) {
				graph[sup].add(i);
			}
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int eno = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			praise[eno] += p;
		}
		
		for(int i=1; i<=n; i++) {
			for(int next : graph[i]) {
				praise[next] += praise[i];
			}
		}
		
		for(int i=1; i<=n; i++) {
			System.out.print(praise[i]+ " ");
		}
	}


}
