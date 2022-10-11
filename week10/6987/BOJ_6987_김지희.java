package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6987_김지희 {
	static int[][] input, match;
	static int[] select;
	static boolean flag;
	
	static int[][] poss = { {0,2}, {1,1}, {2,0}};
	static int[][] comb= {
			{0,1},{0,2},{0,3},{0,4},{0,5},
			{1,2},{1,3},{1,4},{1,5},
			{2,3},{2,4},{2,5},
			{3,4}, {3,5},
			{4,5}	
	};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=4; tc++) {
			st = new StringTokenizer(br.readLine());
			input = new int[6][3];
			for(int i=0; i<6; i++) {
				for(int j=0; j<3; j++) {
					input[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			match = new int[6][3];
			flag = false;
			select = new int[2];
			
			dfs(0);
			if(flag) {
				sb.append(1+ " ");
			}else {
				sb.append(0 + " ");
			}
		}
		
		System.out.println(sb.toString());
	}

	
	private static void dfs(int cnt) { 
		if(cnt==15) {
			if(flag) return;
			
			for(int i=0; i<6; i++) {
				for(int j=0; j<3; j++) {
					if(match[i][j] != input[i][j]) return;
				}
			}
			flag = true;
			return;
		}
		
		int t1 = comb[cnt][0];
		int t2 = comb[cnt][1];
		
		for(int i=0; i<3; i++) {
			int x = poss[i][0];
			int y = poss[i][1];
			
			match[t1][x]++;
			match[t2][y]++;
			dfs(cnt+1);
			match[t1][x]--;
			match[t2][y]--;
		}
		
		
		
		
	}
}
