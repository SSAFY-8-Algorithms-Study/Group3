package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17070파이프옮기기1 {
	static int shape=1,N; // 1번 가로, 2번 세로 , 3번 대각선
	static int result;
	static int[] shape1 = {0,1}; // 가로 체크
	static int[][] shape3 = {{0,1},{1,0},{1,1}};// 대각선 체크
	static int[] shape2 = {1,0}; // 세로 체크
	static int[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0,1,1);
		System.out.println(result);
	}
	private static void dfs(int x, int y, int type) {
		if(x==N-1 && y==N-1) {
			result++;
			return;
		}
		if(type == 1) { //기존모양이 가로일때
			int ni = x + shape1[0];
			int nj = y + shape1[1];
			if(ni>=0 && ni<N && nj>=0 && nj<N && map[ni][nj]!=1) {
				dfs(ni,nj,1); // 가로 -> 가로
			}
			int flag = 0;
			for(int d=0;d<3;d++) {
				ni = x + shape3[d][0];
				nj = y + shape3[d][1];
				if(ni<0 || ni>=N || nj<0 || nj>=N || map[ni][nj]==1) {
					flag = 1;
					break;
				}
			}
			if(flag == 0) dfs(x+1,y+1,3); // 가로 -> 대각선
		}
		else if(type == 2) { // 기존모양이 세로일때
			int ni = x + shape2[0];
			int nj = y + shape2[1];
			if(ni>=0 && ni<N && nj>=0 && nj<N && map[ni][nj]!=1) {
				dfs(ni,nj,2); // 세로 -> 세로
			}
			int flag = 0;
			for(int d=0;d<3;d++) {
				ni = x + shape3[d][0];
				nj = y + shape3[d][1];
				if(ni<0 || ni>=N || nj<0 || nj>=N || map[ni][nj]==1) {
					flag = 1;
					break;
				}
			}
			if(flag == 0) dfs(x+1,y+1,3); // 세로 -> 대각선
		}
		else { // 기존모양 대각선일때
			int ni = x + shape1[0];
			int nj = y + shape1[1];
			if(ni>=0 && ni<N && nj>=0 && nj<N && map[ni][nj]!=1) {
				dfs(ni,nj,1); // 대각선 -> 가로
			}
			ni = x + shape2[0];
			nj = y + shape2[1];
			if(ni>=0 && ni<N && nj>=0 && nj<N && map[ni][nj]!=1) {
				dfs(ni,nj,2); //대각선 -> 세로
			}
			int flag = 0;
			for(int d=0;d<3;d++) {
				ni = x + shape3[d][0];
				nj = y + shape3[d][1];
				if(ni<0 || ni>=N || nj<0 || nj>=N || map[ni][nj]==1) {
					flag = 1;
					break;
				}
			}
			if(flag == 0) dfs(x+1,y+1,3); // 대각선 -> 대각선
		}
	}
}
