package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2607비슷한단어{

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int result = 0;
		int N = Integer.parseInt(br.readLine());
		//각문자 개수를배열에 저장하고 그다음에하나씩ㅂ ㅣ교하면서 조건에 맞는체
		String word = br.readLine();
		String newword;
		int alpha[] = new int[26];
		for(int i=0;i<word.length();i++) {
			char cha = word.charAt(i);
			alpha[cha - 'A']++;
			//배열에 각 알파벳 몇개인지 적어놔
		}
		for(int i=0;i<N-1;i++) {
			int newalpha[] = new int[26];
			int diff = 0;
			newword = br.readLine();
			for(int j=0;j<newword.length();j++) {
				char newcha = newword.charAt(j);
				newalpha[newcha - 'A']++;
			} //배열에 각 알파벳 몇개인지 적어놔
			for(int k=0;k<26;k++) {
				diff += Math.abs(alpha[k]-newalpha[k]);
			} //각 알파벳 돌면서 원래거랑 다른 수 변수에 기록
			if(diff <= 2 && Math.abs(word.length()-newword.length())<=1) {
				result++; //두 알파벳 길이 차이 1이하이고 다른거변수 2보다 작으면 조건 만족
			}
			
			
		}
		System.out.println(result);
	}

}
