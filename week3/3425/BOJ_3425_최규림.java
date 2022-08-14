package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

// 고스택
public class BOJ_3425_최규림 {
	static StringBuilder sb = new StringBuilder();
	static ArrayList<String> list;
	static Stack<Long> stack;
	static int maxValue = (int) Math.pow(10, 9);

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			list = new ArrayList<>();
			while (true) {
				String order = br.readLine();

				// QUIT 입력받으면 종료
				if (order.equals("QUIT")) {
					System.out.print(sb.toString());
					return;
				}
				// END 입력받을 때까지 명령어 입력 및 큐에 저장
				if (order.equals("END")) {
					break;
				}
				list.add(order);
			}

			// 큐에 숫자 저장
			int n = Integer.parseInt(br.readLine());
			for (int i = 0; i < n; i++) {
				long target = Long.parseLong(br.readLine());
				simulate(target);
			}
			sb.append('\n');
		}
	}

	static void simulate(long target) {
		stack = new Stack<>();
		stack.add(target);
		boolean flag = true;
		// 명령어 수행하기
		for (int i = 0; i < list.size(); i++) {
			if (!calc(list.get(i))) {
				flag = false;
				break;
			}
		}
		if (stack.size() != 1 || !flag) {
			sb.append("ERROR").append('\n');
		} else {
			sb.append(stack.peek()).append('\n');
		}
	}

	static boolean calc(String order) {
		long right, left, result, first, second;
		String[] o = order.split(" ");
		switch (o[0]) {
		case "NUM": // NUM X
			long x = Long.parseLong(o[1]);
			stack.add(x);
			break;
		case "POP":
			// 스택이 빈 경우, 에러
			if (stack.isEmpty()) {
				return false;
			}
			stack.pop();
			break;
		case "INV":
			// 스택이 빈 경우, 에러
			if (stack.isEmpty()) {
				return false;
			}
			long target = stack.pop();
			stack.add(target * (-1));
			break;
		case "DUP":
			if (stack.isEmpty()) {
				return false;
			}
			stack.add(stack.peek());
			break;
		case "SWP":
			if (stack.size() < 2) {
				return false;
			}
			first = stack.pop();
			second = stack.pop();
			stack.add(first);
			stack.add(second);
			break;
		case "ADD":
			if (stack.size() < 2) {
				return false;
			}
			first = stack.pop();
			second = stack.pop();
			if (Math.abs(first + second) > maxValue) {
				return false;
			}
			stack.add(first + second);
			break;
		case "SUB":
			if (stack.size() < 2) {
				return false;
			}
			first = stack.pop();
			second = stack.pop();
			if (Math.abs(second - first) > maxValue) {
				return false;
			}
			stack.add(second - first);
			break;
		case "MUL":
			if (stack.size() < 2) {
				return false;
			}
			first = stack.pop();
			second = stack.pop();
			if (Math.abs(second * first) > maxValue) {
				return false;
			}
			stack.add(first * second);
			break;
		case "DIV":
			// 피연산자 수가 2개보다 적은 경우, 에러
			if (stack.size() < 2) {
				return false;
			}
			right = stack.pop();
			left = stack.pop();

			// 0으로 나누는 경우, 에러
			if (right == 0) {
				return false;
			}

			result = Math.abs(left) / Math.abs(right);
			// 피연산자에 음수가 1개인 경우
			if (left < 0 ^ right < 0) {
				stack.add(result * (-1));
			} else {
				stack.add(result);
			}
			break;
		case "MOD":
			// 피연산자 수가 2개보다 적은 경우, 에러
			if (stack.size() < 2) {
				return false;
			}
			right = stack.pop();
			left = stack.pop();

			// 0으로 나누는 경우, 에러
			if (right == 0) {
				return false;
			}

			result = Math.abs(left) % Math.abs(right);
			// 피제수가 음수인 경우
			if (left < 0) {
				stack.add(result * (-1));
			} else {
				stack.add(result);
			}
			break;
		}
		return true;
	}
}
