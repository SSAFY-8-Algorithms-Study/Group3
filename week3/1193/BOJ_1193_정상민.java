package baek2839_설탕배달;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int cnt = 0;
		int num = N;
		int min = 5000;
		if(N % 5 == 0 ) { //N이 5의 배수일때 전부다 5키로로
			System.out.println(N / 5);
			return;
		}
		for(int i=0;num > 0;i++) { //5키로짜리 쭉 써보면서 개수 최소값 갱신
			if(i!=0) num = num - 5;
			cnt = i;
			if(num > 0 && num % 3 == 0) { //계산과정에서 0보다 작아지는경우 발생, 
				cnt = cnt + num/3;
				if(min > cnt) min = cnt;
			}
		}
		if(min == 5000) {//초기값 그대로 5000인 경우는 N킬로그램 만들수 없는경우 
			System.out.println(-1);
			return;
		}
		System.out.println(min);
	}

}
