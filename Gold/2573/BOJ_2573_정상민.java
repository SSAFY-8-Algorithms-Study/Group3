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
		if(i+1<N && check[i+1][j] == 1) check(i+1,j,check,N,M);//상하좌우 모두탐색하며 이어져있는지 확인
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M]; // 빙하 2차원 배열
		int[][] nextmap = new int[N][M]; // 1년 후 빙하 2
		int[][] check = new int[N][M];
		int year = 0;
		int cnt0 = 0;
		int cntm = 1;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); //맵 입력저장
			}
		}
		while(cntm == 1) {//이어져있는 부분이 1개인 동안, 2개이상이면 탈출하여 답 출력
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
						if(i+1<N && map[i+1][j] == 0) cnt0++; //주변 0개수 카운트
						if(map[i][j] - cnt0 <= 0) { //0보다 작으면 0으로 저장
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
						check(i,j,check,N,M);//안전구역이랑 비슷하게 모두 탐색
						cntm++; // 후 카운트 1번
					} // 이 값이 2이상인 경우 빙산 2개로 나뉜거
				}
			}
			if(cntm == 0) {
				System.out.println(0);
				return; // 빙산 안나뉘고 한번에 다녹는 경우 0 출력
			}
			System.out.println("cntm = "+cntm);
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					System.out.print(nextmap[i][j]+" ");
				}
				System.out.println();
			} // 테스트용 출력
			year++;
			map = nextmap; 
			nextmap = new int[N][M];
		}
		System.out.println(year);
	}// 이문제에서

}
