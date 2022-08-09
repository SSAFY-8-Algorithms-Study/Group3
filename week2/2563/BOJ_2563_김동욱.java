package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2563_김동욱 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int papers = Integer.parseInt(br.readLine());
		int [][] board = new int[100][100]; // 100 x 100 좌표로 생각
		int count=0;
		for(int i=0;i<papers;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			for(int j=y;j<y+10;j++) {
				for(int k=x;k<x+10;k++) {
					if(board[j][k]==1)
						continue;
					board[j][k]=1;
				}
			}
		}
		for(int i=0;i<100;i++) {
			for(int j=0;j<100;j++) {
				if(board[i][j]==1)
					count++;
			}
		}
		System.out.println(count);
	}

}
