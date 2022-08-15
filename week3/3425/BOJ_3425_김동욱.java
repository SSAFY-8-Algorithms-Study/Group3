package boj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_3425_김동욱 {
	static ArrayList<String> orders;
	static long[] numbers;
	static int N;
	static Deque<Long> gostack;
	static boolean errorFlag=false;

	public static void ERROR() {
		errorFlag = true;
		return;
	}

	public static void stackCheck() {
		if (errorFlag) {
			System.out.println("ERROR");
			gostack.clear();
			return;
		}

		if (gostack.size() != 1) {
			System.out.println("ERROR");
			gostack.clear();
			return;
		} else
			System.out.println(gostack.pop());
	}

	public static void NUMX(long x) {
		gostack.push(x);
	}

	public static void POP() {
		if (gostack.isEmpty()) {
			ERROR();
			errorFlag = true;
			return;
		}
		gostack.pop();
	}

	public static void INV() {
		if (gostack.isEmpty()) {
			ERROR();
			errorFlag = true;
			return;
		}
		long temp = gostack.pop() * -1;
		gostack.addFirst(temp);
	}

	public static void DUP() {
		if (gostack.isEmpty()) {
			ERROR();
			errorFlag = true;
			return;
		}
		long temp = gostack.peek();
		gostack.addFirst(temp);
	}

	public static void SWP() {
		if (gostack.size() < 2) {
			ERROR();
			errorFlag = true;
			return;
		}
		long temp = gostack.poll();
		long temp1 = gostack.poll();
		gostack.push(temp);
		gostack.push(temp1);
	}

	public static void ADD() {
		if (gostack.size() < 2) {
			ERROR();
			errorFlag = true;
			return;
		}
		long temp = gostack.poll();
		long temp1 = gostack.poll();
		long sum = temp + temp1;
		if (Math.abs(sum) > 1000000000) {
			ERROR();
			errorFlag = true;
			return;
		}
		gostack.addFirst(sum);
	}

	public static void SUB() {
		if (gostack.size() < 2) {
			ERROR();
			errorFlag = true;
			return;
		}
		long temp = gostack.poll();
		long temp1 = gostack.poll();
		long sub = temp1 - temp;
		if (Math.abs(sub) > 1000000000) {
			ERROR();
			errorFlag = true;
			return;
		}
		gostack.addFirst(sub);
	}

	public static void MUL() {
		if (gostack.size() < 2) {
			ERROR();
			errorFlag = true;
			return;
		}
		long temp = gostack.poll();
		long temp1 = gostack.poll();
		long mul = temp * temp1;
		// System.out.println("Shit"+ Math.abs(mul));
		if (Math.abs(mul) > 1000000000) {
			ERROR();
			errorFlag = true;
			return;
		}
		gostack.addFirst(mul);
	}

	public static void DIV() {
		if (gostack.size() < 2) {
			ERROR();
			errorFlag = true;
			return;
		}
		long temp = gostack.poll();
		long temp1 = gostack.poll();
		if (temp == 0) { // 0으로 나누려 할때
			ERROR();
			errorFlag = true;
			return;
		}
		long div = Math.abs(temp1) / Math.abs(temp);
		if ((temp < 0 && temp1 >= 0) || (temp > 0 && temp1 < 0))
			div *= -1; // 둘 중 하나가 음수라면 몫은 음수
		if (Math.abs(div) > 1000000000) {
			ERROR();
			errorFlag = true;
			return;
		}
		gostack.addFirst(div);
	}

	public static void MOD() {
		if (gostack.size() < 2) {
			ERROR();
			errorFlag = true;
			return;
		}
		long temp = gostack.poll();
		long temp1 = gostack.poll();
		if (temp == 0) { // 0으로 나누려 할때
			ERROR();
			errorFlag = true;
			return;
		}
		long mod = Math.abs(temp1) % Math.abs(temp);
		if (temp1 < 0)
			mod *= -1; // 둘 중 하나가 음수라면 몫은 음수
		if (Math.abs(mod) > 1000000000) {
			ERROR();
			errorFlag = true;
			return;
		}
		gostack.addFirst(mod);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		File file = new File("output.txt");
//		FileOutputStream fos = new FileOutputStream(file);
//		PrintStream ps = new PrintStream(fos);
//		System.setOut(ps);
		while (true) {
			orders = new ArrayList<>();
			while (true) {
				String order = br.readLine();
				if (order.equals("END")) {
					break;
				} else if (order.equals("QUIT"))
					return;
				orders.add(order);
			} // end order input
			N = Integer.parseInt(br.readLine());
			numbers = new long[N]; // int 형은 20억까지 저장가능하므로 ok
			for (int i = 0; i < N; i++) {
				numbers[i] = Integer.parseInt(br.readLine());
			}
			gostack = new ArrayDeque<>();
			for (int i = 0; i < N; i++) {
				gostack.push(numbers[i]);
				errorFlag = false;
				for (int j = 0; j < orders.size(); j++) {
					String temp = orders.get(j);
					if (temp.length() > 3) { // NUM 거르
						st = new StringTokenizer(temp);
						st.nextToken();
						NUMX(Integer.parseInt(st.nextToken())); // stack 에 num 추가
						continue;
					} else if (temp.equals("POP")) {
						POP();
						continue;
					} else if (temp.equals("INV")) {
						INV();
						continue;
					} else if (temp.equals("DUP")) {
						DUP();
						continue;
					} else if (temp.equals("SWP")) {
						SWP();
						continue;
					} else if (temp.equals("ADD")) {
						ADD();
						continue;
					} else if (temp.equals("SUB")) {
						SUB();
						continue;
					} else if (temp.equals("MUL")) {
						MUL();
						continue;
					} else if (temp.equals("DIV")) {
						DIV();
						continue;
					} else if (temp.equals("MOD")) {
						MOD();
						continue;
					}
				}
				stackCheck();
			}
			br.readLine();
			System.out.println();
		}

	}

}
