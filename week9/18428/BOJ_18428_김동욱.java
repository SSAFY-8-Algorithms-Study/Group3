package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_18428_kimdongwook {
	static int N;
	static char[][] board;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st;
		N = Integer.parseInt(br.readLine());
		board= new char[N][N];
		for(int i=0;i<N;i++) {
			st = br.readLine().replace(" ","");
			board[i]= st.toCharArray(); 
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				System.out.printf("%c ",board[i][j]);
			}
			System.out.println();
		} // end input
	}

}
