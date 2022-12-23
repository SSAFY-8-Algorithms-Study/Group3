package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 문자열 게임 2
public class BOJ_20437_김지희 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TC; tc++) {
			String str = br.readLine();
			int K = Integer.parseInt(br.readLine());
			if(K==1) {
				System.out.println("1 1");
				continue;
			}
			int[] alphabet = new int[26];
			for(int i=0; i<str.length(); i++) {
				alphabet[str.charAt(i)-'a']++;
			}
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for(int i=0; i<str.length(); i++) { /// 다 볼거야
				if(alphabet[str.charAt(i)-'a']<K) continue;
				
				int cnt = 1;
				for(int j=i+1; j<str.length(); j++) {
					if(str.charAt(i)==str.charAt(j)) // 해당 글자 개수 세기
						cnt++;
					if(cnt==K) { // K개까지 나왔어 , 어차피 카운트 아까 했으니까... 마지막 글자는 첫-마 똑같음
						if(min>j-i+1) min = j-i+1;
						if(max<j-i+1) max = j-i+1;
						break;
					}
				}
			}
			
			if(min==Integer.MAX_VALUE || max == Integer.MIN_VALUE) {
				System.out.println(-1);
			}else System.out.println(min + " "+ max);
		}
	}
}
