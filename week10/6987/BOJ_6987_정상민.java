package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_6987월드컵 {
	static int[][] score;
	static int win,lose, draw,total;
	static boolean flag;
	static int[] team1 = {0,0,0,0,0,1,1,1,1,2,2,2,3,3,4};
	static int[] team2 = {1,2,3,4,5,2,3,4,5,3,4,5,4,5,5};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int t=0;t<4;t++) {
			score = new int[6][4];
			win = 0;
			lose = 0;
			draw = 0;
			total = 0;
			flag = false;
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < 6 ; i++) {
				for(int j = 0 ; j < 3 ; j++) {
					score[i][j] = Integer.parseInt(st.nextToken());
					total += score[i][j];
				}
			}
			if(total >30) {
				System.out.print(0+" ");
				continue;
			}
			check(0);
			if(flag) System.out.print(1+" ");
			else System.out.print(0+" ");

		}
	}
	private static void check(int idx) {
		if(flag) return;
		if(idx==15) {
			flag=true;
			return;
		}
		int a = team1[idx];
		int b = team2[idx];
		//a가 이기는 경우
		if(score[a][0]>0 && score[b][2]>0) {
			score[a][0]--;
			score[b][2]--;
			check(idx+1);
			score[a][0]++;
			score[b][2]++;
		}
		//a와 b가 비기는 경우
		if(score[a][1]>0 && score[b][1]>0) {
			score[a][1]--;
			score[b][1]--;
			check(idx+1);
			score[a][1]++;
			score[b][1]++;
		}
		//a가 지는 경우
		if(score[a][2]>0 && score[b][0]>0) {
			score[a][2]--;
			score[b][0]--;
			check(idx+1);
			score[a][2]++;
			score[b][0]++;
		}
	}
}
