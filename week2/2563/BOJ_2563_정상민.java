package baek2563;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int result;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		map = new int[101][101];
		int x,y;
		for(int n=0;n<N;n++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			for(int i=x;i<x+10;i++) {
				for(int j=y;j<y+10;j++) {
					map[i][j] = 1;
				}
			}

		}
		for(int i=0;i<101;i++) {
			for(int j=0;j<101;j++) {
				if(map[i][j] == 1) result++;
			}
		}
		System.out.println(result);
		
	}

}
