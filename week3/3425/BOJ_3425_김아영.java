package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_3425_김아영 {

	static Stack<Long> sta;
	static List<String> command;
	static List<Long> Numcom;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			command = new ArrayList<>();
			Numcom = new ArrayList<>();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			if (s.equals("QUIT"))break;
			while (true) {
				if (s.equals("END"))break;
				command.add(s);
				if (s.equals("NUM")) {Numcom.add(Long.parseLong(st.nextToken()));}
				st = new StringTokenizer(br.readLine());
				s = st.nextToken();		
			}			
			int N = Integer.parseInt(br.readLine());
			for(int i=0;i<N;i++) {
				sta = new Stack<>();
				long stacknum = Integer.parseInt(br.readLine());
				sta.add(stacknum);
				int Numcom_cnt = 0;
				int check = 0;
				for(int j=0;j<command.size();j++) {
					if (command.get(j).equals("DUP")) {
						if (sta.size() == 0) {System.out.println("ERROR"); check++; break;}
						long pk = sta.peek();
						sta.add(pk);
					}
					else if (command.get(j).equals("MUL")) {
						if (sta.size() < 2) {System.out.println("ERROR");check++; break;}
						long a = sta.pop();
						long b = sta.pop();
						if (a * b > 1000000000) {System.out.println("ERROR");check++; break;}
						sta.add(a * b);
					}
					else if (command.get(j).equals("POP")) {
						if (sta.size() == 0) {System.out.println("ERROR");check++; break;}
						sta.pop();
					}
					else if (command.get(j).equals("NUM")) {
						sta.add(Numcom.get(Numcom_cnt++));
					}
					else if (command.get(j).equals("INV")) {
						if (sta.size() == 0) {System.out.println("ERROR");check++; break;}
						long a = sta.pop();
						long abs = Math.abs(a);
						if (abs > 1000000000) {
	                        System.out.println("ERROR");check++; break;
	                    }
						sta.add(a * -1);
					}
					else if (command.get(j).equals("SWP")) {
						if (sta.size() < 2) {System.out.println("ERROR");check++; break;}
						long a = sta.pop();
						long b = sta.pop();
						sta.add(b);
						sta.add(a);
					}
					else if (command.get(j).equals("ADD")) {
						if (sta.size() < 2) {System.out.println("ERROR");check++; break;}
						long a = sta.pop();
						long b = sta.pop();
						if (Math.abs(a + b)> 1000000000) {System.out.println("ERROR");check++; break;}
						sta.add(a + b);
					}
					else if (command.get(j).equals("SUB")) {
						if (sta.size() < 2) {System.out.println("ERROR");check++; break;}
						long a = sta.pop();
						long b = sta.pop();
						if (Math.abs(b - a) > 1000000000) {System.out.println("ERROR");check++; break;}
						sta.add(b - a);
					}
					else if (command.get(j).equals("DIV")) {
						if (sta.size() < 2) {System.out.println("ERROR");check++; break;}
						int flag = 0;
						long a = sta.pop();
						long b = sta.pop();
						if (a == 0) {System.out.println("ERROR");check++; break;}
						if (Math.abs(b / a) > 1000000000) {System.out.println("ERROR");check++; break;}
						if (b < 0) {b = Math.abs(b); flag++;}
						if (a < 0) {a = Math.abs(a); flag++;}
						if (flag == 1) sta.add((b / a) * -1);
						else sta.add(b / a);
					}
					else if (command.get(j).equals("MOD")) {
						if (sta.size() < 2) {System.out.println("ERROR");check++; break;}
						int flag = 0;
						long a = sta.pop();
						long b = sta.pop();
						if (a == 0) {System.out.println("ERROR");check++; break;}
						if (Math.abs(b % a) > 1000000000) {System.out.println("ERROR");check++; break;}
						long mod = Math.abs(b) % Math.abs(a);
						if (b < 0) {
							sta.add(-1 * mod);
						}
						else sta.add(mod);
					}
				}
				if (check != 1) {
					if (sta.size() != 1) {System.out.println("ERROR");}
					else System.out.println(sta.pop());	
				}									
			}	
			System.out.println();
			br.readLine();
			
		}
				

	}

}
