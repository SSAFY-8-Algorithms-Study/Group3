package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_2922_김동욱 {
	static int count;
	static int N;
	static char[] alphabets= {'A','B','L'};
	static boolean[] visited;
	static char[] selected;
	static char[] makeNewStringg;
	static ArrayList<Integer> addIndex;
	static long answer;
	public static boolean checkString(char[] result) {
		String t = new String(result);
		if(!t.contains("L")) {
			return false;
		}
		int start = 0;
		int end = 2;
		while (end != N) {
			String test = "";
			for (int i = start; i <= end; i++) {
				test += result[i] + "";
			}
			int conso=0;
			int vowel=0;
			for(int i=0;i<3;i++) {
				char inp = test.charAt(i);
				if(inp == 'A'||inp == 'E'||inp == 'I'||inp == 'O'||inp == 'U') {
					vowel++;
				}
				else {
					conso++;
				}
			}
			if(conso>=3 || vowel>=3) {
				return false;
			}
			start++;
			end++;
		}
		return true;
	}
	public static void perm(int idx) {
		if(idx==count) {
			//System.out.println(Arrays.toString(selected));
			char [] tmp = makeNewStringg.clone();
			int s =0;
			for(int i=0;i<tmp.length;i++) {
				if(tmp[i]== '_') {
					tmp[i] = selected[s++];
				}
			}
			//System.out.println(Arrays.toString(tmp));
			if(checkString(tmp)) {
				//System.out.println("chekString" +Arrays.toString(tmp));
				long tempAns=1;
				for(int i: addIndex) {
					if(tmp[i]=='A') {
						tempAns*=5;
					}
					else if(tmp[i]=='B') {
						tempAns*=20;
					}
					else if(tmp[i]=='L') {
						tempAns*=1;
					}
				}
				answer+=tempAns;
			}
			return;
		}

		for(int i=0;i<alphabets.length;i++) {
			selected[idx] = alphabets[i];
			perm(idx+1);
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		String makeNewString = "";
		N = input.length();
		addIndex = new ArrayList<>();
		for (int i = 0; i < input.length(); i++) {
			char tmc = input.charAt(i);
			if (tmc == '_') {
				count++;
				addIndex.add(i);
				makeNewString += tmc + "";
			} else if (tmc == 'A' || tmc == 'E' || tmc == 'I' || tmc == 'O' || tmc == 'U') {
				makeNewString += 'A' + "";
			}else if (tmc=='L') {
				makeNewString += 'L' + "";
			}
			else {
				makeNewString += 'B' + "";
			}
			
			
		} // end input
		makeNewStringg = makeNewString.toCharArray();
		//System.out.println(makeNewString);
		selected = new char[count];
		
		answer =0;
		perm(0);
		System.out.println(answer);
	}

}
