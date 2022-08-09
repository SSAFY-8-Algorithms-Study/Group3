package baek2630;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int Wcnt,Bcnt; 
	// N = 사각형 한변길이, (x,y)는 시작점
	public static void check(int N,int x, int y) {
		int color = map[x][y];
		if(N == 1) { // 길이 1이면 색깔++ 후 리턴
			if(color == 0) Wcnt++;
			else if(color == 1) Bcnt++;
			return;
		}
		int flag = 0; // 다 똑같은 상태
		for(int i=x;i<x+N;i++) {
			for(int j=y;j<y+N;j++) {
				if(map[i][j] != color) {
					flag = 1; // 하나라도 달라서 나눠야하는 상태
					break; // 반복문 탈출
				}
			}
			if(flag == 1) break; // 하나라도 달라서 반복문 탈출
		}
		if(flag == 0) { // 탐색 결과 flag=0이면 다 똑같으므로 색깔++
			if(color == 0) Wcnt ++;
			else if(color == 1) Bcnt++;
		}
		else if(flag == 1) { // 네 군데로 나누어 재귀 호출
			check(N/2,x,y);
			check(N/2,x+(N/2),y+(N/2));
			check(N/2,x,y+(N/2));
			check(N/2,x+(N/2),y);
			}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 입력 받기
		check(N,0,0);
		System.out.println(Wcnt);
		System.out.println(Bcnt);
	}

}
