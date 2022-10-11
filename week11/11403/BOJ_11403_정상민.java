import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,num;
	static ArrayList<Integer>[] arr; 
	static int[][] result;
	static boolean[] visit;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new ArrayList[N+1];
		result = new int[N+1][N+1];
		for(int i=1;i<=N;i++) {
			arr[i] = new ArrayList<Integer>();
		}
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				num = Integer.parseInt(st.nextToken());
				if(num == 1) {
					arr[i].add(j);
				}
			}
		}
//		for(int i=1;i<=N;i++) {
//			System.out.println(arr[i].toString());
//		}
		for(int i=1;i<=N;i++) {
			visit = new boolean[N+1];
			bfs(i);
		}
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				sb.append(result[i][j]+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	private static void bfs(int si) {
		Queue<Integer> que = new LinkedList<>();
		que.add(si);
		//얘는 방문체크 하면 안됨
		while(!que.isEmpty()) {
			int i = que.poll();
			for(int d=0;d<arr[i].size();d++) {
				int ni = arr[i].get(d);
				if(visit[ni]) continue;
				que.add(ni);
				visit[ni] = true;
				result[si][ni] = 1;
			}
		}
	}
}
