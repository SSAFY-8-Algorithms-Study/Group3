package home0727;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class test1051 {
	
	static int[][]map;
	static int N;
	static int M;
	
	public static int get_sq(int x, int y) {
		
		int sum = 0;
		for(int i=1;i<N-x;i++) {
			int dx = x+i;
			int dy = y+i;
			if (dx <0 || dy<0 || dx >=N || dy>=M)continue;
			int row = map[x][dy];
			int col = map[dx][y];
			int diag = map[dx][dy];
			if (map[x][y] == row && map[x][y] == col && map[x][y] == diag){
				int num = (i+1) * (i+1);
				if (sum < num) {
					sum = num;
				}
			}
		}
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 입력 받기
		map = new int[N][M];
		for(int i=0;i<N;i++) {
			String s = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j]=s.charAt(j) - '0';
			}
		}
		
		int MAX = 1;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				// 함수로 보내기
				int sum = get_sq(i,j);
				if (sum > MAX) {
					MAX = sum;
				}
			}
		}
		System.out.println(MAX);
		
	}
}
