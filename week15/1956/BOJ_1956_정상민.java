import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int V, E;
	static int min = 987654321;
	static int[][] dis;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		dis = new int[V+1][V+1];
		
		for (int i = 1; i <= V; i++) {
			for(int j=1;j<=V;j++) {
				dis[i][j] = 987654321;
			}
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			dis[start][end] = distance;
		}

		for(int k=1;k<=V;k++) { // k를 경유해서
			for(int i=1;i<=V;i++) {
				for(int j=1;j<=V;j++) { //i -> j 로 가는 최소길이를 구하자
					dis[i][j] = Math.min(dis[i][j], dis[i][k] + dis[k][j]);
				}
			}
		} // dis 배열에 각점에서 각점으로 가는 최소경로가 들어가 있다.
		for(int i=1;i<=V;i++) {
			min = Math.min(dis[i][i], min);
		}
		System.out.println(min==987654321?-1:min);
		
	}

}
