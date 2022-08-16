package baek1213_팰린드롬만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int oddcnt=0,oddidx=0;
		char[] arr = str.toCharArray();
		char[] result = new char[arr.length];
		Arrays.sort(arr); //오름차순 정렬, 나중에
		ArrayList<Character> list = new ArrayList<>();
		for(char cha : arr) {
			if(!list.contains(cha)) {
				list.add(cha);
			}
		}// 중복제거 해서  리스트에 넣기
		int[] count = new int[list.size()];
		for(int i=0;i<list.size();i++) {
			for(int j=0;j<arr.length;j++) {
				if(list.get(i) == arr[j]) count[i]++;
			}
		}// 각 문자 개수 카운트
		for(int i=0;i<count.length;i++) {
			if(count[i] % 2 == 1) {
				oddcnt++; 
				oddidx = i;
			}
		}
//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		if(oddcnt >= 2) {
			System.out.println("I'm Sorry Hansoo");
			return;
		} // 만약 홀수인 문자가 2개 이상이면 출력 후 종료
//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		else if(oddcnt == 0) { // 전부다 짝수개 일때
			int ridx = 0;
			for(int i=0;i<count.length;i++) {
				while(count[i] != 0) {
				result[ridx] = list.get(i);
				ridx++;
				count[i] -= 2;
				}
			} // 절반 채워넣기
			int idx = result.length/2 - 1;
			for(int i=result.length/2;i<result.length;i++) {
				result[i] = result[idx];
				idx--;
			} // 나머지 절반 채워넣기

		}
//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		else if(oddcnt == 1) { //홀수인 문자 1개일 때
			int idx = result.length / 2;
			int ridx = 0;
			result[idx] = list.get(oddidx);
			count[oddidx]--; //가운데에 개수홀수인 문자 넣어놓고 시작
			for(int i=0;i<count.length;i++) {
				while(count[i] != 0) {
				result[ridx] = list.get(i);
				ridx++;
				count[i] -= 2;
				}
			} // 절반 채워넣기
			idx--;
			for(int i=result.length/2 + 1;i<result.length;i++) {
				result[i] = result[idx];
				idx--;
			} // 나머지 절반 채워넣기
		}
		for(int i=0;i<result.length;i++) { //만들어진 문자열 출력
			System.out.print(result[i]);
		}
	}

}
