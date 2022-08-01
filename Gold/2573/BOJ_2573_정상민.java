package baek2573;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void check(int i,int j,int[][] check,int N,int M) {
		check[i][j] = 0;
		if(j-1>=0 && check[i][j-1] == 1) check(i,j-1,check,N,M);
		if(j+1<M && check[i][j+1] == 1) check(i,j+1,check,N,M);
		if(i-1>=0 && check[i-1][j] == 1) check(i-1,j,check,N,M);
		if(i+1<N && check[i+1][j] == 1) check(i+1,j,check,N,M);
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M]; // 빙하 2차원 배열
		int[][] nextmap = new int[N][M];
		int[][] check = new int[N][M];
		int year = 0;
		int cnt0 = 0;
		int cntm = 1;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		while(cntm == 1) {
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ"+year);
			cntm = 0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(map[i][j] == 0) {
						nextmap[i][j] = map[i][j];
						check[i][j] = 0;
					}
					else {
						if(j-1>=0 && map[i][j-1] == 0) cnt0++;
						if(j+1<M && map[i][j+1] == 0) cnt0++;
						if(i-1>=0 && map[i-1][j] == 0) cnt0++;
						if(i+1<N && map[i+1][j] == 0) cnt0++;
						if(map[i][j] - cnt0 <= 0) {
							check[i][j] = 0;
							nextmap[i][j] = 0;
						}
						else {
							check[i][j] = 1;
							nextmap[i][j] = map[i][j] - cnt0;
						}
						cnt0 = 0;
					}
				}
			}
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(check[i][j] != 0) {
						check(i,j,check,N,M);
						cntm++;
					}
				}
			}
			if(cntm == 0) {
				System.out.println(0);
				return;
			}
			System.out.println("cntm = "+cntm);
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					System.out.print(nextmap[i][j]+" ");
				}
				System.out.println();
			}
			year++;
			map = nextmap;
			nextmap = new int[N][M];
		}
		System.out.println(year);
	}

}
