package baek2468;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2468_정상민 {

	static void find(int x, int y,int N,int[][] check) {
		check[x][y] = 1;
		if(y+1<N && check[x][y+1] == 0) find(x,y+1,N,check);
		if(x+1<N && check[x+1][y] == 0) find(x+1,y,N,check);
		if(x-1>=0 && check[x-1][y] == 0) find(x-1,y,N,check);
		if(y-1>=0 && check[x][y-1] == 0) find(x,y-1,N,check);
		System.out.print("["+x+","+y+"]");
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int safemax = 0;
		int maxheight = 0;
		int[][] map = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] > maxheight) maxheight = map[i][j];
			}
		}
		for(int h=0;h<maxheight+1;h++) {
			int safe = 0;
			int[][] check = new int[N][N]; // 방문여부 배열생성  
			for(int i=0;i<N;i++) { // 잠긴곳 1로 설정
				for(int j=0;j<N;j++) {
					check[i][j] = 0;
					if(map[i][j]<= h) check[i][j] =1;
				}
			}
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(check[i][j] == 0) {
						find(i,j,N,check);
						safe++;
						System.out.println();
					}
				}
			}
			System.out.println(h+"ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			if(safe > safemax) safemax = safe;
		}
		System.out.println(safemax);
		
	}
	

}
