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
		} //입력받은 문자열 정수배열로 전환
	}
	int size = N>M ? M : N; // 가로가 긴지 세로가 긴지, 작은값이 정사각형 최대크기
	int max = 1;
	int value = 0;
	for(int i=0;i<N;i++) {
		for(int j=0;j<M;j++) {
			value = map[i][j];//정사각형 왼쪽상단 꼭지점
			for(int k=1;k<size;k++) {
				if(i+k>=N || j+k>=M) continue; // 범위 넘어가면 스킵
				if(map[i+k][j]==value && map[i][j+k]==value&&map[i+k][j+k]==value) {//정사각형 크기로 값 다 똑같으면
					if((k+1)*(k+1) > max) max = (k+1) * (k+1); //최대값보다 크면 저장
				}
			}
		}
	}
	System.out.println(max);
}
}
