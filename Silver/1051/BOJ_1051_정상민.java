package baek1051;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st = new StringTokenizer(br.readLine());
	int N = Integer.parseInt(st.nextToken());
	int M = Integer.parseInt(st.nextToken());
	int[][] map = new int[N][M];
	for(int i=0;i<N;i++) {
		st = new StringTokenizer(br.readLine());
		String str = st.nextToken();
		for(int j=0;j<M;j++) {
			map[i][j] = str.charAt(j)-'0';
		}
	}
	int size = N>M ? M : N;
	int max = 1;
	int value = 0;
	for(int i=0;i<N;i++) {
		for(int j=0;j<M;j++) {
			value = map[i][j];
			for(int k=1;k<size;k++) {
				if(i+k>=N || j+k>=M) continue;
				if(map[i+k][j]==value && map[i][j+k]==value&&map[i+k][j+k]==value) {
					if((k+1)*(k+1) > max) max = (k+1) * (k+1);
				}
			}
		}
	}
	System.out.println(max);
}
}
