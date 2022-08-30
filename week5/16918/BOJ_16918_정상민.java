import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main{
	static class boom{
		int i,j;
		boom(int i,int j){
			this.i=i;
			this.j=j;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int cnt = 1;
		char[][] map = new char[R][C];
		ArrayList<boom> rboom = new ArrayList<>();
		int[] di = {-1,+1,0,0};
		int[] dj = {0,0,-1,+1};
		for(int i=0;i<R;i++) {
			map[i] = br.readLine().toCharArray();
		}
		if(N == 0 || N == 1) {
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<R;i++) {
				for(int j=0;j<C;j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
			System.out.println(sb);
			return;
		}
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) if(map[i][j] == 'O')rboom.add(new boom(i,j));
		}
		while(cnt != N) {
			if(cnt % 2 == 1) {
				for(int i=0;i<R;i++) {
					for(int j=0;j<C;j++) {
						if(map[i][j] != 'O') map[i][j] = 'O';
					}
				}
			}
			else if(cnt % 2 == 0) {
				for(boom b : rboom) {
					map[b.i][b.j] = '.';
					for(int d=0;d<4;d++) {
						int ni = b.i + di[d];
						int nj = b.j + dj[d];
						if(ni>=0 && ni<R && nj>=0 && nj<C) map[ni][nj] = '.';
					}
				}
				rboom = new ArrayList<>();
				for(int i=0;i<R;i++) {
					for(int j=0;j<C;j++) if(map[i][j] == 'O')rboom.add(new boom(i,j));
				}
			}
			cnt++;
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
