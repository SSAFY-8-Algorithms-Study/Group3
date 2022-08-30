package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class BOJ_16918 {

	static int R, C, N;
	static char map[][];
	static int direct[][] = {{1,0},{-1,0},{0,1},{0,-1}};
	static int timemap[][];
	
	public static void main(String[] args) throws IOException {
		// 2, 4 => 꽉
		// 3초기 상태 주변만 .으로 바뀜
		// 1, 5초 똑같음 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		timemap = new int[R][C];
		for(int i=0;i<R;i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0;j<C;j++) {
				if (map[i][j] == 'O') {
					timemap[i][j] = 3;
				}
			}
		}
		int n = 0;
		while (n++ < N){
			if (n % 2 == 0) { // 폭탄 설치
				for(int i=0;i<R;i++) {
					for(int j=0;j<C;j++) {
						if (map[i][j] == '.') {map[i][j] = 'O'; timemap[i][j] = n + 3;}
					}
				}
			}
			else {
				for(int i=0;i<R;i++) {
					for(int j=0;j<C;j++) { // 시간이 다 된 폭탄 터트림
						if (timemap[i][j] == n) {
							map[i][j] = '.';
							for(int d = 0; d < 4; d++) {
								int dx = i + direct[d][0];
								int dy = j + direct[d][1];
								if (dx <0 || dy < 0 || dx >= R || dy >= C) continue;
								if (timemap[dx][dy] == n)continue;
								if (map[dx][dy] == '.')continue;
								timemap[dx][dy] = 0;
								map[dx][dy] = '.';
							}
						}
					}
				}
			}
		}
		
		for(int i=0;i<R;i++) {
			System.out.println(map[i]);
		}
		
	}
		
		
	

}
