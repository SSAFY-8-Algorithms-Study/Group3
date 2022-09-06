package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main_2922즐거운단어 {
	static char[] input;
	static int N=0;
	static long result=0;
	static ArrayList<Integer> _idx = new ArrayList<>();
	static boolean[] used = new boolean[26];
	static boolean isL;
	static char[] card={'A','B','L'},use;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine().toCharArray();
		for(int i=0;i<input.length;i++) {
			if(input[i] == '_') {
				N++;
				_idx.add(i);
			}
		}
		use = new char[N];
		perm(0);
		System.out.println(result);
	}
	private static void perm(int idx) {
		if(idx == N) {
			char[] copy = input.clone();
			for(int i=0;i<N;i++) {
				copy[_idx.get(i)] = use[i];
			}
			isL = false;
			check(copy);
			return;
		}
		for(int i=0;i<3;i++) {
			use[idx] = card[i];
			perm(idx+1);
		}
	}
	private static void check(char[] copy) {
		int aeiou=0,moum=0;
		for(int i=0;i<copy.length;i++) {
			char c = copy[i];
			if(c == 'A' ||c == 'E' ||c == 'I' ||c == 'O' ||c == 'U') {
				aeiou++;
				moum = 0;
			}
			else {
				moum++;
				aeiou = 0;
				if(c == 'L') isL = true;
			}
			if(aeiou >= 3 || moum >= 3) return;
		}
		if(isL) {
			System.out.println(Arrays.toString(use));
			long sum=1;
			for(int i=0;i<N;i++) {
				if(copy[_idx.get(i)] == 'A') sum *= 5;
				else if(copy[_idx.get(i)] == 'B') sum *= 20;
			}
			System.out.println(sum);
			result += sum;
		}
	}
}
