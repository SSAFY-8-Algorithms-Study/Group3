package baek1193_분수찾기;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int X = Integer.parseInt(br.readLine());
		int a=1, b=2;
		int flag = 0;
		int cnt = 4, sum = 3;
		if(X==1) {
			System.out.println(1+"/"+1);
			return;
		}
		if(X==2) {
			System.out.println(1+"/"+2);
			return;
		} //초반 값에 대해서 걍 출력해버리기
		for(int i=3;i<=X;i++) { // X-1번 수행
			if(i == cnt) { //cnt 값은 x 또는 y가 0이어서 더하는 메커니즘이 다른경우
				if(flag == 0) {
				a++;
				flag = 1;
				}
				else if(flag == 1) {
					b++;
					flag = 0;
				}
				cnt += sum++;
			} 
			else if(flag == 0) { // 대각선 왼쪽아래 방향
				a++;
				b--;
			}
			else if(flag == 1) { // 대각선 오른쪽 위 방향
				a--;
				b++;
			}
		}
		System.out.println(a+"/"+b);
	}

}
