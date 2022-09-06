package week06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//즐거운 단어
public class BOJ_2922_김지희 {

	static String str;
	static int length;
	static long answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
//		System.out.println(str);
		
		length = str.length();
		boolean existL = str.contains("L");
//		System.out.println(existL);
		
		answer =0;
		DFS(0, 0, 0, 1, false);
		
		System.out.println(answer);
	}
	
	private static void DFS(int L, int mCnt, int jCnt, long cnt, boolean lCheck) {
		if(L == length) {
			if(lCheck) answer+=cnt;
			return;
		}
		
		char c = str.charAt(L);
		//빈칸이면
		if(c == '_') {
			if(mCnt<2) {
				DFS(L+1, mCnt+1, 0, cnt*5, lCheck); //모음 5가지 들어가니까
			}
			if(jCnt<2) {
				DFS(L+1, 0, jCnt+1, cnt, true); //L일 때
				DFS(L+1, 0, jCnt+1, cnt*20, lCheck); // 자음 20개니까
			}
		}
		else if(c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
			//모음이 두개까지만 가능하니까
			if(mCnt == 2) { //이미 앞에서 두개가 있
				return;
			}
			DFS(L+1, mCnt+1, 0, cnt, lCheck); //다음 탐색
		}
		else { //자음이면
			if(jCnt == 2) return;
			
			if(c == 'L'){
				DFS(L+1, 0, jCnt+1, cnt, true); //다음 탐색
			}else DFS(L+1, 0, jCnt+1, cnt, lCheck); //다음 탐색
		}
	}
}
