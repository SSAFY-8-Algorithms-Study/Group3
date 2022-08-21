package baek17608_막대기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int high = 0, num = 0;
		int cnt = 0;
		Stack<Integer> stack = new Stack<>();
		for(int i=0;i<N;i++) {
			stack.add(Integer.parseInt(br.readLine()));
		}//스텍에 넣기
		high = stack.pop();//맨 오른쪽 값 높이저장
		cnt++; //맨 오른쪽기둥은 무조건 보이므로 카운트 ++
		while(!stack.isEmpty()) {
			num = stack.pop();// 하나씩 빼면서
			if(num > high) { // 이전 높이보다 높아야 보임
				cnt++;
				high = num; 
			}
		}
		System.out.println(cnt);
		
	}

}
