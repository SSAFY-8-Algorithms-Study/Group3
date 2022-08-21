package baek2447_별찍기10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<String> result = star(N); // N값 사이즈에 맞게 메서드 호출
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<result.size();i++) {
			sb.append(result.get(i));
			sb.append("\n");//문자열 리스트 저장하면서 줄바꿈 추가저장
		}
		System.out.println(sb);
	}
	public static ArrayList<String> star(int n) {
		if(n == 1) { // n이 1까지 오면 * 리턴
			ArrayList<String> n1 = new ArrayList<>();
			n1.add("*");
			return n1;
		}// 총 3구간으로 나누는데 위아래 똑같고 중간만 가운데 공백있음
		ArrayList<String> drawstar = star(n/3); // 재귀호출
		ArrayList<String> arr = new ArrayList<>();
		for(int i=0;i<drawstar.size();i++) {
			arr.add(drawstar.get(i)+drawstar.get(i)+drawstar.get(i));
		}/////////////////////
		for(int i=0;i<drawstar.size();i++) {
			String str = "";
			str += drawstar.get(i);
			for(int j=0;j<n/3;j++) {
				str += " ";
			}
			str += drawstar.get(i);
			arr.add(str);
		}/////////////////////
		for(int i=0;i<drawstar.size();i++) {
			arr.add(drawstar.get(i)+drawstar.get(i)+drawstar.get(i));
		}/////////////////////
		return arr;
	}
}
