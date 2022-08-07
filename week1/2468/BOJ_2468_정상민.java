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
	} //상하좌우 탐색하며 check배열 1로 바꾸고 계속 주변 탐색

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
				map[i][j] = Integer.parseInt(st.nextToken());//맵 입력받으며
				if(map[i][j] > maxheight) maxheight = map[i][j];// 최대높이값 저장
			}
		}
		for(int h=0;h<maxheight+1;h++) {// '0'부터 최대 높이까지 반복문수행
			int safe = 0;//안전구역 카운트 변수
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
						find(i,j,N,check); //이어져있는 안전구역 모두 탐색
						safe++;// 후 카운트 ++
						System.out.println();
					}
				}
			}
			System.out.println(h+"ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			if(safe > safemax) safemax = safe; //최대값 저장
		}
		System.out.println(safemax);
		
	}//여기서는 탐색메서드에서 상하좌우 모두 할 필요없고, (우,하)만 하면된다고 생각했는데 아님
	

}
